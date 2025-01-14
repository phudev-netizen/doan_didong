<?php
header("Content-Type: application/json; charset=UTF-8");

// Cấu hình kết nối cơ sở dữ liệu
$serverName = "localhost";
$connectionOptions = [
    "Database" => "ShopFA",
    "Uid" => "sa",
    "PWD" => "123",
    "CharacterSet" => "UTF-8"
];

// Kết nối cơ sở dữ liệu
$conn = sqlsrv_connect($serverName, $connectionOptions);
if ($conn === false) {
    echo json_encode(["status" => "error", "message" => "Không thể kết nối cơ sở dữ liệu"]);
    exit;
}

// Truy vấn danh sách danh mục
$sql = "SELECT Danh_muc_ID, Ten_danh_muc, Mo_ta_danh_muc FROM Danh_muc"; // Chỉnh sửa tên bảng và cột nếu cần
$stmt = sqlsrv_query($conn, $sql);
if ($stmt === false) {
    echo json_encode(["status" => "error", "message" => "Lỗi truy vấn"]);
    exit;
}

// Lấy dữ liệu danh mục
$categories = [];
while ($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)) {
    $categories[] = $row;
}

// Trả kết quả dưới dạng JSON
echo json_encode(["status" => "success", "data" => $categories]);

// Đóng kết nối
sqlsrv_close($conn);
?>