package com.example.edoc.repository;

import com.example.edoc.entity.Vaitro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface VaitroReponsitory extends JpaRepository<Vaitro,Long> {
    Optional<Vaitro> findByTen(String ten);
}
