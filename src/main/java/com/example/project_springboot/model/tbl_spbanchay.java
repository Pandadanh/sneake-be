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
@Table(name = "tbl_spbanchay")
public class tbl_spbanchay {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_spbanchay")
    private int idSpbanchay;

    @ManyToOne
    @JoinColumn(name = "id_pro")
    private tbl_product product;

    @Column(name = "sldaban")
    private int sldaban;


    public tbl_spbanchay() {

    }

    public tbl_spbanchay(int idSpbanchay, tbl_product product, int sldaban) {
        this.idSpbanchay = idSpbanchay;
        this.product = product;
        this.sldaban = sldaban;
    }

    public int getIdSpbanchay() {
        return idSpbanchay;
    }

    public void setIdSpbanchay(int idSpbanchay) {
        this.idSpbanchay = idSpbanchay;
    }

    public tbl_product getProduct() {
        return product;
    }

    public void setProduct(tbl_product product) {
        this.product = product;
    }

    public int getSldaban() {
        return sldaban;
    }

    public void setSldaban(int sldaban) {
        this.sldaban = sldaban;
    }
}
