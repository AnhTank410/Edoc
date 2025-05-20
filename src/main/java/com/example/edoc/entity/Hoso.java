package com.example.edoc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Hoso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hoten;
    private String dienthoai;
    private String email;
    private String ngaysinh;

    @OneToOne
    @JoinColumn(name = "taikhoan_id")
    private Taikhoan taikhoan;
}
