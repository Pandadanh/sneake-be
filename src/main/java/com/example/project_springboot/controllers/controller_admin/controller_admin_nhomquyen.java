package com.example.project_springboot.controllers.controller_admin;

import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_nhomquyen;
import com.example.project_springboot.service.service_user;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api-admin/controller-nhomquyen")
public class controller_admin_nhomquyen {
    @Autowired
    private service_user service_user ;

    @GetMapping("/show")
        public ResponseEntity<ResponseObject> show(){
        List<tbl_nhomquyen> list = service_user.findallnhomquyen();
        String count = String.valueOf(list.size());

        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully retrieved", count, list)
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }



    @GetMapping("/search")
    public ResponseEntity<ResponseObject> show_product(
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "6") int size,
        @RequestParam(required = false) String search
    )

    {
        int itemsPerPage = size; // Số sản phẩm trên mỗi trang


        if (search == null || search.equals("") )  {
            search = null;
        }

        String page_all = String.valueOf(service_user.countall());

        Page<tbl_nhomquyen> productPage = service_user.findPagedNhomquyen(search, page, itemsPerPage);

        if (!productPage.isEmpty()) {
            List<tbl_nhomquyen> list_product = productPage.getContent();
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully retrieved", page_all, list_product)
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }
}
