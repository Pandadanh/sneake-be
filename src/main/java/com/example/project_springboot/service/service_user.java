package com.example.project_springboot.service;

import com.example.project_springboot.model.tbl_nhomquyen;
import com.example.project_springboot.model.tbl_phanquyen;
import com.example.project_springboot.model.tbl_quyen;
import com.example.project_springboot.model.tbl_users;
import com.example.project_springboot.repositories.nhomquyenRepositories;
import com.example.project_springboot.repositories.phanquyenRepositories;
import com.example.project_springboot.repositories.quyenRepositories;
import com.example.project_springboot.repositories.userRepositories;
import com.example.project_springboot.service.rules.rules_service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class service_user implements rules_service<tbl_users> {

    @Autowired
    private userRepositories userrepositories ;

    @Autowired
    private phanquyenRepositories phanquyenRepositories ;

    @Autowired
    private nhomquyenRepositories nhomquyenRepositories ;

    @Autowired
    private quyenRepositories quyenRepositories ;


    public service_user(){

    }

    public tbl_users check_login(String email, String password){
        tbl_users user =  userrepositories.findByEmailAndMatKhau(email,password);
        return user;
    }

    public String check_sign_out(tbl_users users){
        String error ="";

        if( userrepositories.existsByIdUser(users.getIdUser())){
            error+="id : entity ";
        }
        if( userrepositories.existsByEmail(users.getEmail())){
            error+="email : entity ";

        }
        if( error.equals(""))
            userrepositories.save(users);
        else
            error+="not save";
        return error;

    }


    public String save_user(tbl_users users){
        String error ="";
        if (userrepositories.existsByEmail(users.getEmail())){
            error="email : Email da ton tai";
            return error;
        }

        if (!users.getEmail().matches("^[a-zA-Z0-9._%+-]+@gmail.com$")) {
            error +="email : Khong dung dinh dang ";
        }
        if (!users.getSoDienThoai().matches("^0\\d{9}$")) {
            error +="SDT : Khong dung dinh dang ";
        }
        if (users.getMatKhau().length() < 5) {
            error +="Passwork : Khong dung dinh dang ";
        }

        if(error.equals("")){
            tbl_nhomquyen quyen = new tbl_nhomquyen("Khách hàng",0);
            users.setNhomQuyen(quyen);
            users.setIsActive(1);
            tbl_users a= userrepositories.save(users);
            
            a.setIsActive(1);
            userrepositories.save(a);
        }



        return error;
    }
    public String updateUserActivation(String activeToken) {
        tbl_users user = userrepositories.findByActiveToken(activeToken);
        String error="";
        if (user != null) {
            tbl_nhomquyen nhomquyen = new tbl_nhomquyen("khách hàng",0);
            user.setNhomQuyen(nhomquyen);
            user.setIsActive(1);
            user.setActiveToken(null);
            userrepositories.save(user);
        }
        else error ="error";

        return error;
    }

    public boolean checkexist(int id){
        return userrepositories.existsByIdUser(id);
    }
    public tbl_users findByid(int id){ return userrepositories.findByIdUser(id);}
    @Override
    public List<tbl_users> showall() {
        return userrepositories.findAll();
    }

    public boolean findbyEmail(String newEmail) {
        return userrepositories.existsByEmail(newEmail);
    }



    public boolean updateUser(Integer userId, String tenUser, String email, String diachi, String sodth) {
        Optional<tbl_users> userOptional = userrepositories.findById(userId);
        if (userOptional.isPresent()) {
            tbl_users user = userOptional.get();
            user.setTenUser(tenUser);
            user.setEmail(email);
            user.setDiaChi(diachi);
            user.setSoDienThoai(sodth);
            user.setIsActive(1);

            userrepositories.save(user); // Lưu thay đổi vào cơ sở dữ liệu
            return true; // Trả về true nếu cập nhật thành công
        }
        return false;
    }

    public boolean updateavatar(Integer userId, String avatar) {
        Optional<tbl_users> userOptional = userrepositories.findById(userId);
        if (userOptional.isPresent()) {
            tbl_users user = userOptional.get();
            user.setAvatar(avatar);

            userrepositories.save(user); // Lưu thay đổi vào cơ sở dữ liệu
            return true; // Trả về true nếu cập nhật thành công
        }
        return false;
    }

    public boolean updatepassword(Integer userId, String password) {
        Optional<tbl_users> userOptional = userrepositories.findById(userId);
        if (userOptional.isPresent()) {
            tbl_users user = userOptional.get();
            user.setAvatar(password);

            userrepositories.save(user); // Lưu thay đổi vào cơ sở dữ liệu
            return true; // Trả về true nếu cập nhật thành công
        }
        return false;
    }

    public List<String> check_quyen(tbl_nhomquyen nhomquuyen) {

        List<tbl_phanquyen> list_phanquyen = phanquyenRepositories.findByNhomquyen(nhomquuyen);
        List<String> list_quyen = new ArrayList();
        for(tbl_phanquyen phanuyen : list_phanquyen ){
                list_quyen.add(phanuyen.getQuyen().getQuyen());
        }
       return list_quyen;
    }

    public Long count() {
        return userrepositories.count();
    }

    public List<String> show_all_quyen(tbl_nhomquyen nhomquuyen) {
        List<tbl_phanquyen> show = phanquyenRepositories.findAllByNhomquyen(nhomquuyen);
        List<String> need = new ArrayList<>();
        for(tbl_phanquyen a : show){
            need.add(a.getQuyen().getQuyen());
        }
        return  need;
    }


    public boolean findbyQuyen(tbl_quyen quyen) {
        return phanquyenRepositories.existsByQuyen(quyen);
    }

    public tbl_users update_user(tbl_users users){
        return  userrepositories.save(users);
    }

    public boolean set_trangthai(int usertId) {
        tbl_users tblUsers = userrepositories.findByIdUser(usertId);
        if(tblUsers==null){
            return false;
        }
        if(tblUsers.getTrangThai() == 0){
            tblUsers.setTrangThai(1);
        }
        else{
            tblUsers.setTrangThai(0);
        }
        userrepositories.save(tblUsers);
        return true;
    }


    public Page<tbl_nhomquyen> findPagedNhomquyen(String search, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size); // Vì trang tính từ 0, nên trừ đi 1
        return nhomquyenRepositories.findPagedNhomquyen(search, pageRequest);
    }


    public int countall() {return userrepositories.findAll().size();    }
    public int countalluser() {return userrepositories.findAll().size();    }



    public Page<tbl_users> findPagedTaiKhoan(String search, String trangthaihd, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size); // Vì trang tính từ 0, nên trừ đi 1
        return userrepositories.findPagedTaiKhoan(search, trangthaihd, pageRequest);

    }

    public List<tbl_nhomquyen> findallnhomquyen(){
        return nhomquyenRepositories.findAll();
    }


    public void xoaUser(int id) {
        tbl_users user = userrepositories.findByIdUser(id);
        user.setTrangThai(1);
        user.setDaxoa(1);
        userrepositories.save(user);
    }

    public boolean checkexistQuyen(String nhomquyen,String quyen) {
        Optional<tbl_nhomquyen> tblNhomquyen = nhomquyenRepositories.findById(nhomquyen);
        Optional<tbl_quyen> tblquyen = quyenRepositories.findById(quyen);

        return phanquyenRepositories.existsByNhomquyenAndQuyen(tblNhomquyen.get(),tblquyen.get());
    }

    public boolean add(tbl_nhomquyen danhmucnew) {
        try {
            nhomquyenRepositories.save(danhmucnew);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Optional<tbl_nhomquyen> findById(String id) {
        return nhomquyenRepositories.findById(id);
    }
}
