<?php
header('Content-Type: application/json');

// URL xác thực token của Google
define('GOOGLE_OAUTH_URL', 'https://oauth2.googleapis.com/tokeninfo');

// Cấu hình cơ sở dữ liệu
$DB_HOST = 'localhost';
$DB_USER = 'sa';
$DB_PASS = '123';
$DB_NAME = 'ShopFA';

// Kết nối cơ sở dữ liệu
$conn = new mysqli($DB_HOST, $DB_USER, $DB_PASS, $DB_NAME);

// Kiểm tra kết nối
if ($conn->connect_error) {
    die(json_encode([
        "status" => "error",
        "message" => "Failed to connect to the database: " . $conn->connect_error
    ]));
}

// Kiểm tra phương thức yêu cầu
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Nhận dữ liệu từ client
    $input = json_decode(file_get_contents('php://input'), true);
    $idToken = $input['idToken'] ?? null;

    if (!$idToken) {
        echo json_encode([
            "status" => "error",
            "message" => "ID Token is required."
        ]);
        exit();
    }

    // Xác thực Google ID Token
    $googleUser = verifyGoogleToken($idToken);
    if (!$googleUser || !isset($googleUser['email'])) {
        echo json_encode([
            "status" => "error",
            "message" => "Invalid ID Token."
        ]);
        exit();
    }

    $email = $googleUser['email'];
    $name = $googleUser['name'] ?? "Unknown User";

    // Kiểm tra xem người dùng đã tồn tại hay chưa
    $stmt = $conn->prepare("SELECT * FROM Tai_khoan WHERE Email = ?");
    $stmt->bind_param("s", $email);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        // Người dùng đã tồn tại
        echo json_encode([
            "status" => "success",
            "message" => "User already exists."
        ]);
    } else {
        // Người dùng chưa tồn tại, thêm mới (bỏ qua cột `picture`)
        $stmt = $conn->prepare("INSERT INTO Tai_khoan (Username, Email) VALUES (?, ?)");
        $stmt->bind_param("ss", $name, $email);
        if ($stmt->execute()) {
            echo json_encode([
                "status" => "success",
                "message" => "User registered successfully."
            ]);
        } else {
            echo json_encode([
                "status" => "error",
                "message" => "Failed to register user: " . $stmt->error
            ]);
        }
    }

    $stmt->close();
} else {
    echo json_encode([
        "status" => "error",
        "message" => "Invalid request method."
    ]);
}

// Đóng kết nối cơ sở dữ liệu
$conn->close();

/**
 * Xác thực Google ID Token
 *
 * @param string $idToken
 * @return array|null
 */
function verifyGoogleToken($idToken) {
    $url = GOOGLE_OAUTH_URL . "?id_token=" . $idToken;
    $response = file_get_contents($url);
    return $response ? json_decode($response, true) : null;
}
?>
