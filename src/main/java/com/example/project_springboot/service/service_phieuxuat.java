package com.example.project_springboot.service;

import com.example.project_springboot.model.tbl_chitiet_px;
import com.example.project_springboot.model.tbl_phieuxuat;
import com.example.project_springboot.repositories.chitietpxRepositrories;
import com.example.project_springboot.repositories.phieuxuatRepositories;
import com.example.project_springboot.repositories.userRepositories;
import com.example.project_springboot.service.rules.rules_service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class service_phieuxuat implements rules_service<tbl_phieuxuat> {


    @Autowired
    private phieuxuatRepositories phieuxuatRepositories;

    @Autowired
    private chitietpxRepositrories chitietpxRepositrories;

    @Autowired
    private userRepositories usersRepository;


    @Override
    public List<tbl_phieuxuat> showall() {
        return phieuxuatRepositories.findAll();
    }

    public List<tbl_chitiet_px> showall_chitiet() {
        return chitietpxRepositrories.findAll();
    }

    public void save(tbl_phieuxuat abc) {
        phieuxuatRepositories.save(abc);
    }

//    public tbl_phieuxuat createPhieuXuat(int idKhachHang, String tongTien, date ngayDat, int tongSoLuong) {
//        tbl_phieuxuat phieuXuat = new tbl_phieuxuat();
//        phieuXuat.setKhachHang(idKhachHang);
//        phieuXuat.setTongTien(tongTien);
//        phieuXuat.setTongSoLuong(tongSoLuong);
//        phieuXuat.setNgayDat(ngayDat);
//        return phieuXuatRepository.save(phieuXuat);
//    }

    public tbl_phieuxuat taoPhieuXuat(tbl_phieuxuat phieuXuatRequest) {
        return phieuxuatRepositories.save(phieuXuatRequest);
    }

    public List<tbl_phieuxuat> findpageuser(int idKh){
        return phieuxuatRepositories.findPhieuXuatByKhachHangIdUserOrderByNgayDatDesc(idKh);
    };

    public List<tbl_chitiet_px> findchititebyid(int idPx){
        return chitietpxRepositrories.findAllByPhieuxuat_IdPx(idPx);
    };

    public tbl_chitiet_px save_chitiet(tbl_chitiet_px a){
        return chitietpxRepositrories.save(a);
    };

    public Long tongtien() {
        List<tbl_phieuxuat> list = phieuxuatRepositories.findAll();
        int sum = 0;
        for(tbl_phieuxuat a : list){
            sum += Integer.valueOf(a.getTongTien());
        }
        return (long) sum;
    }


    public Long count() {
        return phieuxuatRepositories.count();
    }

    public List<tbl_chitiet_px> findallid(int id) {
        return chitietpxRepositrories.findAllByPhieuxuat_IdPx(id);
    }


    public Page<tbl_phieuxuat> findPagedphieuxuat(String ngaymin, String ngaymax, String tinhtrang, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size); // Vì trang tính từ 0, nên trừ đi 1
        return phieuxuatRepositories.findPagedphieuxuat(ngaymin,ngaymax,tinhtrang, pageRequest);
    }

    public int countall() {return  phieuxuatRepositories.findAll().size();    }


    public List<tbl_phieuxuat> findPagedPhieuxuatcaocap(String ngaymin, String ngaymax, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size); // Vì trang tính từ 0, nên trừ đi 1
        return phieuxuatRepositories.findPagedphieuxuatcaocap(ngaymin,ngaymax, pageRequest);
    }

    public List<Object[]> getProductSalesInfo(List<Integer> idPxList) {
        return chitietpxRepositrories.getProductSalesInfo(idPxList);
    }

    public Optional<tbl_phieuxuat> findByid(Integer idPx) {
        return phieuxuatRepositories.findById(idPx);
    }
}
