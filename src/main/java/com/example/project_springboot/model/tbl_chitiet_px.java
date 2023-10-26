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
    @Table(name = "tbl_chitiet_px")
    public class tbl_chitiet_px {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_chitiet_px")
        private int idChitietPx;

        @ManyToOne
        @JoinColumn(name = "id_px")
        private tbl_phieuxuat phieuxuat;

        @ManyToOne
        @JoinColumn(name = "id_pro")
        private tbl_product product;

        @ManyToOne
        @JoinColumn(name = "id_size")
        private tbl_size size;

        @Column(name = "soluong")
        private String soLuong;

        @Column(name = "giaban")
        private int giaban;

    public tbl_chitiet_px() {

    }

    public tbl_chitiet_px(tbl_phieuxuat phieuxuat, tbl_product product, tbl_size size,
        String soLuong,
        int giaban) {
        this.phieuxuat = phieuxuat;
        this.product = product;
        this.size = size;
        this.soLuong = soLuong;
        this.giaban = giaban;
    }

    public tbl_chitiet_px(int idChitietPx, tbl_phieuxuat phieuxuat, tbl_product product,
        tbl_size size,
        String soLuong, int giaban) {
        this.idChitietPx = idChitietPx;
        this.phieuxuat = phieuxuat;
        this.product = product;
        this.size = size;
        this.soLuong = soLuong;
        this.giaban = giaban;
    }

    public int getIdChitietPx() {
        return idChitietPx;
    }

    public void setIdChitietPx(int idChitietPx) {
        this.idChitietPx = idChitietPx;
    }

    public tbl_phieuxuat getPhieuxuat() {
        return phieuxuat;
    }

    public void setPhieuxuat(tbl_phieuxuat phieuxuat) {
        this.phieuxuat = phieuxuat;
    }

    public tbl_product getProduct() {
        return product;
    }

    public void setProduct(tbl_product product) {
        this.product = product;
    }

    public tbl_size getSize() {
        return size;
    }

    public void setSize(tbl_size size) {
        this.size = size;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }
}
