package com.example.project_springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_nhacungcap")
public class tbl_nhacungcap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ncc")
    private int idNcc;

    @Column(name = "ten_ncc")
    private String tenNcc;

    @Column(name = "daxoa")
    private int daxoa;

    public tbl_nhacungcap() {

    }

    public tbl_nhacungcap( String tenNcc, int daxoa) {
        this.tenNcc = tenNcc;
        this.daxoa = daxoa;
    }
    public tbl_nhacungcap(int idNcc, String tenNcc, int daxoa) {
        this.idNcc = idNcc;
        this.tenNcc = tenNcc;
        this.daxoa = daxoa;
    }

    public int getDaxoa() {
        return daxoa;
    }

    public void setDaxoa(int daxoa) {
        this.daxoa = daxoa;
    }

    public int getId() {
        return idNcc;
    }

    public void setId(int id) {
        this.idNcc= idNcc;
    }

    public String getTenNcc() {
        return tenNcc;
    }

    public void setTenNcc(String tenNcc) {
        this.tenNcc = tenNcc;
    }
}
