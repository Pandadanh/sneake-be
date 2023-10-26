package com.example.project_springboot.service;

import com.example.project_springboot.model.tbl_mangxh;
import com.example.project_springboot.repositories.mangxhRepositories;
import com.example.project_springboot.service.rules.rules_service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class service_mangxh implements rules_service<tbl_mangxh> {


    @Autowired
    private mangxhRepositories mangxhRepositories ;

    @Override
    public List<tbl_mangxh> showall() {
        return mangxhRepositories.findAll();
    }

    public List<tbl_mangxh> showallbyid() {
        return mangxhRepositories.findAllCustomQuery();
    }
}
