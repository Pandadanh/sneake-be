package com.example.project_springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_nhomquyen")
public class tbl_nhomquyen {

    @Id
    @Column(name = "nhomquyen")
    private String nhomquyen;

    @Column(name = "daxoa")
    private int daxoa;

    public tbl_nhomquyen() {

    }

    public tbl_nhomquyen(String nhomquyen, int daxoa) {
        this.nhomquyen = nhomquyen;
        this.daxoa = daxoa;
    }

    public String getnhomquyen() {
        return nhomquyen;
    }

    public void setId(String id) {
        this.nhomquyen = id;
    }

    public int getDaxoa() {
        return daxoa;
    }

    public void setDaxoa(int daxoa) {
        this.daxoa = daxoa;
    }
}
