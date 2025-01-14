<?php

header("Content-Type: application/json; charset=UTF-8");

try {
    // Lấy dữ liệu từ yêu cầu POST (dưới dạng JSON)
    $data = json_decode(file_get_contents("php://input"), true);

    // Kiểm tra dữ liệu đầu vào
    if (!isset($data['username']) || !isset($data['password']) || !isset($data['email'])) {
        echo json_encode([
            "status" => "error",
            "message" => "Vui lòng nhập đầy đủ thông tin (username, password, email)!"
        ]);
        exit;
    }

    // Lấy thông tin từ dữ liệu đầu vào
    $username = $data['username'];
    $password = $data['password'];
    $email = $data['email'];

    // Cấu hình kết nối đến cơ sở dữ liệu SQL Server
    $serverName = "localhost"; // Máy chủ cơ sở dữ liệu
    $connectionOptions = array(
        "Database" => "ShopFA", // Tên cơ sở dữ liệu
        "Uid" => "sa",          // Tên người dùng cơ sở dữ liệu
        "PWD" => "123",         // Mật khẩu người dùng cơ sở dữ liệu
        "CharacterSet" => "UTF-8" // Đảm bảo mã hóa UTF-8
    );

    // Kết nối đến cơ sở dữ liệu SQL Server
    $conn = sqlsrv_connect($serverName, $connectionOptions);

    if ($conn === false) {
        die(json_encode(array(
            "status" => "error",
            "message" => "Không thể kết nối cơ sở dữ liệu",
            "errors" => sqlsrv_errors()
        )));
    }

    // Kiểm tra xem tên đăng nhập đã tồn tại chưa
    $sqlCheck = "SELECT * FROM Tai_khoan WHERE Username = ?";
    $stmtCheck = sqlsrv_prepare($conn, $sqlCheck, array(&$username));

    // Thực thi câu truy vấn kiểm tra
    if (!sqlsrv_execute($stmtCheck)) {
        die(json_encode(array(
            "status" => "error",
            "message" => "Lỗi truy vấn kiểm tra tên đăng nhập",
            "errors" => sqlsrv_errors()
        )));
    }

    // Nếu tên đăng nhập đã tồn tại
    if (sqlsrv_fetch($stmtCheck)) {
        echo json_encode([
            "status" => "error",
            "message" => "Tên đăng nhập đã tồn tại!"
        ]);
    } else {
        // Nếu tên đăng nhập chưa tồn tại, thực hiện đăng ký
        $sqlInsert = "INSERT INTO Tai_khoan (Username, Mat_khau, Email) VALUES (?, ?, ?)";
        $stmtInsert = sqlsrv_prepare($conn, $sqlInsert, array(&$username, &$password, &$email));

        // Thực thi câu truy vấn đăng ký
        if (!sqlsrv_execute($stmtInsert)) {
            die(json_encode(array(
                "status" => "error",
                "message" => "Lỗi khi đăng ký tài khoản",
                "errors" => sqlsrv_errors()
            )));
        }

        // Trả về thông báo đăng ký thành công
        echo json_encode([
            "status" => "success",
            "message" => "Đăng ký thành công!"
        ]);
    }

} catch (Exception $e) {
    // Lỗi hệ thống khác
    echo json_encode([
        "status" => "error",
        "message" => "Lỗi không xác định: " . $e->getMessage()
    ]);
} finally {
    // Đóng kết nối
    if (isset($conn)) {
        sqlsrv_close($conn);
    }
}
?>
