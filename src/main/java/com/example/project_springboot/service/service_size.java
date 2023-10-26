package com.example.project_springboot.service;

import com.example.project_springboot.model.tbl_size;
import com.example.project_springboot.repositories.sizeRepositories;
import com.example.project_springboot.service.rules.rules_service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class service_size implements rules_service<tbl_size> {


    @Autowired
    private sizeRepositories sizeRepositories;

    public service_size(){
    }

    public Long count(){
        return sizeRepositories.count();
    }
    public List<tbl_size> showall(){
        return sizeRepositories.findAll();
    }

    public List<tbl_size> List_all_findsizefindid(List<Integer> a){
        return sizeRepositories.findByIdSize(a);
    }

    public tbl_size find_size_by_id(int idsize){
        return sizeRepositories.findById(idsize);
    }

    public List<tbl_size> findAllByDaxoaNot() {
        return sizeRepositories.findAllByDaxoaNot(1);
    }

    public List<tbl_size> findAllByDaxoaNotAndIdSizeOrSizeContaining(int i, String search, String search1) {
        return sizeRepositories.findAllByDaxoaNotAndIdSizeOrSizeContaining(1,
            Integer.parseInt(search), search);

    }

    public List<tbl_size> findPagedSizes(int size, int offset) {
        return sizeRepositories.findPagedSizes(size, offset);
    }

    public List<tbl_size> findPagedSizesBySearch(int size, int offset, String search) {
        return sizeRepositories.findPagedSizesBySearch(search, size, offset);
    }

    public int countSizesBySearch(String search) {
        return sizeRepositories.countSizesBySearch(search);
    }

    public String delete_size(int id) {
        String error="";
       try{
           sizeRepositories.deleteById(id);
       }catch (Exception e){
           error = "Fail";
       }
       return error;
    }

    public String soft_delete_size(int id){
        try {
            // Kiểm tra xem id có tồn tại trong cơ sở dữ liệu không
            tbl_size sizeOptional = sizeRepositories.findById(id);
            if (!sizeOptional.getSize().isEmpty()) {
                return "Không tìm thấy kích thước có id = " + id;
            }

            // Đặt trạng thái của size thành đã xóa (ví dụ: daxoa = 1)
            sizeOptional.setDaxoa(1);

            // Lưu thay đổi vào cơ sở dữ liệu
            sizeRepositories.save(sizeOptional);
            return "";

        } catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi khi xóa kích thước";
        }
    };

    public boolean add(tbl_size danhmucnew) {
       try {
           sizeRepositories.save(danhmucnew);
           return true;
       }catch (Exception e){
           return false;
       }
    }

    public tbl_size findById(int id) {
        return sizeRepositories.findById(id);
    }

    public tbl_size findbySize(String size) {
        return sizeRepositories.findBySize(size);
    }
}
