package com.example.project_springboot.controllers.controller_admin;

import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.service.service_phieuxuat;
import com.example.project_springboot.service.service_product;
import com.example.project_springboot.service.service_user;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api-admin/controller-dashboard")
public class controller_admin_dashboard {
    @Autowired
    private service_user service_user ;

    @Autowired
    private service_product service_product ;

    @Autowired
    private service_phieuxuat service_phieuxuat ;

    @GetMapping("/show")
    public ResponseEntity<?> show() {

        Long count_user = service_user.count();
        Long count_product = service_product.count();
        Long tongtien = service_phieuxuat.tongtien();
        Long count_donhang = service_phieuxuat.count();


        HashMap<String,Long> list_data = new HashMap<>();
        list_data.put("count_user",count_user);
        list_data.put("count_donhang",count_donhang);
        list_data.put("count_product",count_product);
        list_data.put("tongtien",tongtien);

        if (!list_data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", "",list_data)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }
}
