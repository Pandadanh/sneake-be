package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_nhomquyen;
import com.example.project_springboot.model.tbl_phanquyen;
import com.example.project_springboot.model.tbl_quyen;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface phanquyenRepositories  extends JpaRepository<tbl_phanquyen,Integer> {

    List<tbl_phanquyen> findByNhomquyen(tbl_nhomquyen nhomquyen);

    List<tbl_phanquyen> findAllByNhomquyen(tbl_nhomquyen nhomquuyen);

    boolean existsByQuyen(tbl_quyen quyen);

    boolean existsByNhomquyenAndQuyen(tbl_nhomquyen nhomquyen, tbl_quyen quyen);

}