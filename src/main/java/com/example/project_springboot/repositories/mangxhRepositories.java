package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_mangxh;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface mangxhRepositories  extends JpaRepository<tbl_mangxh,Integer> {
    @Query(value = "SELECT * FROM tbl_mangxh", nativeQuery = true)
    List<tbl_mangxh> findAllCustomQuery();
}
