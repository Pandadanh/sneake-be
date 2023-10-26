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
@Table(name = "tbl_phanquyen")
public class tbl_phanquyen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "nhomquyen")
    private tbl_nhomquyen nhomquyen;

    @ManyToOne
    @JoinColumn(name = "quyen")
    private tbl_quyen quyen;

    public tbl_phanquyen() {

    }

    public tbl_phanquyen(tbl_nhomquyen nhomquyen, tbl_quyen quyen) {
        this.nhomquyen = nhomquyen;
        this.quyen = quyen;
    }

    public tbl_phanquyen(int id, tbl_nhomquyen nhomquyen, tbl_quyen quyen) {
        this.id = id;
        this.nhomquyen = nhomquyen;
        this.quyen = quyen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public tbl_nhomquyen getNhomquyen() {
        return nhomquyen;
    }

    public void setNhomquyen(tbl_nhomquyen nhomquyen) {
        this.nhomquyen = nhomquyen;
    }

    public tbl_quyen getQuyen() {
        return quyen;
    }

    public void setQuyen(tbl_quyen quyen) {
        this.quyen = quyen;
    }
}
