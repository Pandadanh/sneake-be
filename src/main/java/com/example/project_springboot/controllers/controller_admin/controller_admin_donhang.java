package com.example.project_springboot.controllers.controller_admin;

import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_chitiet_px;
import com.example.project_springboot.model.tbl_phieuxuat;
import com.example.project_springboot.model.tbl_users;
import com.example.project_springboot.service.service_phieuxuat;
import com.example.project_springboot.service.service_product;
import com.example.project_springboot.service.service_user;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api-admin/controller-don-hang")
public class controller_admin_donhang {

    @Autowired
    private service_phieuxuat service_phieuxuat ;

    @Autowired
    private service_user service_user ;

    @Autowired
    private service_product service_product ;


    @GetMapping("/show")
    public ResponseEntity<?> show() {
        List<tbl_chitiet_px> list =  service_phieuxuat.showall_chitiet();

        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", "",list)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<?> find(@PathVariable int id) {
        List<tbl_chitiet_px> list =  service_phieuxuat.findallid(id);

        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", "",list)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @GetMapping("/chitiet-donhang/{id}")
    public ResponseEntity<?> chitiet_donhang(@PathVariable int id) {
        List<tbl_chitiet_px> list =  service_phieuxuat.findchititebyid(id);

        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", "",list)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @GetMapping("/search")
    public ResponseEntity<ResponseObject> show_product(
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "6") int size,
        @RequestParam(required = false) String ngaymin,
        @RequestParam(required = false) String ngaymax,
        @RequestParam(required = false) String tinhtrang

    )

    {
        int itemsPerPage = size; // Số sản phẩm trên mỗi trang

        Integer iddm =null;

        if (ngaymin == null || ngaymin.equals("") )  {
            ngaymin = null;
        }

        if (ngaymax == null || ngaymax.equals("") )  {
            ngaymax = null;
        }

        if (tinhtrang == null || tinhtrang.equals("") )  {
            tinhtrang = null;
        }


        String page_all = String.valueOf(service_phieuxuat.countall());

        Page<tbl_phieuxuat> productPage = service_phieuxuat.findPagedphieuxuat(ngaymin,ngaymax,tinhtrang, page, itemsPerPage);

        if (!productPage.isEmpty()) {
            List<tbl_phieuxuat> list_product = productPage.getContent();
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully retrieved", page_all, list_product)
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @PostMapping("/xacnhan")
    public ResponseEntity<?> xacnhan(@RequestBody Map<String,?> requestdata) {

        String id_pxstr = (String) requestdata.get("id_px");
        Integer id_nv = (int) requestdata.get("id_nv");
        Integer id_px ;


        try {
            id_px = Integer.valueOf(id_pxstr);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Fail", "No products found")
            );
        }
        tbl_users nhanvien = service_user.findByid(id_nv);
        Optional<tbl_phieuxuat> phieuxuat_op = service_phieuxuat.findByid(id_px);
        tbl_phieuxuat phieuxuat = phieuxuat_op.get();
        phieuxuat.setTrangThai(1);
        service_phieuxuat.save(phieuxuat);

        if (1==1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @PostMapping("/huyxacnhan")
    public ResponseEntity<?> huyxacnhan(@RequestBody Map<String,?> requestdata) {

        String id_pxstr = (String) requestdata.get("id_px");
        Integer id_nv = (int) requestdata.get("id_nv");
        Integer id_px ;


        try {
            id_px = Integer.valueOf(id_pxstr);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Fail", "No products found")
            );
        }


        List<tbl_chitiet_px> list_px = service_phieuxuat.findchititebyid(id_px);
        for (tbl_chitiet_px px : list_px ) {
            String proSoluong = service_product.tangsoluong(px.getProduct().getIdPro(),px.getSize().getIdSize(),
                Integer.parseInt(px.getSoLuong()));
        }

        tbl_users nhanvien = service_user.findByid(id_nv);
        Optional<tbl_phieuxuat> phieuxuat_op = service_phieuxuat.findByid(id_px);
        tbl_phieuxuat phieuxuat = phieuxuat_op.get();
        phieuxuat.setTrangThai(2);
        service_phieuxuat.save(phieuxuat);

        if (1==1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }
}
