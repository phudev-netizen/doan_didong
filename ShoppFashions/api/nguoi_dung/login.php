<?php

header("Content-Type: application/json; charset=UTF-8");

try {
    // Lấy dữ liệu từ yêu cầu POST (dưới dạng JSON)
    $data = json_decode(file_get_contents("php://input"), true);

    // Kiểm tra dữ liệu đầu vào
    if (!isset($data['username']) || !isset($data['password'])) {
        echo json_encode([
            "status" => "error",
            "message" => "Vui lòng nhập đủ thông tin!"
        ]);
        exit;
    }

    // Lấy thông tin từ dữ liệu đầu vào
    $username = $data['username'];
    $password = $data['password'];

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

    // Truy vấn kiểm tra tên đăng nhập và mật khẩu
    $sql = "SELECT * FROM Tai_khoan WHERE Username = ? AND Mat_khau = ?";
    $stmt = sqlsrv_prepare($conn, $sql, array(&$username, &$password));

    // Thực thi câu truy vấn
    if (!sqlsrv_execute($stmt)) {
        die(json_encode(array(
            "status" => "error",
            "message" => "Lỗi truy vấn",
            "errors" => sqlsrv_errors()
        )));
    }

    // Kiểm tra kết quả
    if (sqlsrv_fetch($stmt)) {
        // Nếu có kết quả, trả về thông báo đăng nhập thành công
        $user = array(
            "Username" => $username,
            "Mat_khau" => $password
        );
        echo json_encode([
            "status" => "success",
            "message" => "Đăng nhập thành công",
            "user" => $user
        ]);
    } else {
        // Nếu không có kết quả, trả về thông báo lỗi
        echo json_encode([
            "status" => "error",
            "message" => "Sai tên đăng nhập hoặc mật khẩu"
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
