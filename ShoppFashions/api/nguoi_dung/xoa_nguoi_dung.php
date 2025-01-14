<?php
header("Content-Type: application/json");
require_once("../../config/db.php");
require_once("../../model/nguoi_dung.php");

$db = new Database();
$conn = $db->connect();

$data = json_decode(file_get_contents("php://input"));
$nguoiDung = new NguoiDung($conn);

$nguoiDung->user_id = $data->user_id;

if ($nguoiDung->xoaNguoiDung()) {
    echo json_encode(["message" => "Xóa người dùng thành công."]);
} else {
    echo json_encode(["message" => "Xóa người dùng thất bại."]);
}
?>
