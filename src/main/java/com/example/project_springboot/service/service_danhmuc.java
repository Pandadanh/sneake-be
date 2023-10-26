package com.example.project_springboot.service;

import com.example.project_springboot.model.tbl_danhmuc;
import com.example.project_springboot.repositories.danhmucRepositories;
import com.example.project_springboot.service.rules.rules_service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
public class service_danhmuc implements rules_service<tbl_danhmuc> {

    @Autowired
    private danhmucRepositories danhmucRepositories;

    public service_danhmuc(){
    }

    public List<tbl_danhmuc> showall(){
        return danhmucRepositories.findAll();
    }

    public tbl_danhmuc findById(int id){
        return danhmucRepositories.findByIdDm(id);
    }

    public List<tbl_danhmuc> findActiveDanhMuc() {
        return danhmucRepositories.findActiveDanhMuc();
    }

    public List<tbl_danhmuc> findDanhMucBySearch(String search) {
        return danhmucRepositories.findDanhMucBySearch(search);
    }


    public List<tbl_danhmuc> findDanhMucByRegexp(String search) {
        return danhmucRepositories.findDanhMucByRegexp(search);
    }
    public int countall() {return danhmucRepositories.findAll().size();    }


    public Page<tbl_danhmuc> findPagedDanhmuc(String search, Integer iddmStr, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size); // Vì trang tính từ 0, nên trừ đi 1
        return danhmucRepositories.findPagedDanhmuc(search, iddmStr, pageRequest);
    }
    public boolean add (tbl_danhmuc danhmuc){
        try {
            danhmucRepositories.save(danhmuc);
            return true;
        }catch ( Exception e){
            return false;
        }
    }
}
