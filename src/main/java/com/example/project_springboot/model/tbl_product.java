package com.example.project_springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_product")
public class tbl_product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pro")
    private int idPro;

    @Column(name = "ten_pro")
    private String tenPro;

    @ManyToOne
    @JoinColumn(name = "id_nh")
    private tbl_nhanhieu nhanhieu;

    @ManyToOne
    @JoinColumn(name = "id_dm")
    private tbl_danhmuc danhmuc;

    @Column(name = "giacu")
    private String giaCu;

    @Column(name = "giamoi")
    private String giaMoi;

    @Column(name = "total_view")
    private int totalView;

    @Column(name = "mota")
    private String moTa;

    @Column(name = "hinhanh")
    private String hinhAnh;

    @Column(name = "daxoa")
    private int daXoa;


    public tbl_product() {
    }

    public tbl_product(int idPro, String tenPro, tbl_nhanhieu nhanhieu, tbl_danhmuc danhmuc,
        String giaCu, String giaMoi, int totalView, String moTa, String hinhAnh, int daXoa) {
        this.idPro = idPro;
        this.tenPro = tenPro;
        this.nhanhieu = nhanhieu;
        this.danhmuc = danhmuc;
        this.giaCu = giaCu;
        this.giaMoi = giaMoi;
        this.totalView = totalView;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.daXoa = daXoa;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public String getTenPro() {
        return tenPro;
    }

    public void setTenPro(String tenPro) {
        this.tenPro = tenPro;
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

    public String getGiaCu() {
        return giaCu;
    }

    public void setGiaCu(String giaCu) {
        this.giaCu = giaCu;
    }

    public String getGiaMoi() {
        return giaMoi;
    }

    public void setGiaMoi(String giaMoi) {
        this.giaMoi = giaMoi;
    }

    public int getTotalView() {
        return totalView;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getDaXoa() {
        return daXoa;
    }

    public void setDaXoa(int daXoa) {
        this.daXoa = daXoa;
    }
}
