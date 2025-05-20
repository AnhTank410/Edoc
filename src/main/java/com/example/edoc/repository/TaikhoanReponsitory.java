package com.example.edoc.repository;

import com.example.edoc.entity.Taikhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaikhoanReponsitory extends JpaRepository<Taikhoan,Integer> {
    boolean existsByTaikhoan(String taikhoan);
    Optional<Taikhoan> findByTaikhoan(String taikhoan);
}
