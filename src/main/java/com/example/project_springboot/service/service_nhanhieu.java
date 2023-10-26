package com.example.project_springboot.service;

import com.example.project_springboot.model.tbl_nhanhieu;
import com.example.project_springboot.repositories.nhanhieuRepositories;
import com.example.project_springboot.service.rules.rules_service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
public class service_nhanhieu implements rules_service<tbl_nhanhieu> {

    @Autowired
    private nhanhieuRepositories nhanhieuRepositories;

    public service_nhanhieu(){
    }

    public List<tbl_nhanhieu> showall(){
        return nhanhieuRepositories.findAll();
    }

    public tbl_nhanhieu findById(int id){
        return (tbl_nhanhieu) nhanhieuRepositories.findByIdNh(id);
    }

    public int countall() {return  nhanhieuRepositories.findAll().size();    }

    public Page<tbl_nhanhieu> findPagedNhanhieu(String search, Integer idnhStr, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size); // Vì trang tính từ 0, nên trừ đi 1
        return nhanhieuRepositories.findPagedNhanhieu(search, idnhStr, pageRequest);
    }

    public boolean add(tbl_nhanhieu danhmucnew) {
        try {
            nhanhieuRepositories.save(danhmucnew);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
