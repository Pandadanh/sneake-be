package com.example.project_springboot.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_phieuxuat")
public class tbl_phieuxuat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_px")
    private int idPx;

    @ManyToOne
    @JoinColumn(name = "id_kh")
    private tbl_users khachHang;

    @ManyToOne
    @JoinColumn(name = "id_nv")
    private tbl_users nhanvien ;

    private String tongTien;

    @Column(name = "ngaydat")
    private LocalDate ngayDat;

    @Column(name = "daxoa")
    private String daXoa;

    @Column(name = "tongsl")
    private int tongSoLuong;

    @Column(name = "trangthai")
    private int trangThai;

    public tbl_phieuxuat() {

    }


    public tbl_phieuxuat(tbl_users khachHang, tbl_users nhanvien, String tongTien,
        LocalDate ngayDat,
        String daXoa, int tongSoLuong, int trangThai) {
        this.khachHang = khachHang;
        this.nhanvien = nhanvien;
        this.tongTien = tongTien;
        this.ngayDat = ngayDat;
        this.daXoa = daXoa;
        this.tongSoLuong = tongSoLuong;
        this.trangThai = trangThai;
    }

    public tbl_phieuxuat(int idPx, tbl_users khachHang, tbl_users nhanvien, String tongTien,
        LocalDate ngayDat, String daXoa, int tongSoLuong, int trangThai) {
        this.idPx = idPx;
        this.khachHang = khachHang;
        this.nhanvien = nhanvien;
        this.tongTien = tongTien;
        this.ngayDat = ngayDat;
        this.daXoa = daXoa;
        this.tongSoLuong = tongSoLuong;
        this.trangThai = trangThai;
    }

    public int getIdPx() {
        return idPx;
    }

    public void setIdPx(int idPx) {
        this.idPx = idPx;
    }

    public tbl_users getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(tbl_users khachHang) {
        this.khachHang = khachHang;
    }

    public tbl_users getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(tbl_users nhanvien) {
        this.nhanvien = nhanvien;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public LocalDate getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDate ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getDaXoa() {
        return daXoa;
    }

    public void setDaXoa(String daXoa) {
        this.daXoa = daXoa;
    }

    public int getTongSoLuong() {
        return tongSoLuong;
    }

    public void setTongSoLuong(int tongSoLuong) {
        this.tongSoLuong = tongSoLuong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
