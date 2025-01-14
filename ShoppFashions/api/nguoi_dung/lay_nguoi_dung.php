<?php
header("Content-Type: application/json");
require_once("../../config/db.php");
require_once("../../model/nguoi_dung.php");

$db = new Database();
$conn = $db->connect();

$nguoiDung = new NguoiDung($conn);
$result = $nguoiDung->layDanhSachNguoiDung();

if ($result->num_rows > 0) {
    $nguoi_dung_arr = [];
    while ($row = $result->fetch_assoc()) {
        $nguoi_dung_arr[] = $row;
    }
    echo json_encode($nguoi_dung_arr);
} else {
    echo json_encode(["message" => "Không có dữ liệu."]);
}
?>
