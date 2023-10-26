package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface settingRepositories  extends JpaRepository<tbl_setting,Integer> {

}
