package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_pro_soluong;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface proSoLuongRepositories extends JpaRepository<tbl_pro_soluong,Integer> {

    @Query("SELECT DISTINCT ps.product.idPro FROM tbl_pro_soluong ps WHERE ps.size.idSize IN :idSizes")
    List<Integer> findProductIdsBySizes(@Param("idSizes") List<Integer> idSizes);

//    @Query("SELECT DISTINCT ps.idPro FROM tbl_pro_soluong ps WHERE ps.idSize IN :idSizes")
//    List<Integer> findProductIdsBySizes(@Param("idSizes") List<Integer> idSizes);

//    @Query("SELECT NEW com.example.danggggggg.dto.tbl_pro_soluong(ps.idPro) FROM tbl_pro_soluong ps WHERE ps.idSize IN :idSizes")
//    List<tbl_pro_soluong> findProductIdsBySizes(@Param("idSizes") List<Integer> idSizes);

//    @Query("SELECT NEW com.example.danggggggg.model.tbl_pro_soluong(ps.product.idPro, ps.size.idSize, ps.soLuong) FROM tbl_pro_soluong ps")
//    List<tbl_pro_soluong> findAllWithSelectedFields();

    @Query("SELECT DISTINCT ps.size.idSize FROM tbl_pro_soluong ps WHERE ps.product.idPro = :idPro AND soluong != 0")
    List<Integer> findSizeIdsByProductId(@Param("idPro") int idPro);

    @Query("SELECT ts.soLuong FROM tbl_pro_soluong ts WHERE ts.product.idPro = :idPro AND ts.size.idSize = :idSize")
    int findsoluongByProductIdAndSizeId(@Param("idPro") int idPro, @Param("idSize") int idSize);


//    @Query("UPDATE tbl_pro_soluong ps SET ps.soluong = ps.soluong - 1 WHERE ps.product.idPro = :idPro AND ps.size.idSize = :idSize")
//    void GiamSoluong(@Param("idPro") int idPro, @Param("idSize") int idSize);
    tbl_pro_soluong findByProduct_IdProAndSize_IdSize(int idPro,int id_size);

//    @Query(value = "SELECT * FROM tbl_product WHERE id_dm = 1 LIMIT 4", nativeQuery = true)

    @Query(value = "SELECT SUM(ps.soluong) FROM tbl_pro_soluong ps WHERE ps.id_pro = :idPro", nativeQuery = true)
    Integer Sumidpro(@Param("idPro") int idPro);


    List<tbl_pro_soluong> findAllByProduct_IdPro(int idPro);
}
