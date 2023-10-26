package com.example.project_springboot.service;

import com.example.project_springboot.model.tbl_pro_soluong;
import com.example.project_springboot.model.tbl_product;
import com.example.project_springboot.repositories.proSoLuongRepositories;
import com.example.project_springboot.repositories.productRepositories;
import com.example.project_springboot.service.rules.rules_service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class service_product implements rules_service<tbl_product> {

    @Autowired
    private productRepositories productRepositories;

    @Autowired
    private proSoLuongRepositories proSoLuongRepositories;

    public void service_user(){
    }



    public List<tbl_product> showall_product(){
        return productRepositories.findAll();
    }

    public List<tbl_product> showproduct_top4(){
        return productRepositories.findProductsInCategoryOneLimitFour();
    }

    public List<tbl_product> showproduct_iddm1(){
        Pageable pageable = PageRequest.of(0, 4);
        return productRepositories.findTop4ByDanhmucIdDm(1,pageable);
    }

    public List<tbl_product> showproduct_iddm2(){

        Pageable pageable = PageRequest.of(0, 4);
        return productRepositories.findTop4ByDanhmucIdDm(2,pageable);
    }

    @Override
    public List<tbl_product> showall() {
        return productRepositories.findAll();
    }
    public List<tbl_product> find_by(String tenpro,String idnh,String iddm) {
        return productRepositories.findProductsByCriteria(tenpro,idnh,iddm);
    }



    public Page<tbl_product> findPagedProducts(String tenpro, Integer idnhStr, Integer iddmStr, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size); // Vì trang tính từ 0, nên trừ đi 1
        return productRepositories.findPagedProducts(tenpro, idnhStr, iddmStr, pageRequest);
    }

    public int countall() {
        return productRepositories.findAll().size();
    }

//    public Page<tbl_product> service_search_product(int sapxep, int minne, int maxne, List<Integer> checkboxBrand, List<Integer> checkboxSize, int page, int size) {
//        PageRequest pageRequest = PageRequest.of(page - 1, size); // Vì trang tính từ 0, nên trừ đi 1
//        return productRepositories.search_product( sapxep,  minne,maxne,checkboxBrand, checkboxSize,pageRequest);
//    }
public List<tbl_product> searchProduct(int sapxep, int minne, int maxne, List<Integer> checkboxBrand, List<Integer> checkboxSize) {
    List<tbl_product> productList = new ArrayList<>();

    if(maxne < minne){
        return null;
    }
    String a= String.valueOf(minne);
    String b = String.valueOf(maxne);


 //    String b = "400000000";
    if (sapxep == 0) {
        // Sắp xếp theo ID
        productList = productRepositories.findByGiaMoiBetween(a, b);
    } else if (sapxep == 1) {
        // Sắp xếp giảm dần theo giá mới
        productList = productRepositories.findByGiaMoiBetweenOrderByGiaMoiDesc(a, b);
    } else if (sapxep == 2) {
        // Sắp xếp tăng dần theo giá mới
        productList = productRepositories.findByGiaMoiBetweenOrderByGiaMoiAsc(a, b);
    }

    // Lọc theo checkboxBrand nếu được chọn
     if (checkboxBrand != null && !checkboxBrand.isEmpty()) {
        productList = productList.stream()
            .filter(product -> checkboxBrand.contains(product.getNhanhieu().getId_nh()))
            .collect(Collectors.toList());

        if (productList.isEmpty()) {
            return null; // Trả về null nếu danh sách sản phẩm sau khi lọc rỗng.
        }
    }


    // Lọc theo checkboxSize nếu được chọn
    List<Integer> productIds = proSoLuongRepositories.findProductIdsBySizes(checkboxSize);


    if(checkboxSize != null && !checkboxSize.isEmpty()){
        if (!productIds.isEmpty()) {
            productList = productList.stream()
                .filter(productSizeDTO -> productIds.contains(productSizeDTO.getIdPro()))
                .collect(Collectors.toList());
        }
        else
            return  null;
    }



    return productList;
}


public tbl_product find_product_byID(int id){
        return productRepositories.findByIdPro(id);
}


public List<Integer> find_prosl_byid(int id) {return proSoLuongRepositories.findSizeIdsByProductId(id); }

    public void save_product(tbl_product pro){
        productRepositories.save(pro);
    }


    public Integer max_size_product(int idpro,int idsize){return proSoLuongRepositories.findsoluongByProductIdAndSizeId(idpro,idsize);}

    public List<tbl_product> findProductsInCategoryLimitFour(int dm){
        return productRepositories.findProductsInCategoryLimitFour(dm);
    }

    public List<tbl_pro_soluong> findSoluong() {
        return proSoLuongRepositories.findAll();
    }

    public String giamsoluong(int idPro,int id_size,int soluongMuon){
        // Lấy số lượng hiện có từ cơ sở dữ liệu
        tbl_pro_soluong proSoLuong = proSoLuongRepositories.findByProduct_IdProAndSize_IdSize(idPro, id_size);

        if (proSoLuong != null) {
            int soluongHienCo = proSoLuong.getSoLuong();

            // Kiểm tra số lượng có đủ để trừ không
            if (soluongHienCo >= soluongMuon) {
                // Trừ đi số lượng muốn mua
                soluongHienCo -= soluongMuon;

                // Cập nhật số lượng mới vào cơ sở dữ liệu
                proSoLuong.setSoLuong(soluongHienCo);
                proSoLuongRepositories.save(proSoLuong);

                return "Trừ số lượng thành công";
            } else {
                return "Số lượng sản phẩm không đủ";
            }
        } else {
            return "Không tìm thấy sản phẩm";
        }

    }
    public List<tbl_product> searchProducts(String search) {
        Pageable pageable = PageRequest.of(0, 5); // Lấy 6 kết quả đầu tiên

        return productRepositories.findProductsBySearch(search,pageable);
    }

    public Long count() {
       return productRepositories.count();
    }

    public int Sumidpro(int id) {
        return proSoLuongRepositories.Sumidpro(id);
    }


    public String tangsoluong(int idPro,int id_size,int soluongMuon){
        // Lấy số lượng hiện có từ cơ sở dữ liệu
        tbl_pro_soluong proSoLuong = proSoLuongRepositories.findByProduct_IdProAndSize_IdSize(idPro, id_size);

        if (proSoLuong != null) {
            int soluongHienCo = proSoLuong.getSoLuong();

            // Kiểm tra số lượng có đủ để trừ không
            if (soluongHienCo >= soluongMuon) {
                // Trừ đi số lượng muốn mua
                soluongHienCo += soluongMuon;

                // Cập nhật số lượng mới vào cơ sở dữ liệu
                proSoLuong.setSoLuong(soluongHienCo);
                proSoLuongRepositories.save(proSoLuong);

                return "Trừ số lượng thành công";
            } else {
                return "Số lượng sản phẩm không đủ";
            }
        } else {
            return "Không tìm thấy sản phẩm";
        }

    }

    public List<tbl_pro_soluong> findSoluongByIdPro(int idPro) {
        return proSoLuongRepositories.findAllByProduct_IdPro(idPro);
    }
}
