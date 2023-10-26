package com.example.project_springboot.controllers.controller_shopgiay;

import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_chitiet_px;
import com.example.project_springboot.model.tbl_phieuxuat;
import com.example.project_springboot.model.tbl_product;
import com.example.project_springboot.model.tbl_size;
import com.example.project_springboot.model.tbl_users;
import com.example.project_springboot.service.service_phieuxuat;
import com.example.project_springboot.service.service_product;
import com.example.project_springboot.service.service_size;
import com.example.project_springboot.service.service_user;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/controller-giohang")
public class controller_giohang {
    @Autowired
    private service_phieuxuat service_phieuxuat;

    @Autowired
    private service_size service_size;

    @Autowired
    private service_user service_user;

    @Autowired
    private service_product service_product;

    @GetMapping("/home")
    public ResponseEntity<ResponseObject> home() {

        List<tbl_phieuxuat> tbl_phieuxuat = service_phieuxuat.showall();
//        tbl_users abcd = new tbl_users(2,"12345","Nguyen Van A","nva@gmail.com","0147258369","Chua co nha","user-2.jpg",0, "Nhân viên",1,null,null );
//        tbl_phieuxuat abc = new tbl_phieuxuat(1,abcd,2,"10000",null,"",3,0 );
//        service_phieuxuat.save(abc);
        if (!tbl_phieuxuat.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", tbl_phieuxuat)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @PostMapping("/create-phieu-xuat")
    public ResponseEntity<ResponseObject> createPhieuXuat(@RequestBody Map<String,?> phieuXuatRequest) {

        int id_user = (int) phieuXuatRequest.get("idUser");
        tbl_users user = service_user.findByid(id_user);
        if (user.getIsActive() != 1 && !user.getMatKhau().equals(""))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Fail", "Not id founds")
            );
        }

        int id_nv = 1;
        String tongtien = (String) phieuXuatRequest.get("tongtien");
        LocalDate ngayHomNay = LocalDate.now();
        String daxoa = "";
        String tongslstr = (String) phieuXuatRequest.get("tongsl");
        int tongsl = Integer.parseInt(tongslstr);

        int trangthai = 0;


        List<Map<String, Object>> cartItems = (List<Map<String, Object>>) phieuXuatRequest.get("cart");
        String error="";
        for (Map<String, Object> cartItem : cartItems) {

            int idPro = (int) cartItem.get("idPro");

            String id_sizestr = (String) cartItem.get("id_size");
            int id_size = Integer.parseInt(id_sizestr);

            int soluongmuon = 0;
            try{
                String soluongmuona = (String) cartItem.get("soluong");
                 soluongmuon = Integer.parseInt(soluongmuona);
            }
            catch (Exception e) {
                 soluongmuon = (int) cartItem.get("soluong");
            }

             error += service_product.giamsoluong(idPro,id_size,soluongmuon);
            //thêm vô chitiet phiếu xuất

        }

        // thêm vô
        tbl_users nhanvien = service_user.findByid(id_nv);
        tbl_phieuxuat phieuNhap = new tbl_phieuxuat(user,nhanvien,tongtien,ngayHomNay,daxoa,tongsl,trangthai);
        tbl_phieuxuat response = service_phieuxuat.taoPhieuXuat(phieuNhap);

        for (Map<String, Object> cartItem : cartItems) {

            int idPro = (int) cartItem.get("idPro");
            tbl_product product = service_product.find_product_byID(idPro);
            String id_sizestr = (String) cartItem.get("id_size");
            int id_size = Integer.parseInt(id_sizestr);


            String soluong = "";
            try{
                int soluongint = (int) cartItem.get("soluong");
                soluong = String.valueOf(soluongint);
            }
            catch (Exception e) {
                soluong = (String) cartItem.get("soluong");
            }


            int giaban = Integer.parseInt(product.getGiaMoi()) *  Integer.parseInt(soluong);

            //thêm vô chitiet phiếu xuất
            tbl_product product1 = service_product.find_product_byID(idPro);
            tbl_size size = service_size.find_size_by_id(id_size);
            tbl_chitiet_px  tbl_chitiet_px = new tbl_chitiet_px(response,product1,size,soluong,giaban);
            service_phieuxuat.save_chitiet(tbl_chitiet_px);
        }





        if (1==1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserter")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "No products found")
        );

    }

}

