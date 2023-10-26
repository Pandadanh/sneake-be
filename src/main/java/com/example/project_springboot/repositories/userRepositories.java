package com.example.project_springboot.repositories;

import com.example.project_springboot.model.tbl_users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface userRepositories extends JpaRepository<tbl_users,Integer> {
    tbl_users findByEmailAndMatKhau(String Email, String MatKhau);
    boolean existsByIdUser(int Id);
    boolean existsByEmail(String Email);
    tbl_users findByEmailAndIsActive(String email, int Z);
    tbl_users findByEmail(String email);

    tbl_users findByActiveToken(String activeToken);
    tbl_users findByIdUser(int Id);

    tbl_users save(tbl_users tblUsers);


    @Query(value = "SELECT * FROM tbl_users p" +
        " WHERE (p.daxoa = 0) " +
        "AND (:search IS NULL OR p.id_user LIKE CONCAT('%', :search, '%')) " +
        "AND (:search IS NULL OR p.ten_user LIKE CONCAT('%', :search, '%')) " +
        "AND (:trangthaihd IS NULL OR p.trangthai = :trangthaihd)",
        nativeQuery = true)
    Page<tbl_users> findPagedTaiKhoan(String search, String trangthaihd, Pageable pageRequest);




}
