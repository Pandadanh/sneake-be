package com.example.project_springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_sliders")
public class tbl_silders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sliders")
    private int idSliders;

    @Column(name = "photo")
    private String photo;

    public tbl_silders() {
    }

    public tbl_silders(int id,String photo) {
        this.idSliders =id;
        this.photo = photo;
    }

    public tbl_silders(String photo) {
        this.photo = photo;
    }

    public int getId_sliders() {
        return idSliders;
    }

    public void setId_sliders(int id_sliders) {
        this.idSliders = id_sliders;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
