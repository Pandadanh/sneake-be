package com.example.project_springboot.controllers.controller_admin;


import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_size;
import com.example.project_springboot.service.service_size;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api-admin/controller-size")
public class controller_admin_size {
    @Autowired
    private service_size service_size ;

    @GetMapping("/show/{page}")
    public ResponseEntity<?> show(@PathVariable(name = "page") Integer page) {
        if (page == null || page < 1) {
            page = 1; // Nếu page không có giá trị hoặc nhỏ hơn 1, thì gán giá trị mặc định là 1
        }

        Long count = service_size.count();
        int size = 7;
        int offset = (page - 1) * size;
        List<tbl_size> tbl_size = service_size.findPagedSizes(size, offset);

        if (!tbl_size.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", String.valueOf(count), tbl_size)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @GetMapping("/show-search/{search}/{page}")
    public ResponseEntity<?> show_search(@PathVariable String search, @PathVariable Integer page) {
        if (page == null || page < 1) {
            page = 1; // Nếu page không có giá trị hoặc nhỏ hơn 1, thì gán giá trị mặc định là 1
        }

        int size = 7;
        int offset = (page - 1) * size;

        int count = service_size.countSizesBySearch(search);
        List<tbl_size> tbl_size = service_size.findPagedSizesBySearch(size, offset, search);

        if (!tbl_size.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", String.valueOf(count), tbl_size)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {

        String error = service_size.delete_size(id);

        if (error.equals("")) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }
    @PutMapping("/soft-delete/{id}")
    public ResponseEntity<?> soft_delete(@PathVariable int id) {

        String error = service_size.soft_delete_size(id);

        if (error.equals("")) {
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

        String danhmuc = requestdata.get("sizenew").toString();
        tbl_size danhmucnew = new tbl_size(danhmuc,0);
        boolean check = service_size.add(danhmucnew);

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
        tbl_size danhmuc = service_size.findById(id);
        danhmuc.setDaxoa(1);
        boolean check = service_size.add(danhmuc);

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
        tbl_size danhmuc = service_size.findById(id);
        danhmuc.setSize(danhmuc1);
        boolean check = service_size.add(danhmuc);

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

        tbl_size danhmuc = service_size.findById(id);

        if (danhmuc.getSize()!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", "",danhmuc)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }
}
