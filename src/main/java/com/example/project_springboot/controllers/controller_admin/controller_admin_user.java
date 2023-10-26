package com.example.project_springboot.controllers.controller_admin;


import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_nhomquyen;
import com.example.project_springboot.model.tbl_users;
import com.example.project_springboot.service.service_user;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api-admin/controller-user-admin")
public class controller_admin_user {

    @Autowired
    private service_user service_user ;


    @GetMapping("/checkquyen/{usertId}")
    public ResponseEntity<?> check_quyen(@PathVariable int usertId) {
        tbl_users user = service_user.findByid(usertId);
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Fail", "No products found")
            );
        }
        List<String> list_quyen = service_user.check_quyen(user.getNhomQuyen());

        if (!list_quyen.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", "",list_quyen)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

        @PostMapping("/update-nhomquyen")
        public ResponseEntity<?> check_quyen(@RequestBody Map<String,?> request) {

        Object id_user =  request.get("id_user");
        int id;
        if (id_user instanceof Integer) {

             id = (int) id_user;

        } else {
            String temp = (String) id_user;
           id = Integer.parseInt(temp);
        }
        String nhomquyen = (String) request.get("nhomquyen");

        tbl_nhomquyen tblNhomquyen = new tbl_nhomquyen(nhomquyen,0);
        tbl_users user = service_user.findByid(id);
        user.setNhomQuyen(tblNhomquyen);
        service_user.update_user(user);

        if (1==1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @GetMapping("/doitt/{usertId}")
    public ResponseEntity<?> set_trangthai(@PathVariable int usertId) {
        boolean set = service_user.set_trangthai(usertId);
        if (set) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @GetMapping("/search")
    public ResponseEntity<ResponseObject> show_product(
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "6") int size,
        @RequestParam(required = false) String search,
        @RequestParam(required = false) String trangthaihd


    )
    {
        int itemsPerPage = size; // Số sản phẩm trên mỗi trang

        Integer iddm =null;

        if (search == null || search.equals("") )  {
            search = null;
        }

        if (trangthaihd == null || trangthaihd.equals("") )  {
            trangthaihd = null;
        }



        String page_all = String.valueOf(service_user.countalluser());

        Page<tbl_users> productPage = service_user.findPagedTaiKhoan(search, trangthaihd, page, itemsPerPage);

        if (!productPage.isEmpty()) {
            List<tbl_users> list_product = productPage.getContent();
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully retrieved", page_all, list_product)
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @GetMapping("/xoa-tk")
    public ResponseEntity<ResponseObject> show_product( @RequestParam int id  )
    {

        try{
            service_user.xoaUser(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ResponseObject("Fail", "No products found")
            );
        }
        if (1==1) {

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully retrieved")
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @GetMapping("/checkbox-quyen")
    public ResponseEntity<?> checkbox_quyen(@RequestParam String quyen , @RequestParam String nhomquyen) {
        boolean user = service_user.checkexistQuyen(nhomquyen,quyen);
        if (user) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }
}
