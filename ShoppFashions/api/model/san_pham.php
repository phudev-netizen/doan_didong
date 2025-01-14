<?php
class SanPham {
    private $conn;

    public $san_pham_id;
    public $ten_san_pham;
    public $mo_ta;
    public $gia;
    public $so_luong_sp;
    public $danh_muc_id;
    public $mau_sac;
    public $kich_thuoc;
    public $hinh_anh;

    public function __construct($db) {
        $this->conn = $db;
    }

    public function layDanhSachSanPham() {
        $sql = "SELECT * FROM San_pham";
        return $this->conn->query($sql);
    }

    public function themSanPham() {
        $sql = "INSERT INTO San_pham (Ten_san_pham, Mo_ta, Gia, Soluong_SP, Danh_muc_ID, Mau_sac, Kich_thuoc, Hinh_anh)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("ssdiisss", $this->ten_san_pham, $this->mo_ta, $this->gia, $this->so_luong_sp, $this->danh_muc_id, $this->mau_sac, $this->kich_thuoc, $this->hinh_anh);
        return $stmt->execute();
    }

    public function xoaSanPham() {
        $sql = "DELETE FROM San_pham WHERE San_pham_ID = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("i", $this->san_pham_id);
        return $stmt->execute();
    }
}
?>
