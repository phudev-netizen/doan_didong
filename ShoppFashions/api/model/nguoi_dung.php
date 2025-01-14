<?php
class NguoiDung {
    private $conn;
    
    public $user_id;
    public $username;
    public $email;
    public $mat_khau;
    public $vai_tro;
    public $ngay_dang_ky;
    public $trang_thai;

    public function __construct($db) {
        $this->conn = $db;
    }

    public function layDanhSachNguoiDung() {
        $sql = "SELECT * FROM Nguoi_dung";
        return $this->conn->query($sql);
    }

    public function themNguoiDung() {
        $sql = "INSERT INTO Nguoi_dung (Username, Email, Mat_khau, Vai_tro) VALUES (?, ?, ?, ?)";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("ssss", $this->username, $this->email, $this->mat_khau, $this->vai_tro);
        return $stmt->execute();
    }

    public function xoaNguoiDung() {
        $sql = "DELETE FROM Nguoi_dung WHERE User_ID = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("i", $this->user_id);
        return $stmt->execute();
    }
}
?>
