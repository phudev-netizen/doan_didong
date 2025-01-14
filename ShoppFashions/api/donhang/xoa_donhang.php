<?php
header("Content-Type: application/json");
require_once("../../config/db.php");
require_once("../../model/don_hang.php"); // Đảm bảo bạn đã có file này với lớp DonHang
$db = new Database();
$conn = $db->connect();
$donHang = new DonHang($conn);
$data = json_decode(file_get_contents("php://input"));

$donHang->don_hang_id = $data->don_hang_id; // ID đơn hàng cần xóa

$result = $donHang->xoaDonHang();
if ($result == true) {
    echo json_encode(array("message" => "Xóa đơn hàng thành công"));
} else {
    echo json_encode(array("message" => "Xóa đơn hàng thất bại"));
}
?>