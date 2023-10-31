package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_nhacungcap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface nhacungcapRepositores  extends JpaRepository<tbl_nhacungcap,Integer> {
    @Query(value = "SELECT * FROM tbl_nhacungcap p" +
        " WHERE (:search IS NULL OR p.ten_ncc LIKE CONCAT('%', :search, '%')) " +
        "AND  daxoa <> 1",
        nativeQuery = true)
    Page<tbl_nhacungcap> findPagedNhanhieu(String search, Pageable pageRequest);
}
