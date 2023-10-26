package com.example.project_springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_danhmuc")
public class tbl_danhmuc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dm")
    private int idDm;

    @Column(name = "danhmuc")
    private String danhMuc;

    @Column(name = "daxoa")
    private int daXoa;

    public  tbl_danhmuc(){

    }
    public tbl_danhmuc(int idDm, String danhMuc, int daXoa) {
        this.idDm = idDm;
        this.danhMuc = danhMuc;
        this.daXoa = daXoa;
    }

    public tbl_danhmuc( String danhMuc, int daXoa) {
        this.danhMuc = danhMuc;
        this.daXoa = daXoa;
    }

    public int getIdDm() {
        return idDm;
    }

    public void setIdDm(int idDm) {
        this.idDm = idDm;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public int isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(int daXoa) {
        this.daXoa = daXoa;
    }
// Constructors, getters và setters
}
