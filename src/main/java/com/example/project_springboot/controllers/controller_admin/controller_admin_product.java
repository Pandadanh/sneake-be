package com.example.project_springboot.controllers.controller_admin;


import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_danhmuc;
import com.example.project_springboot.model.tbl_nhanhieu;
import com.example.project_springboot.model.tbl_pro_soluong;
import com.example.project_springboot.model.tbl_product;
import com.example.project_springboot.service.service_danhmuc;
import com.example.project_springboot.service.service_nhanhieu;
import com.example.project_springboot.service.service_product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api-admin/controller-product")
public class controller_admin_product {

    @Autowired
    private service_product service_product ;

    @Autowired
    private service_nhanhieu service_nhanhieu ;

    @Autowired
    private service_danhmuc service_danhmuc ;



    @GetMapping("/search")
    public ResponseEntity<ResponseObject> show_product(
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "6") int size,
        @RequestParam(required = false) String search,
        @RequestParam(required = false) String id_nh,
        @RequestParam(required = false) String id_dm
    )

    {
        int itemsPerPage = size; // Số sản phẩm trên mỗi trang

        Integer idnh =null;
        Integer iddm =null;

        if (search == null || search.equals("") )  {
            search = null;
        }

        if (id_nh == null || id_nh.equals("") )  {
            id_nh = null;

        }
        else idnh = Integer.valueOf(id_nh);
        if (id_dm == null || id_dm.equals(""))  {
            id_dm = null;
        }
        else iddm = Integer.valueOf(id_dm);


        String page_all = String.valueOf(service_product.countall());

        Page<tbl_product> productPage = service_product.findPagedProducts(search, idnh, iddm, page, itemsPerPage);

        if (!productPage.isEmpty()) {
            List<tbl_product> list_product = productPage.getContent();
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully retrieved", page_all, list_product)
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @GetMapping("/sum-soluong/{id}")
    public ResponseEntity<?> find(@PathVariable int id) {
        int Sumidpro;
       try{
            Sumidpro =  service_product.Sumidpro(id);
       }catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
               new ResponseObject("Fail", "No products found","",0)
           );
       }

        if (Sumidpro >0 ) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter", "",Sumidpro)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @GetMapping("/show-dm-nh")
    public ResponseEntity<ResponseObject> show() {

        Map<String,List<?>> list_data = new HashMap<>();


        List<tbl_nhanhieu> list_nhanhieu = service_nhanhieu.showall();
        List<tbl_danhmuc> list_danhmuc= service_danhmuc.showall();


        list_data.put("list_nhanhieu",list_nhanhieu);
        list_data.put("list_danhmuc",list_danhmuc);


        if (!list_data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter","",list_data)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Faill", "fail")
        );
    }

    @GetMapping("/show-chitiet-soluong")
    public ResponseEntity<ResponseObject> show_chitiet_sl(@RequestParam int idPro) {

        List<tbl_pro_soluong> list_data = service_product.findSoluongByIdPro(idPro);


        if (!list_data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter","",list_data)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Faill", "fail")
        );
    }

    @GetMapping("/show-product")
    public ResponseEntity<ResponseObject> show_product(@RequestParam int idPro) {

        tbl_product data = service_product.find_product_byID(idPro);


        if (data.getDanhmuc() != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter","",data)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Faill", "fail")
        );
    }




}
