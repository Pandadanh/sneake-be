package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_nhomquyen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface nhomquyenRepositories  extends JpaRepository<tbl_nhomquyen,String> {



    @Query(value = "SELECT * FROM tbl_nhomquyen p" +
        " WHERE (:search IS NULL OR p.nhomquyen LIKE CONCAT('%', :search, '%')) " +
        "AND daxoa <> 1",
        nativeQuery = true)
    Page<tbl_nhomquyen> findPagedNhomquyen(String search, Pageable pageRequest);
}
