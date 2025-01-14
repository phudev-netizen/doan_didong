<?php
$host = 'localhost';
$username = 'sa';
$password = '123'; // Nếu bạn đã đặt mật khẩu, thay bằng mật khẩu của bạn
$database = 'ShoppFashions';

// Kết nối
$conn = new mysqli($host, $username, $password, $database);

// Kiểm tra kết nối
if ($conn->connect_error) {
    die("Kết nối thất bại: " . $conn->connect_error);
}
echo "Kết nối thành công!";
?>
