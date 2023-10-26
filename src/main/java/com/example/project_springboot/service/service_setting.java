package com.example.project_springboot.service;

import com.example.project_springboot.model.tbl_setting;
import com.example.project_springboot.repositories.settingRepositories;
import com.example.project_springboot.service.rules.rules_service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class service_setting implements rules_service<tbl_setting> {

    @Autowired
    private settingRepositories settingRepositories;
    @Override
    public List<tbl_setting> showall() {
        return settingRepositories.findAll();
    }

    public void save(tbl_setting avc) {
        settingRepositories.save(avc);
    }
}
