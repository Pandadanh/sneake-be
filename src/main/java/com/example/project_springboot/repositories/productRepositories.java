package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface productRepositories extends JpaRepository<tbl_product,Integer> {
    tbl_product findByTenPro(String name);

    @Query(value = "SELECT * FROM tbl_product WHERE id_dm = 1 LIMIT 4", nativeQuery = true)
    List<tbl_product> findProductsInCategoryOneLimitFour();

    @Query("SELECT p FROM tbl_product p WHERE p.danhmuc.idDm = :idDm ")
    List<tbl_product> findTop4ByIdDm(@Param("idDm") int idDm);

    List<tbl_product> findTop4ByDanhmucIdDm(int idDm, Pageable pageable);

    @Query(value = "SELECT * FROM tbl_product " +
        "JOIN tbl_danhmuc ON tbl_danhmuc.id_dm = tbl_product.id_dm " +
        "WHERE ten_pro REGEXP ?1 AND id_nh REGEXP ?2 AND tbl_product.id_dm REGEXP ?3 " +
        "ORDER BY giamoi DESC LIMIT 6", nativeQuery = true)
    List<tbl_product> findProductsByCriteria(String tenProPattern, String idNhPattern, String idDmPattern);


    @Query("SELECT p FROM tbl_product p" +
        " WHERE (:tenpro IS NULL OR p.tenPro LIKE %:tenpro%) " +
        "AND (:idnhStr IS NULL OR p.nhanhieu.idNh = :idnhStr) " +
        "AND (:iddmStr IS NULL OR p.danhmuc.idDm = :iddmStr)")
    Page<tbl_product> findPagedProducts(String tenpro, Integer idnhStr, Integer iddmStr, Pageable pageable);

    List<tbl_product> findByGiaMoiBetween(String minne, String maxne);

    List<tbl_product> findByGiaMoiBetweenOrderByGiaMoiAsc(String minne, String maxne);

    List<tbl_product> findByGiaMoiBetweenOrderByGiaMoiDesc(String minne, String maxne);


    tbl_product findByIdPro(int idPro);

    @Query(value = "SELECT * FROM tbl_product WHERE id_dm = ?1 LIMIT 4", nativeQuery = true)
    List<tbl_product> findProductsInCategoryLimitFour(int categoryId);
//
    @Query("SELECT p FROM tbl_product p " +
    "JOIN tbl_nhanhieu nh ON p.nhanhieu.idNh = nh.idNh " +
    "JOIN tbl_danhmuc dm ON p.danhmuc.idDm = dm.idDm " +
    "WHERE p.tenPro LIKE %:search%")
    List<tbl_product> findProductsBySearch(@Param("search") String search, Pageable pageable);

}