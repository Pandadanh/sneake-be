package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_silders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface sildersRepositories extends JpaRepository<tbl_silders,Integer> {

    @Query(value = "SELECT * FROM tbl_sliders ",nativeQuery = true)
    Page<tbl_silders> findPagedSilders(PageRequest pageRequest);
}
