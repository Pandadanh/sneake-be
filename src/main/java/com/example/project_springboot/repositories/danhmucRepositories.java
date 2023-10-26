package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_danhmuc;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface danhmucRepositories  extends JpaRepository<tbl_danhmuc,Integer> {

    tbl_danhmuc findByIdDm(int idDm);

    @Query(value = "SELECT * FROM tbl_danhmuc WHERE daxoa <> 1", nativeQuery = true)
    List<tbl_danhmuc> findActiveDanhMuc();

    @Query(value = "SELECT tbl_danhmuc.* FROM tbl_danhmuc  WHERE daxoa <> 1 AND (id_dm LIKE %:search% OR danhmuc LIKE %:search%)", nativeQuery = true)
    List<tbl_danhmuc> findDanhMucBySearch(@Param("search") String search);

    @Query(value = "SELECT tbl_danhmuc.* FROM tbl_danhmuc  WHERE daxoa <> 1 AND danhmuc REGEXP :search", nativeQuery = true)
    List<tbl_danhmuc> findDanhMucByRegexp(@Param("search") String search);

    @Query(value = "SELECT * FROM tbl_danhmuc p" +
        " WHERE (:search IS NULL OR p.danhmuc LIKE CONCAT('%', :search, '%')) " +
        "AND (:iddmStr IS NULL OR p.id_dm = :iddmStr)",
        nativeQuery = true)
    Page<tbl_danhmuc> findPagedDanhmuc(String search, Integer iddmStr, Pageable pageRequest);

}

