package com.example.project_springboot.service;

import com.example.project_springboot.model.tbl_nhacungcap;
import com.example.project_springboot.repositories.nhacungcapRepositores;
import com.example.project_springboot.service.rules.rules_service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class service_nhacungcap  implements rules_service<tbl_nhacungcap> {

    @Autowired
    private nhacungcapRepositores nhacungcapRepositores;
    @Override
    public List<tbl_nhacungcap> showall() {
        return nhacungcapRepositores.findAll();
    }

    public int countall() {return  nhacungcapRepositores.findAll().size();    }

    public Page<tbl_nhacungcap> findPagedNhanhieu(String search, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size); // Vì trang tính từ 0, nên trừ đi 1
        return nhacungcapRepositores.findPagedNhanhieu(search, pageRequest);
    }

    public boolean add(tbl_nhacungcap danhmucnew) {
        try {
            nhacungcapRepositores.save(danhmucnew);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public tbl_nhacungcap findById(int id) {
        return nhacungcapRepositores.findById(id).get();
    }
}
