package com.example.project_springboot.controllers.controller_admin;

import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_chitiet_pn;
import com.example.project_springboot.model.tbl_danhmuc;
import com.example.project_springboot.model.tbl_nhacungcap;
import com.example.project_springboot.model.tbl_nhanhieu;
import com.example.project_springboot.model.tbl_size;
import com.example.project_springboot.service.service_danhmuc;
import com.example.project_springboot.service.service_nhacungcap;
import com.example.project_springboot.service.service_nhanhieu;
import com.example.project_springboot.service.service_phieunhap;
import com.example.project_springboot.service.service_size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api-admin/controller-chitietpn")
public class controller_admin_phieunhap {
    @Autowired
    private service_phieunhap service_phieunhap ;

    @Autowired
    private service_size service_size ;

    @Autowired
    private service_nhanhieu service_nhanhieu ;

    @Autowired
    private service_danhmuc service_danhmuc ;

    @Autowired
    private service_nhacungcap service_nhacungcap ;

    public controller_admin_phieunhap() {
    }

    @GetMapping
    public List<tbl_chitiet_pn> getAllChiTietPhieuNhap() {
        return service_phieunhap.getAllChiTietPhieuNhap();
    }


    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createChiTietPhieuNhap(@RequestBody Map<String,?> chiTietPhieuNhap) {

        String tongtien = (String) chiTietPhieuNhap.get("tongtien");

        String tongsl = (String) chiTietPhieuNhap.get("tongsl");

        int tongsl_int = Integer.parseInt(tongsl);
        String id_ncc ;

        try {
             id_ncc = (String) chiTietPhieuNhap.get("nhacungcap");
        }catch (Exception e){

            Integer temp = (Integer) chiTietPhieuNhap.get("nhacungcap");
            id_ncc = String.valueOf(temp);
        }
        String id_nv ;
        try {
             id_nv = (String) chiTietPhieuNhap.get("idUser");
        }catch (Exception e){

            Integer temp = (Integer) chiTietPhieuNhap.get("idUser");
            id_nv = String.valueOf(temp);
        }



        List<Map<String, Object>> cartItems = (List<Map<String, Object>>) chiTietPhieuNhap.get("phieunhap");

        String error = service_phieunhap.createChiTietPhieuNhap( tongtien, tongsl_int, cartItems ,id_ncc , id_nv);

        if (error.equals("")) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", error)
        );
    }

    @GetMapping("/show-size")
    public ResponseEntity<ResponseObject> createChiTietPhieuNhap( @RequestParam(required = false) String size) {

        tbl_size error = service_size.findbySize(size);

        if (error.getSize() != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter","",error)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Faill", "fail")
        );
    }

    @GetMapping("/show-nhacungcap")
    public ResponseEntity<ResponseObject> show_nhacungcap( ) {

        List<tbl_nhacungcap> error = service_nhacungcap.showall();

        if (error.size() >0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter","",error)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Faill", "fail")
        );
    }


    @GetMapping("/show")
    public ResponseEntity<ResponseObject> show() {

       Map<String,List<?>> list_data = new HashMap<>();

       List<tbl_size> list_size = service_size.showall();
        List<tbl_nhanhieu> list_nhanhieu = service_nhanhieu.showall();
        List<tbl_danhmuc> list_danhmuc= service_danhmuc.showall();

        list_data.put("list_size",list_size);
        list_data.put("list_nhanhieu",list_nhanhieu);
        list_data.put("list_danhmuc",list_danhmuc);


        if (!list_data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter","",list_data)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Faill", "fail")
        );
    }
}
