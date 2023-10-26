package com.example.project_springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_pro_soluong")
public class tbl_pro_soluong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pro_soluong")
    private int idProSoluong;

    @ManyToOne
    @JoinColumn(name = "id_pro")
    private tbl_product product;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private tbl_size size;

    @Column(name = "soluong")
    private int soLuong;

    public tbl_pro_soluong( tbl_product product, tbl_size size, int soLuong) {
        this.product = product;
        this.size = size;
        this.soLuong = soLuong;
    }

    public int getIdProSoluong() {
        return idProSoluong;
    }

    public  tbl_pro_soluong(){

    }

    public void setIdProSoluong(int idProSoluong) {
        this.idProSoluong = idProSoluong;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
