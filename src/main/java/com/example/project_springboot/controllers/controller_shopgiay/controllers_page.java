package com.example.project_springboot.controllers.controller_shopgiay;


import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_chitiet_px;
import com.example.project_springboot.model.tbl_danhmuc;
import com.example.project_springboot.model.tbl_mangxh;
import com.example.project_springboot.model.tbl_nhanhieu;
import com.example.project_springboot.model.tbl_phieuxuat;
import com.example.project_springboot.model.tbl_product;
import com.example.project_springboot.model.tbl_setting;
import com.example.project_springboot.model.tbl_silders;
import com.example.project_springboot.model.tbl_size;
import com.example.project_springboot.model.tbl_users;
import com.example.project_springboot.service.service_danhmuc;
import com.example.project_springboot.service.service_mangxh;
import com.example.project_springboot.service.service_nhanhieu;
import com.example.project_springboot.service.service_phieuxuat;
import com.example.project_springboot.service.service_product;
import com.example.project_springboot.service.service_setting;
import com.example.project_springboot.service.service_silders;
import com.example.project_springboot.service.service_size;
import com.example.project_springboot.service.service_user;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/controller-page")
public class controllers_page {


    @GetMapping("/footer")
    public ResponseEntity<?> show_footer() {

        List<tbl_mangxh> list_setting = service_mangxh.showall();

        if (list_setting != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", list_setting)
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @Autowired
    private service_danhmuc service_danhmuc ;

    @Autowired
    private service_silders service_sliders ;

    @Autowired
    private service_product service_product ;

    @Autowired
    private service_nhanhieu service_nhanhieu ;

    @Autowired
    private service_size service_size ;
    @Autowired
    private service_setting service_setting ;

    @Autowired
    private service_mangxh service_mangxh ;

    @Autowired
    private service_user service_user ;

    @Autowired
    private service_phieuxuat service_phieuxuat ;


    @GetMapping("/home")
    public ResponseEntity<ResponseObject> home() {

//        List<tbl_product> list_product = service_product.showproduct_top4();
        Map<String, List<?>> list_data = new HashMap<>();
        List<tbl_silders> list_silders = service_sliders.showall();
        List<tbl_product> list_product_top4= service_product.showproduct_top4();
        List<tbl_product> list_product_iddm1= service_product.showproduct_iddm1();
        List<tbl_product> list_product_iddm2= service_product.showproduct_iddm2();

        list_data.put("list_silders", list_silders);
        list_data.put("list_product_top4", list_product_top4);
        list_data.put("list_product_iddm1", list_product_iddm1);
        list_data.put("list_product_iddm2", list_product_iddm2);

        if (!list_data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", list_data)
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }



    @GetMapping("/inf-product")
    public ResponseEntity<ResponseObject> product() {

//        List<tbl_product> list_product = service_product.showproduct_top4();
        Map<String, List<?>> list_data = new HashMap<>();
        List<tbl_nhanhieu> list_nhanhieu = service_nhanhieu.showall();
        List<tbl_size> list_size= service_size.showall();

        list_data.put("list_nhanhieu", list_nhanhieu);
        list_data.put("list_size", list_size);


        if (!list_data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", list_data)
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @GetMapping("/product")
    public ResponseEntity<ResponseObject> search() {

        String tenpro = "";
        String idnhStr = "";
        String iddmStr = "";

        List<tbl_product> list_product = service_product.find_by(tenpro, idnhStr, iddmStr);

        if (!list_product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", list_product)
            );
        }


        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @CrossOrigin(origins = "*", maxAge = 3600) // Thêm cài đặt CORS cho phương thức
    @GetMapping("/show-product")
    public ResponseEntity<ResponseObject> show_product(
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "9") int size,
        @RequestParam(required = false) String tenpro,
        @RequestParam(required = false) String idnhStr,
        @RequestParam(required = false) String iddmStr
        )

    {

        int itemsPerPage = size; // Số sản phẩm trên mỗi trang

        Integer idnh =null;
        Integer iddm =null;

        if (tenpro != null && tenpro.isEmpty()  )  {
            tenpro = null;
        }

        if (idnhStr != null && idnhStr.isEmpty())  {
            idnhStr = null;

        }
        else idnh = Integer.valueOf(idnhStr);
        if (iddmStr != null && iddmStr.isEmpty())  {
            iddmStr = null;
        }
        else iddm = Integer.valueOf(iddmStr);


        String page_all = String.valueOf(service_product.countall());

        Page<tbl_product> productPage = service_product.findPagedProducts(tenpro, idnh, iddm, page, itemsPerPage);

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
@CrossOrigin(origins = "*", maxAge = 3600) // Thêm cài đặt CORS cho phương thức
@GetMapping("/search")
    public ResponseEntity<ResponseObject> search(
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "9") int size,
        @RequestParam(required = false) String sapxep,
        @RequestParam(required = false) String minne,
        @RequestParam(required = false) String maxne,
        @RequestParam(required = false) String checkbox_brand,
        @RequestParam(required = false) String checkbox_size)

       {

           List<Integer> brandList = null;
           List<Integer> sizeList = null;
           int sapxep_int = 0;

           int minPrice = 0; // Giá trị mặc định
           int maxPrice = 9000000; // Giá trị mặc định

           if (minne != null && !minne.isEmpty()) {
               minPrice = Integer.parseInt(minne);
               if(minPrice < 0){
                   minPrice = 0;// Chuyển đổi chuỗi thành int
               }

           }

           if (maxne != null && !maxne.isEmpty()) {

               maxPrice = Integer.parseInt(maxne);
               if(maxPrice < 3000000){
                   maxPrice = 3000000;// Chuyển đổi chuỗi thành int
               }
               if(maxPrice > 9000000){
                   maxPrice = 9000000;// Chuyển đổi chuỗi thành int
               }
           }

           if (checkbox_brand != null && !checkbox_brand.isEmpty()) {
               String[] brandArray = checkbox_brand.split(",");
               brandList = Arrays.stream(brandArray)
                   .map(Integer::valueOf)
                   .collect(Collectors.toList());
           }

           if (checkbox_size != null && !checkbox_size.isEmpty()) {
               String[] sizeArray = checkbox_size.split(",");
               sizeList = Arrays.stream(sizeArray)
                   .map(Integer::valueOf)
                   .collect(Collectors.toList());
           }

           if (sapxep.equals("giagiam")) {
               sapxep_int = 1;
           }
           else if (sapxep.equals("giatang")) {
               sapxep_int = 2;
           }

           String page_all = String.valueOf(service_product.countall());

           List<tbl_product> productPage = service_product.searchProduct(sapxep_int, minPrice, maxPrice,brandList,sizeList);

           if (productPage != null && !productPage.isEmpty()) {

               return ResponseEntity.status(HttpStatus.OK).body(
                   new ResponseObject("ok", "Successfully retrieved", page_all, productPage)
               );
           }

           return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
               new ResponseObject("Fail", "No products found")
           );
    }


    @GetMapping
    public String show_page_login(HttpServletRequest request){
        request.setAttribute("test","ok vo roi");
        return "login";
    }

// về làm tiếp phần này , trả về mảng list ,
    @GetMapping("/chitietsp/{productId}")
    public ResponseEntity<ResponseObject> chitietsanpham(@PathVariable int productId) {

        Map<String, List<?>> list_data = new HashMap<>();

        List<tbl_product> listproduct = new ArrayList<>();
        tbl_product product = service_product.find_product_byID(productId);
        if (product != null) {
            listproduct.add(product);
            product.setTotalView(product.getTotalView() + 1);
            service_product.save_product(product);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ResponseObject("Fail", "No products found")
            );
        }


        List<Integer> pro_soluong = service_product.find_prosl_byid(productId);
        List<tbl_size> list_size = service_size.List_all_findsizefindid(pro_soluong);


        List<tbl_nhanhieu> listnhanhieu = new ArrayList<>();
        tbl_nhanhieu nhanhieu =  service_nhanhieu.findById(product.getNhanhieu().getId_nh());
        if (nhanhieu != null)
            listnhanhieu.add(nhanhieu);


        List<tbl_danhmuc> listdanhmuc =new ArrayList<>();
        tbl_danhmuc danhmuc =  service_danhmuc.findById(product.getDanhmuc().getIdDm());
        if (danhmuc != null)
            listdanhmuc.add(danhmuc);

        List<tbl_product> products_lienquan =service_product.findProductsInCategoryLimitFour(product.getDanhmuc().getIdDm());



        list_data.put("product", listproduct);
        list_data.put("list_size", list_size
        );
        list_data.put("nhanhieu", listnhanhieu);
        list_data.put("danhmuc", listdanhmuc);

        list_data.put("list_product_danhmuc", products_lienquan);



        if (!list_data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", list_data)
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @GetMapping("/chitietsp/{productId}/{sizeid}")
    public ResponseEntity<?> chiTietSP(@PathVariable int productId, @PathVariable int sizeid) {

        Integer maxsl = service_product.max_size_product(productId,sizeid);

        if (maxsl != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", maxsl)
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @GetMapping("/header")
    public ResponseEntity<?> show_header() {

        Map<String, List<?>> list_data = new HashMap<>();


        List<tbl_setting> list_setting = service_setting.showall();

        List<tbl_danhmuc> list_danhmuc = service_danhmuc.showall();
        List<tbl_nhanhieu> list_nhanhieu = service_nhanhieu.showall();



        list_data.put("list_setting", list_setting);
        list_data.put("list_danhmuc", list_danhmuc);
        list_data.put("list_nhanhieu", list_nhanhieu);



        if (list_data != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", list_data)
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }




    @GetMapping("/search-goi-y/{search}")
    public  ResponseEntity<?>  goi_y(@PathVariable String search ) {
        List<tbl_product> products = service_product.searchProducts(search);

        if (products != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", products)
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @GetMapping("/search-iddm-idnh/{iddm}/{idNh}")
    public ResponseEntity<?> searchCC (@PathVariable int iddm, @PathVariable int idNh) {

        tbl_nhanhieu tbl_nhanhieu = service_nhanhieu.findById(idNh);
        tbl_danhmuc tbl_danhmuc = service_danhmuc.findById(iddm);

        Map<String,Object> list_data = new HashMap<>();

        list_data.put("nhanhieu", tbl_nhanhieu);
        list_data.put("danhmuc", tbl_danhmuc);

        if (list_data != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", list_data)
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @GetMapping("/search-idpro-idsize/{idpro}/{idsize}")
    public ResponseEntity<?> searchaa (@PathVariable int idpro, @PathVariable int idsize) {

        tbl_product product = service_product.find_product_byID(idpro);
        tbl_size size = service_size.find_size_by_id(idsize);

        Map<String,Object> list_data = new HashMap<>();

        list_data.put("product", product);
        list_data.put("size", size);

        if (list_data != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", list_data)
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @GetMapping("/show-user/{iduser}")
    public ResponseEntity<?> show_user(@PathVariable int iduser) {

        List<tbl_users> users = new ArrayList<>();
        tbl_users user = service_user.findByid(iduser);
        users.add(user);

        List<tbl_phieuxuat> phieuXuatList = service_phieuxuat.findpageuser(iduser);

        Map<String,List<?>> list_data = new HashMap<>();

        list_data.put("users", users);
        list_data.put("list_phieuXuat", phieuXuatList);
        if (list_data != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", list_data)
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @GetMapping("/show-chitet-px/{id_px}")
    public ResponseEntity<?> show_chitet(@PathVariable int id_px) {

        List<tbl_chitiet_px> phieuXuatList = service_phieuxuat.findchititebyid(id_px);


        if (phieuXuatList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted", "", phieuXuatList)
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }
    @PostMapping("/check-UserEmail")
    public ResponseEntity<?> checkUserEmail(@RequestBody Map<String, Object> logindata) {

        String newEmail = (String) logindata.get("email");
        Integer userId = (Integer) logindata.get("id_user");

        // Kiểm tra xem userId và newEmail có hợp lệ không
        if (userId <= 0 || newEmail == null || newEmail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("Fail", "Invalid userId or email")
            );
        }
        // Kiểm tra xem newEmail có tồn tại khác hay ko

        if(service_user.findbyEmail(newEmail)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("Fail", "exist email")
            );
        }

        if (1==1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Email updated successfully")
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @PostMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody Map<String, Object> logindata) {

        Integer userId = (Integer) logindata.get("id_user");
        String ten_user = (String) logindata.get("ten_user");
        String email = (String) logindata.get("email");
        String diachi = (String) logindata.get("diachi");
        String sodth = (String) logindata.get("sodth");
        if (userId == null || ten_user == null || email == null || diachi == null || sodth == null) {
            // Báo lỗi nếu có giá trị null
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ResponseObject("Fail", "Gia tri trong")
            );
        }

        boolean error = service_user.updateUser(userId,ten_user,email,diachi,sodth);
        if (error) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Updated successfully")
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }

    @PostMapping("/update-avatar-user")
    public ResponseEntity<?> updateavatar(@RequestBody Map<String, Object> logindata) {

        Integer userId = (Integer) logindata.get("id_user");
        String avatar = (String) logindata.get("avatar");

        boolean error = service_user.updateavatar(userId,avatar);
        if (error) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Updated successfully")
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @PostMapping("/update-password-user")
    public ResponseEntity<?> updatepassword(@RequestBody Map<String, Object> logindata) {

        Integer userId = (Integer) logindata.get("id_user");
        String password = (String) logindata.get("password");

        boolean error = service_user.updatepassword(userId,password);
        if (error) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Updated successfully")
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }

}
