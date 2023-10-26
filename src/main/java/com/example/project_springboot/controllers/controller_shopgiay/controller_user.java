package com.example.project_springboot.controllers.controller_shopgiay;


import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_users;
import com.example.project_springboot.service.service_user;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/controller-user")
public class controller_user {

    @Autowired
    private service_user service_user ;


    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody Map<String,String> logindata ){
        String email = logindata.get("email");
        String password = logindata.get("matkhau");


        tbl_users user = service_user.check_login(email,password);

        if(user!=null){
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted","", user)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(

            new ResponseObject("NOT_FOUND", "Not Found", "","")
        );

    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseObject> sign_up(@RequestBody  tbl_users sign_user ) {
        String error = service_user.save_user(sign_user);

        if (error.equals("")) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", sign_user)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(

            new ResponseObject("ERROR", "ERROR", "", error)
        );
    }

    @PostMapping("/active-token")
    public ResponseEntity<ResponseObject> active_token(@RequestParam String activeToken) {

        String error = service_user.updateUserActivation(activeToken);
        if (error.equals("")) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", error)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(

            new ResponseObject("ERROR", "ERROR", "", error)
        );
    }



}
