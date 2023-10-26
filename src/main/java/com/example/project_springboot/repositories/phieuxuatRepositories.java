package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_phieuxuat;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface phieuxuatRepositories extends JpaRepository<tbl_phieuxuat,Integer> {

    List<tbl_phieuxuat> findPhieuXuatByKhachHangIdUserOrderByNgayDatDesc(int idKh);


    tbl_phieuxuat save(tbl_phieuxuat tblPhieuxuat);

    @Query(value = "SELECT * FROM tbl_phieuxuat p WHERE ngaydat >= :ngaymin AND ngaydat <= :ngaymax AND (:tinhtrang IS NULL OR p.trangthai REGEXP :tinhtrang)",
        nativeQuery = true)
    Page<tbl_phieuxuat> findPagedphieuxuat(@Param("ngaymin") String ngaymin, @Param("ngaymax") String ngaymax, @Param("tinhtrang") String tinhtrang, Pageable pageable);


    @Query(value = "SELECT p FROM tbl_phieuxuat p " +
        "WHERE ngaydat >= :ngaymin " +
        "AND ngaydat <= :ngaymax " )
    List<tbl_phieuxuat> findPagedphieuxuatcaocap(
        @Param("ngaymin") String ngaymin,
        @Param("ngaymax") String ngaymax,
        Pageable pageRequest);
}