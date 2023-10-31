package com.example.project_springboot.service;

import com.example.project_springboot.model.tbl_chitiet_pn;
import com.example.project_springboot.model.tbl_danhmuc;
import com.example.project_springboot.model.tbl_nhacungcap;
import com.example.project_springboot.model.tbl_nhanhieu;
import com.example.project_springboot.model.tbl_phieunhap;
import com.example.project_springboot.model.tbl_pro_soluong;
import com.example.project_springboot.model.tbl_product;
import com.example.project_springboot.model.tbl_size;
import com.example.project_springboot.model.tbl_users;
import com.example.project_springboot.repositories.chitietphieunhapRepositories;
import com.example.project_springboot.repositories.danhmucRepositories;
import com.example.project_springboot.repositories.nhacungcapRepositores;
import com.example.project_springboot.repositories.nhanhieuRepositories;
import com.example.project_springboot.repositories.phieunhapRepositories;
import com.example.project_springboot.repositories.proSoLuongRepositories;
import com.example.project_springboot.repositories.productRepositories;
import com.example.project_springboot.repositories.sizeRepositories;
import com.example.project_springboot.repositories.userRepositories;
import com.example.project_springboot.service.rules.rules_service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class service_phieunhap implements rules_service<tbl_phieunhap> {
    @Autowired
    private sizeRepositories sizeRepositories;

    @Autowired
    private nhanhieuRepositories nhanhieuRepositories;

    @Autowired
    private nhacungcapRepositores nhacungcapRepositores;

    @Autowired
    private danhmucRepositories danhmucRepositories;

    @Autowired
    private proSoLuongRepositories proSoLuongRepositories;

    @Autowired
    private userRepositories userRepositories;

    @Autowired
    private phieunhapRepositories phieuNhapRepository;

    @Autowired
    private chitietphieunhapRepositories chiTietPhieuNhapRepository;

    @Autowired
    private productRepositories productRepositories;

    public List<tbl_phieunhap> getAllPhieuNhap() {
        return phieuNhapRepository.findAll();
    }

    public tbl_phieunhap createPhieuNhap(tbl_phieunhap phieuNhap) {
        return phieuNhapRepository.save(phieuNhap);
    }


    public List<tbl_chitiet_pn> getAllChiTietPhieuNhap() {
        return chiTietPhieuNhapRepository.findAll();
    }

    public String createChiTietPhieuNhap(String tongtien,int tongsl, List<Map<String, Object>>  cartItems,String id_ncc,String id_nv) {
        String error = "";
        LocalDate currentDate = LocalDate.now();

    tbl_phieunhap new_phieunhap = new tbl_phieunhap();
    tbl_users nhanvien = userRepositories.findByIdUser(Integer.parseInt(id_nv));

    tbl_nhacungcap nhacungcap = nhacungcapRepositores.findById(Integer.valueOf(id_ncc)).get();

    new_phieunhap.setNhanvien(nhanvien);
    new_phieunhap.setNgaynhap(currentDate);
    new_phieunhap.setTongsl(tongsl);
    new_phieunhap.setTinhtrang(0);
    new_phieunhap.setTongTien(tongtien);
    new_phieunhap.setNhacungcap(nhacungcap);


    tbl_phieunhap new_success = phieuNhapRepository.save(new_phieunhap);


        for (Map<String, Object> cartItem : cartItems) {
            int idPn = new_success.getIdPn();

            Object id_pro_test = cartItem.get("id_pro");
            int id_pro;
            if (id_pro_test instanceof Integer) {
                id_pro = (int) id_pro_test;
            } else {
                String test = (String) id_pro_test;
                id_pro = Integer.parseInt(test);
            }

            tbl_product tblProduct = productRepositories.findByIdPro(id_pro);

            int id_nh = tblProduct.getNhanhieu().getId_nh();
            int id_dm = tblProduct.getDanhmuc().getIdDm();

            Object dongia_test = cartItem.get("gianhap");
            int dongia;
            if (dongia_test instanceof Integer) {
                dongia = (int) dongia_test;
            } else {
                String test = (String) dongia_test;
                dongia = Integer.parseInt(test);
            }

            Object id_size_test = cartItem.get("id_size");
            int id_size;
            if (id_size_test instanceof Integer) {
                id_size = (int) id_size_test;
            } else {
                String test = (String) id_size_test;
                id_size = Integer.parseInt(test);
            }


            Object soluong_test = cartItem.get("soluong");
            String soluong;
            if (soluong_test instanceof Integer) {
                int test = (int) soluong_test;
                soluong = String.valueOf(test);
            } else {
                 soluong = (String) soluong_test;

            }

            //thêm vô chitiet phiếu nhap


            tbl_phieunhap phieunhap = phieuNhapRepository.getById(idPn);
            tbl_product product = productRepositories.findByIdPro(id_pro);
            tbl_nhanhieu nhanhieu = nhanhieuRepositories.findByIdNh(id_nh);
            tbl_danhmuc danhmuc = danhmucRepositories.findByIdDm(id_dm);
            tbl_size size1 =sizeRepositories.findById(id_size);

            tbl_chitiet_pn tbl_chitiet_pn = new tbl_chitiet_pn(  phieunhap,  product, nhanhieu, danhmuc, dongia, size1,soluong);
            chiTietPhieuNhapRepository.save(tbl_chitiet_pn);

            tbl_pro_soluong soluongcu = proSoLuongRepositories.findByProduct_IdProAndSize_IdSize(id_pro, id_size);
            if(soluongcu != null){
                int soLuongCu = soluongcu.getSoLuong();
                int soLuongMoi = soLuongCu + Integer.parseInt(soluong);
                soluongcu.setSoLuong(soLuongMoi);
                proSoLuongRepositories.save(soluongcu);
            }
            else {
                tbl_size size = sizeRepositories.findById(id_size);
                int soLuongMoi = Integer.parseInt(soluong);
                tbl_pro_soluong new_pro_soluong = new tbl_pro_soluong(tblProduct,size,soLuongMoi);
                proSoLuongRepositories.save(new_pro_soluong);
            }




        }


    return error;
    }
    @Override
    public List<tbl_phieunhap> showall() {
        return null;
    }
}
