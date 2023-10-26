package com.example.project_springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_nhanhieu")
public class tbl_nhanhieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nh")
    private int idNh;

    @Column(name = "nhanhieu")
    private String nhanhieu;

    @Column(name = "daxoa")
    private int daxoa;

    public tbl_nhanhieu(){

    }


    public tbl_nhanhieu(String nhanhieu, int daxoa) {
        this.nhanhieu = nhanhieu;
        this.daxoa = daxoa;
    }

    public tbl_nhanhieu(int id_nh, String nhanhieu, int daxoa) {
        this.idNh = id_nh;
        this.nhanhieu = nhanhieu;
        this.daxoa = daxoa;
    }

    public int getId_nh() {
        return idNh;
    }

    public void setId_nh(int id_nh) {
        this.idNh = id_nh;
    }

    public String getNhanhieu() {
        return nhanhieu;
    }

    public void setNhanhieu(String nhanhieu) {
        this.nhanhieu = nhanhieu;
    }

    public int getDaxoa() {
        return daxoa;
    }

    public void setDaxoa(int daxoa) {
        this.daxoa = daxoa;
    }
}
