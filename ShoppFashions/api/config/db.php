<?php
$serverName = "localhost"; // Máy chủ cơ sở dữ liệu
$connectionOptions = array(
    "Database" => "ShopFA", // Tên cơ sở dữ liệu
    "Uid" => "sa",          // Tên người dùng cơ sở dữ liệu
    "PWD" => "123",         // Mật khẩu người dùng cơ sở dữ liệu
    "CharacterSet" => "UTF-8" // Đảm bảo mã hóa UTF-8
);


// Kết nối đến cơ sở dữ liệu SQL Server
$conn = sqlsrv_connect($serverName, $connectionOptions);

// Kiểm tra kết nối
if ($conn === false) {
    die(json_encode(array(
        "status" => "error",
        "message" => "Không thể kết nối cơ sở dữ liệu",
        "errors" => sqlsrv_errors()
    )));
}

// Nếu kết nối thành công
echo json_encode(array(
    "status" => "success",
    "message" => "Ket noi csdl thanh cong "
));

// Đóng kết nối khi không còn sử dụng
sqlsrv_close($conn);
?>
