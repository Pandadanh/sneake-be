package com.example.project_springboot.controllers.controller_admin;

import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_phieuxuat;
import com.example.project_springboot.model.tbl_product;
import com.example.project_springboot.service.service_phieuxuat;
import com.example.project_springboot.service.service_product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api-admin/controller-phieuxuat")
public class controller_admin_phieuxuat {
    @Autowired
    private service_phieuxuat service_phieuxuat ;


    @Autowired
    private service_product service_product ;

    @GetMapping("/thong-ke")
    public ResponseEntity<ResponseObject> thong_ke(
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "6") int size,
        @RequestParam(required = false) String id_nh,
        @RequestParam(required = false) String id_dm,
        @RequestParam(required = false) String ngaymin,
        @RequestParam(required = false) String ngaymax
    )

    {
        int itemsPerPage = size; // Số sản phẩm trên mỗi trang

        Integer idnh =null;
        Integer iddm =null;

        if (ngaymin == null || ngaymin.equals("") )  {
            ngaymin = null;
        }
        if (ngaymax == null || ngaymax.equals("") )  {
            ngaymax = null;
        }

        if (id_nh != null && !id_nh.isEmpty()) {
            try {
                idnh = Integer.parseInt(id_nh);

            } catch (NumberFormatException e) {
                idnh = null;
            }
        } else {
            idnh =null;
        }


        if (id_dm != null && !id_dm.isEmpty()) {
            try {
                iddm = Integer.parseInt(id_nh);

            } catch (NumberFormatException e) {
                iddm = null;
            }
        } else {
            iddm =null;
        }

        int count = 0;


        List<tbl_phieuxuat> productPage = service_phieuxuat.findPagedPhieuxuatcaocap(ngaymin,ngaymax, page, itemsPerPage);
    List<Integer> list_idPx = new ArrayList<>();
        List<Map<String, Object>> keyValues = new ArrayList<>();

        for (tbl_phieuxuat product :productPage) {
            list_idPx.add(product
                .getIdPx());
        }

        List<Object[]> list_data = service_phieuxuat.getProductSalesInfo(list_idPx);


        for (Object[] row : list_data) {
            int id = (int) row[0];
            String key = (String) row[1];
            int value = (int) row[2];

            // Tìm xem đã tồn tại dòng nào có cùng "id" trong danh sách kết quả chưa
            boolean exists = false;
            for (Map<String, Object> keyValue : keyValues) {
                if (id == (int) keyValue.get("id")) {
                    // Có dòng có cùng "id", thực hiện cộng tối số lượng và tính tổng giá tiền
                    int totalSoLuong = (int) keyValue.get("Tongsoluong") + Integer.parseInt(key);
                    int totalGiaTien = (int) keyValue.get("Tonggiatien") + (value * Integer.parseInt(key));

                    // Cập nhật giá trị mới vào dòng này
                    keyValue.put("Tongsoluong", totalSoLuong);
                    keyValue.put("Tonggiatien", totalGiaTien);

                    exists = true;
                    break;
                }
            }

            if (!exists) {
                // Tạo một dòng mới và thêm vào danh sách kết quả
                Map<String, Object> keyValue = new HashMap<>();

                tbl_product product = service_product.find_product_byID(id);
                keyValue.put("id", id);
                keyValue.put("product", product);
                keyValue.put("Tongsoluong", Integer.parseInt(key));
                keyValue.put("Tonggiatien", value * Integer.parseInt(key));
                keyValue.put("Soluongconcai", service_product.Sumidpro(id));

                keyValues.add(keyValue);
            }
        }
        String page_all = String.valueOf(keyValues.size());
        if (!keyValues.isEmpty()) {

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully retrieved", page_all, keyValues)
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }



}
