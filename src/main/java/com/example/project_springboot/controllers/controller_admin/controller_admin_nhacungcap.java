package com.example.project_springboot.controllers.controller_admin;

import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_nhacungcap;
import com.example.project_springboot.service.service_nhacungcap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api-admin/controller-nhacungcap")
public class controller_admin_nhacungcap {
    @Autowired
    private service_nhacungcap service_nhacungcap ;

    @GetMapping("/search")
    public ResponseEntity<ResponseObject> show_product(
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "6") int size,
        @RequestParam(required = false) String search
    )

    {
        int itemsPerPage = size; // Số sản phẩm trên mỗi trang

        Integer idnh =null;

        if (search == null || search.equals("") )  {
            search = null;
        }


        String page_all = String.valueOf(service_nhacungcap.countall());

        Page<tbl_nhacungcap> productPage = service_nhacungcap.findPagedNhanhieu(search, page, itemsPerPage);

        if (!productPage.isEmpty()) {
            List<tbl_nhacungcap> list_product = productPage.getContent();
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully retrieved", page_all, list_product)
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Map<String, ?> requestdata) {

        String danhmuc = requestdata.get("nhanhieunew").toString();
        tbl_nhacungcap danhmucnew = new tbl_nhacungcap(danhmuc,0);
        boolean check = service_nhacungcap.add(danhmucnew);

        if (check) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Map<String, ?> requestdata) {

        String intstr = (String) requestdata.get("id");
        int id = Integer.parseInt(intstr);
        tbl_nhacungcap danhmuc = service_nhacungcap.findById(id);
        danhmuc.setDaxoa(1);
        boolean check = service_nhacungcap.add(danhmuc);

        if (check) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Map<String, ?> requestdata) {
        String intstr = (String) requestdata.get("id");
        int id = Integer.parseInt(intstr);
        String danhmuc1 = requestdata.get("nhanhieunew").toString();
        tbl_nhacungcap danhmuc = service_nhacungcap.findById(id);
        danhmuc.setTenNcc(danhmuc1);
        boolean check = service_nhacungcap.add(danhmuc);

        if (check) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @PostMapping("/show-id")
    public ResponseEntity<?> show_id(@RequestBody Map<String, ?> requestdatas) {

        String intstr = (String) requestdatas.get("id");
        Integer id = Integer.parseInt(intstr);

        tbl_nhacungcap danhmuc = service_nhacungcap.findById(id);

        if (danhmuc.getTenNcc()!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", "",danhmuc)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }
}
