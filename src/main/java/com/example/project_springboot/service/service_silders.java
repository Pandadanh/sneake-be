package com.example.project_springboot.service;

import com.example.project_springboot.model.tbl_silders;
import com.example.project_springboot.repositories.sildersRepositories;
import com.example.project_springboot.service.rules.rules_service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
public class service_silders implements rules_service<tbl_silders> {

    @Autowired
    private sildersRepositories sildersRepositories;

    public service_silders(){
    }
    public List<tbl_silders> showall(){
        return sildersRepositories.findAll();
    }


    public Page<tbl_silders> findPagedSilders(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size); // Vì trang tính từ 0, nên trừ đi 1
        return sildersRepositories.findPagedSilders( pageRequest);
    }

    public int countall() {return sildersRepositories.findAll().size();    }


    public boolean add(tbl_silders danhmucnew) {
        try{
            sildersRepositories.save(danhmucnew);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean delete(tbl_silders danhmucnew) {
        try{
            sildersRepositories.delete(danhmucnew);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Optional<tbl_silders> findById(int id) {
        return sildersRepositories.findById(id);
    }
}
