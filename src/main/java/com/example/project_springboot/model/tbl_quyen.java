package com.example.project_springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_quyen")
public class tbl_quyen {

    @Id
    @Column(name = "quyen")
    private String quyen;

    @Column(name = "daxoa")
    private String daxoa;

    public tbl_quyen() {

    }

    public tbl_quyen(String quyen, String daxoa) {
        this.quyen = quyen;
        this.daxoa = daxoa;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public String getDaxoa() {
        return daxoa;
    }

    public void setDaxoa(String daxoa) {
        this.daxoa = daxoa;
    }
}
