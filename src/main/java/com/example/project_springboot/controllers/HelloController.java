package com.example.project_springboot.controllers;

import com.example.project_springboot.model.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public ResponseEntity<ResponseObject> showall() {
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "Xin chào")
        );
    }
}



