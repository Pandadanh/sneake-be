package com.example.project_springboot.controllers.controller_admin;

import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_danhmuc;
import com.example.project_springboot.service.service_danhmuc;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api-admin/controller-danhmuc")
public class controller_admin_danhmuc {
    @Autowired
    private service_danhmuc service_danhmuc ;

    @PostMapping("/show")
    public ResponseEntity<?> show_dm_hoatdong() {

        List<tbl_danhmuc> list = service_danhmuc.findActiveDanhMuc();
        String size = String.valueOf(list.size());
        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", size,list)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @PostMapping("/find-iddm-danhmuc")
    public ResponseEntity<?> find_iddm_or_danhmuc(@RequestBody  Map<String,?> request) {

      try {
          String search = (String) request.get("search");
          List<tbl_danhmuc> list = service_danhmuc.findDanhMucBySearch(search);
          String size = String.valueOf(list.size());
          if (!list.isEmpty()) {
              return ResponseEntity.status(HttpStatus.OK).body(
                  new ResponseObject("ok", "Successfully inserter", size,list)
              );
          }
      }catch (NumberFormatException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("ok", "Successfully inserter", "0","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("ok", "Successfully inserter", "0","")
        );
    }

    @PostMapping("/find-danhmuc")
    public ResponseEntity<?> find_danhmuc(@RequestBody  Map<String,?> request) {
        String search = (String) request.get("search");
        List<tbl_danhmuc> list = service_danhmuc.findDanhMucByRegexp(search);

        String size = String.valueOf(list.size());
        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", size,list)
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
        @RequestParam(required = false) String id_dm

    )

    {
        int itemsPerPage = size; // Số sản phẩm trên mỗi trang

        Integer iddm =null;

        if (search == null || search.equals("") )  {
            search = null;
        }

        if (id_dm == null || id_dm.equals(""))  {
            iddm = null;
        }
        else iddm = Integer.valueOf(id_dm);


        String page_all = String.valueOf(service_danhmuc.countall());

        Page<tbl_danhmuc> productPage = service_danhmuc.findPagedDanhmuc(search, iddm, page, itemsPerPage);

        if (!productPage.isEmpty()) {
            List<tbl_danhmuc> list_product = productPage.getContent();
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully retrieved", page_all, list_product)
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Map<String, ?> requestdata) {

        String danhmuc = requestdata.get("danhmucnew").toString();
        tbl_danhmuc danhmucnew = new tbl_danhmuc(danhmuc,0);
        boolean check = service_danhmuc.add(danhmucnew);

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
        tbl_danhmuc danhmuc = service_danhmuc.findById(id);
        danhmuc.setDaXoa(1);
        boolean check = service_danhmuc.add(danhmuc);

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
        String danhmuc1 = requestdata.get("danhmucnew").toString();
        tbl_danhmuc danhmuc = service_danhmuc.findById(id);
        danhmuc.setDanhMuc(danhmuc1);
        boolean check = service_danhmuc.add(danhmuc);

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

        tbl_danhmuc danhmuc = service_danhmuc.findById(id);

        if (danhmuc.getDanhMuc()!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", "",danhmuc)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }
}
