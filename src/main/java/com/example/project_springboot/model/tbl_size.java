package com.example.project_springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_size")
public class tbl_size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_size")
    private int idSize;

    @Column(name = "size")
    private String size;

    @Column(name = "daxoa")
    private int daxoa;

//    @ManyToMany(mappedBy = "sizes")
//    private Set<tbl_product> products;
//
//    public Set<tbl_product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(Set<tbl_product> products) {
//        this.products = products;
//    }

    public tbl_size() {

    }

    public tbl_size(int idSize, String size, int daxoa) {
        this.idSize = idSize;
        this.size = size;
        this.daxoa = daxoa;
    }

    public tbl_size( String size, int daxoa) {
        this.idSize = idSize;
        this.size = size;
        this.daxoa = daxoa;
    }

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getDaxoa() {
        return daxoa;
    }

    public void setDaxoa(int     daxoa) {
        this.daxoa = daxoa;
    }
}
