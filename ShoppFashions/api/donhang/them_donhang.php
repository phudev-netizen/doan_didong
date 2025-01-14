<?php
header("Content-Type: application/json");
require_once("../../config/db.php");
require_once("../../model/don_hang.php"); // Đảm bảo bạn đã có file này với lớp DonHang
$db = new Database();
$conn = $db->connect();
$donHang = new DonHang($conn);
$data = json_decode(file_get_contents("php://input"));

$donHang->khach_hang_id = $data->khach_hang_id;
$donHang->ngay_dat_hang = $data->ngay_dat_hang;
$donHang->tong_tien = $data->tong_tien;
$donHang->trang_thai = $data->trang_thai;

$result = $donHang->themDonHang();
if ($result == true) {
    echo json_encode(array("message" => "Thêm đơn hàng thành công"));
} else {
    echo json_encode(array("message" => "Thêm đơn hàng thất bại"));
}
?>