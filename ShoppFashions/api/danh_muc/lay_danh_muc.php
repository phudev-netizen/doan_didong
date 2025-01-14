<?php
header("Content-Type: application/json");
require_once("../../config/db.php");

require_once("../../model/danh_muc.php"); // Đảm bảo bạn đã có file này với lớp DanhMuc
$db = new Database();
$conn = $db->connect();
$danhMuc = new DanhMuc($conn);

$result = $danhMuc->layDanhSachDanhMuc();
$danh_muc_array = array();

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $danh_muc_array[] = $row;
    }
    echo json_encode(array("danh_muc" => $danh_muc_array));
} else {
    echo json_encode(array("message" => "Không tìm thấy danh mục"));
}
?>