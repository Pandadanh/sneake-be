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
@Table(name = "tbl_img_product")
public class tbl_img_product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imgpro")
    private int idImgpro;

    @ManyToOne
    @JoinColumn(name = "id_pro")
    private tbl_product product;

    @Column(name = "src_img")
    private String srcImg;

    public tbl_img_product() {

    }

    public tbl_img_product(tbl_product product, String srcImg) {
        this.product = product;
        this.srcImg = srcImg;
    }

    public tbl_img_product(int idImgpro, tbl_product product, String srcImg) {
        this.idImgpro = idImgpro;
        this.product = product;
        this.srcImg = srcImg;
    }

    public int getIdImgpro() {
        return idImgpro;
    }

    public void setIdImgpro(int idImgpro) {
        this.idImgpro = idImgpro;
    }

    public tbl_product getProduct() {
        return product;
    }

    public void setProduct(tbl_product product) {
        this.product = product;
    }

    public String getSrcImg() {
        return srcImg;
    }

    public void setSrcImg(String srcImg) {
        this.srcImg = srcImg;
    }
}
