<?php
header("Content-Type: application/json");
require_once("../../config/db.php");
require_once("../../model/san_pham.php");

// Kết nối tới cơ sở dữ liệu
$db = new Database();
$conn = $db->connect();

// Khởi tạo đối tượng Sản Phẩm
$sanPham = new SanPham($conn);
$result = $sanPham->layDanhSachSanPham();

// Kiểm tra và xuất dữ liệu dưới dạng JSON
if ($result->num_rows > 0) {
    $san_pham_arr = [];
    while ($row = $result->fetch_assoc()) {
        $san_pham_arr[] = $row;
    }
    echo json_encode($san_pham_arr);
} else {
    echo json_encode(["message" => "Không có dữ liệu."]);
}
?>

