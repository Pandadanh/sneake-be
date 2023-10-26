package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_phieunhap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface phieunhapRepositories extends JpaRepository<tbl_phieunhap, Integer> {
    tbl_phieunhap save(tbl_phieunhap tblPhieunhap);
}