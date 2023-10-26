package com.example.project_springboot.controllers.controller_admin;


import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_nhomquyen;
import com.example.project_springboot.model.tbl_quyen;
import com.example.project_springboot.service.service_user;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping(path = "/api-admin/controller-user-admin")
public class controller_admin_quyen {

    @Autowired
    private service_user service_user ;

    @GetMapping("/show/{nhomquyen}")
    public ResponseEntity<?> check_all_quyen(@PathVariable String nhomquyen) {
        tbl_nhomquyen nhomquyen1 = new tbl_nhomquyen(nhomquyen,0);
        List<String> user = service_user.show_all_quyen(nhomquyen1);

        if (!user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", "",user)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @GetMapping("/check-quyen/{quyen}")
    public ResponseEntity<?> check_quyen(@PathVariable String quyen) {
        tbl_quyen quyen1 = new tbl_quyen(quyen,"0");
        boolean user = service_user.findbyQuyen(quyen1);

        if (user) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Map<String, ?> requestdata) {

        String danhmuc = requestdata.get("new").toString();
        tbl_nhomquyen danhmucnew = new tbl_nhomquyen(danhmuc,0);
        boolean check = service_user.add(danhmucnew);

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
        Optional<tbl_nhomquyen> danhmuc = service_user.findById(intstr);
        tbl_nhomquyen danh = danhmuc.get();
        danh.setDaxoa(1);
        boolean check = service_user.add(danh);

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

        String danhmuc1 = requestdata.get("danhmucnew").toString();
        Optional<tbl_nhomquyen> danhmuc = service_user.findById(intstr);
        tbl_nhomquyen danh = danhmuc.get();
        danh.setId(danhmuc1);
        boolean check = service_user.add(danh);

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
        Optional<tbl_nhomquyen> danhmuc = service_user.findById(intstr);

        if (danhmuc.get()!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", "",danhmuc.get())
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

}
