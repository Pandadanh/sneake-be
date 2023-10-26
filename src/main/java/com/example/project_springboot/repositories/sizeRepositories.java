
package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_size;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface sizeRepositories extends JpaRepository<tbl_size,Integer> {

    @Query("SELECT s FROM tbl_size s WHERE s.idSize IN :idSizes")
    List<tbl_size> findByIdSize(@Param("idSizes") List<Integer> idSizes);


    tbl_size findById(int ID);

    List<tbl_size> findAllByDaxoaNot(int i);

    List<tbl_size> findAllByDaxoaNotAndIdSizeOrSizeContaining(int daxoa, int idSize, String search);

    @Query(value = "SELECT * FROM tbl_size WHERE daxoa <> 1 LIMIT :size OFFSET :offset", nativeQuery = true)
    List<tbl_size> findPagedSizes(@Param("size") int size, @Param("offset") int offset);

    @Query(value = "SELECT * FROM tbl_size WHERE daxoa <> 1 AND (id_size LIKE %:search% OR size LIKE %:search%) LIMIT :size OFFSET :offset", nativeQuery = true)
    List<tbl_size> findPagedSizesBySearch(String search, int size, int offset);

    @Query(value = "SELECT COUNT(*) FROM tbl_size WHERE daxoa <> 1 AND (id_size LIKE %:search% OR size LIKE %:search%)", nativeQuery = true)
    int countSizesBySearch(String search);

    @Query(value = "SELECT * FROM tbl_size WHERE daxoa <> 1 AND size LIKE %:search%", nativeQuery = true)
    tbl_size findBySize(@Param("search") String search);

}