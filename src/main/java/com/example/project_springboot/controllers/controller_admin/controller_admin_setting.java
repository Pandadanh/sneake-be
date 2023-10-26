package com.example.project_springboot.controllers.controller_admin;

import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_setting;
import com.example.project_springboot.service.service_setting;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api-admin/controller-setting")
public class controller_admin_setting {
    @Autowired
    private service_setting service_setting ;

    @GetMapping("/show")
    public ResponseEntity<?> show() {

        List<tbl_setting> tbl_setting = service_setting.showall();

        if (!tbl_setting.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", "", tbl_setting.get(1))
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @GetMapping("/update-page")
    public ResponseEntity<?> update_logo(@RequestBody Map<String,String> data) {

        String file_name = data.get("filename_logo");
        String filename_favicon= data.get("filename_favicon");

        List<tbl_setting> tbl_setting = service_setting.showall();
        tbl_setting avc = tbl_setting.get(1);
        if(file_name != null)
            avc.setLogo(file_name);

        if(filename_favicon != null)
            avc.setLogo(filename_favicon);

        service_setting.save(avc);

        if (1==1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", "", tbl_setting.get(1))
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

}
