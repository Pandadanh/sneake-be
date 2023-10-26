package com.example.project_springboot.controllers.controller_admin;


import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_silders;
import com.example.project_springboot.service.service_silders;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api-admin/controller-sliders")
public class controller_admin_slider {
    @Autowired
    private service_silders service_sliders ;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Map<String, ?> requestdata) {

        String danhmuc = requestdata.get("sizenew   ").toString();
        tbl_silders danhmucnew = new tbl_silders(danhmuc);
        boolean check = service_sliders.add(danhmucnew);

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
        Optional<tbl_silders> danhmuc1 = service_sliders.findById(id);
        tbl_silders danhmuc = danhmuc1.get();
        boolean check = service_sliders.delete(danhmuc);

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
        String danhmuc1 = requestdata.get("sizenew").toString();


        Optional<tbl_silders> danhmuc2 = service_sliders.findById(id);
        tbl_silders danhmuc = danhmuc2.get();
        danhmuc.setPhoto(danhmuc1);
        boolean check = service_sliders.add(danhmuc);

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

        Optional<tbl_silders> danhmuc2 = service_sliders.findById(id);
        tbl_silders danhmuc = danhmuc2.get();

        if (danhmuc.getPhoto()!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", "",danhmuc)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }
}
