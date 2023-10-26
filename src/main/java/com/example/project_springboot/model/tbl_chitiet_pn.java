package com.example.project_springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_chitiet_pn")
public class tbl_chitiet_pn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ctpn")
    private int idCtpn;

    @ManyToOne
    @JoinColumn(name = "id_pn")
    private tbl_phieunhap phieunhap;

    @ManyToOne
    @JoinColumn(name = "id_pro")
    private tbl_product product;

    @ManyToOne
    @JoinColumn(name = "id_nh")
    private tbl_nhanhieu nhanhieu;

    @ManyToOne
    @JoinColumn(name = "id_dm")
    private tbl_danhmuc danhmuc;

    @Column(name = "dongia")
    private int dongia;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private tbl_size size;

    @Column(name = "soluong")
    private String soluong;

    // Constructors, getters, setters

    public tbl_chitiet_pn() {

    }

    public tbl_chitiet_pn(tbl_phieunhap phieunhap, tbl_product product, tbl_nhanhieu nhanhieu,
        tbl_danhmuc danhmuc, int dongia, tbl_size size, String soluong) {
        this.phieunhap = phieunhap;
        this.product = product;
        this.nhanhieu = nhanhieu;
        this.danhmuc = danhmuc;
        this.dongia = dongia;
        this.size = size;
        this.soluong = soluong;
    }

    public tbl_chitiet_pn(int idCtpn, tbl_phieunhap phieunhap, tbl_product product,
        tbl_nhanhieu nhanhieu, tbl_danhmuc danhmuc, int dongia, tbl_size size, String soluong) {
        this.idCtpn = idCtpn;
        this.phieunhap = phieunhap;
        this.product = product;
        this.nhanhieu = nhanhieu;
        this.danhmuc = danhmuc;
        this.dongia = dongia;
        this.size = size;
        this.soluong = soluong;
    }

    public int getIdCtpn() {
        return idCtpn;
    }

    public void setIdCtpn(int idCtpn) {
        this.idCtpn = idCtpn;
    }

    public tbl_phieunhap getPhieunhap() {
        return phieunhap;
    }

    public void setPhieunhap(tbl_phieunhap phieunhap) {
        this.phieunhap = phieunhap;
    }

    public tbl_product getProduct() {
        return product;
    }

    public void setProduct(tbl_product product) {
        this.product = product;
    }

    public tbl_nhanhieu getNhanhieu() {
        return nhanhieu;
    }

    public void setNhanhieu(tbl_nhanhieu nhanhieu) {
        this.nhanhieu = nhanhieu;
    }

    public tbl_danhmuc getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(tbl_danhmuc danhmuc) {
        this.danhmuc = danhmuc;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public tbl_size getSize() {
        return size;
    }

    public void setSize(tbl_size size) {
        this.size = size;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
