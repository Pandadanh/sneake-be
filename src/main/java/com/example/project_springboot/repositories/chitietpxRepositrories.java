package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_chitiet_px;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface chitietpxRepositrories  extends JpaRepository<tbl_chitiet_px,Integer> {

 List<tbl_chitiet_px> findAllByPhieuxuat_IdPx(int idPx);

 @Query(value = "SELECT c.id_pro,c.soluong,  c.giaban  " +
     "FROM tbl_chitiet_px c " +
     "WHERE c.id_px IN :idPxList ", nativeQuery = true)
 List<Object[]> getProductSalesInfo(@Param("idPxList") List<Integer> idPxList);


}
