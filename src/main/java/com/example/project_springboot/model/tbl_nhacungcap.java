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
    private int id;

    @Column(name = "ten_ncc")
    private String tenNcc;

    public tbl_nhacungcap() {

    }

    public tbl_nhacungcap(int id, String tenNcc) {
        this.id = id;
        this.tenNcc = tenNcc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNcc() {
        return tenNcc;
    }

    public void setTenNcc(String tenNcc) {
        this.tenNcc = tenNcc;
    }
}
