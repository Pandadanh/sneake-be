package com.example.project_springboot.controllers.controller_shopgiay;

import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_product;
import com.example.project_springboot.service.service_product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = "/api/controller-product")
public class controller_product {


    @Autowired
    private service_product service_product;

    @GetMapping("/home")
    public ResponseEntity<ResponseObject> home() {

        List<tbl_product> list_product = service_product.showproduct_top4();

        if (!list_product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", list_product)
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }

}