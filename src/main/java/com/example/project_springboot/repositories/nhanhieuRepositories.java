
package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_nhanhieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface nhanhieuRepositories extends JpaRepository<tbl_nhanhieu,Integer> {

    tbl_nhanhieu findByIdNh(int idNh);

    @Query(value = "SELECT * FROM tbl_nhanhieu p" +
        " WHERE (:search IS NULL OR p.nhanhieu LIKE CONCAT('%', :search, '%')) " +
        "AND (:idnhStr IS NULL OR p.id_nh = :idnhStr)" +
        "AND  daxoa <> 1",
        nativeQuery = true)
    Page<tbl_nhanhieu> findPagedNhanhieu(String search, Integer idnhStr, Pageable pageRequest);
}