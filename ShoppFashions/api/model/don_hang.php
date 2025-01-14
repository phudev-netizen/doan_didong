<?php
class DonHang {
    private $conn;

    public $don_hang_id;
    public $khach_hang_id;
    public $ngay_dat_hang;
    public $tong_tien;
    public $trang_thai;

    public function __construct($db) {
        $this->conn = $db;
    }

    // Method to fetch all orders
    public function layDanhSachDonHang() {
        $sql = "SELECT * FROM Don_hang";
        return $this->conn->query($sql);
    }

    // Method to add a new order
    public function themDonHang() {
        $sql = "INSERT INTO Don_hang (Khach_hang_ID, Ngay_dat_hang, Tong_tien, Trang_thai)
                VALUES (?, ?, ?, ?)";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("isds", $this->khach_hang_id, $this->ngay_dat_hang, $this->tong_tien, $this->trang_thai);
        return $stmt->execute();
    }

    // Method to delete an order
    public function xoaDonHang() {
        $sql = "DELETE FROM Don_hang WHERE Don_hang_ID = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("i", $this->don_hang_id);
        return $stmt->execute();
    }
}
?>
