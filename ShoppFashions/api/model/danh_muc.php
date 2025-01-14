<?php
class DanhMuc {
    private $conn;

    public $danh_muc_id;
    public $ten_danh_muc;
    public $mo_ta_danh_muc;

    public function __construct($db) {
        $this->conn = $db;
    }

    // Method to fetch all categories
    public function layDanhSachDanhMuc() {
        $sql = "SELECT * FROM Danh_muc";
        return $this->conn->query($sql);
    }
}
?>