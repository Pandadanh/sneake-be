package com.example.project_springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_mangxh")
public class tbl_mangxh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tenmxh")
    private String tenmxh;

    @Column(name = "url")

    private String url;

    public  tbl_mangxh(){

    }
    public tbl_mangxh(int id, String tenmxh, String url) {
        this.id = id;
        this.tenmxh = tenmxh;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenmxh() {
        return tenmxh;
    }

    public void setTenmxh(String tenmxh) {
        this.tenmxh = tenmxh;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
