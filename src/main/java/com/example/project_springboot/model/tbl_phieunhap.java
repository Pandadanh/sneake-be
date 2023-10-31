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
@Table(name = "tbl_phieunhap")
public class tbl_phieunhap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pn")
    private int idPn;

    @ManyToOne
    @JoinColumn(name = "id_nv")
    private tbl_users nhanvien ;

    @Column(name = "tongtien")
    private String tongTien;

    @Column(name = "ngaynhap")
    private LocalDate ngaynhap;

    @Column(name = "tongsl")
    private int tongsl;

    @Column(name = "tinhtrang")
    private int tinhtrang;

    @ManyToOne
    @JoinColumn(name = "id_ncc")
    private tbl_nhacungcap nhacungcap ;
    public tbl_phieunhap() {

    }

    public tbl_phieunhap(tbl_users nhanvien, String tongTien, LocalDate ngaynhap, int tongsl,
        int tinhtrang , tbl_nhacungcap nhacungcap) {
        this.nhanvien = nhanvien;
        this.tongTien = tongTien;
        this.ngaynhap = ngaynhap;
        this.tongsl = tongsl;
        this.tinhtrang = tinhtrang;
        this.nhacungcap = nhacungcap;
    }

    public tbl_phieunhap(int idPn, tbl_users nhanvien, String tongTien, LocalDate ngaynhap,
        int tongsl,
        int tinhtrang ,tbl_nhacungcap nhacungcap) {
        this.idPn = idPn;
        this.nhanvien = nhanvien;
        this.tongTien = tongTien;
        this.ngaynhap = ngaynhap;
        this.tongsl = tongsl;
        this.tinhtrang = tinhtrang;
        this.nhacungcap = nhacungcap;
    }

    public tbl_nhacungcap getNhacungcap() {
        return nhacungcap;
    }

    public void setNhacungcap(tbl_nhacungcap nhacungcap) {
        this.nhacungcap = nhacungcap;
    }

    public int getIdPn() {
        return idPn;
    }

    public void setIdPn(int idPn) {
        this.idPn = idPn;
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

    public LocalDate getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(LocalDate ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public int getTongsl() {
        return tongsl;
    }

    public void setTongsl(int tongsl) {
        this.tongsl = tongsl;
    }

    public int getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(int tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
}
