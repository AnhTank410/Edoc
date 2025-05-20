package com.example.edoc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Taikhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taikhoan;
    private String matkhau;

    @OneToOne(mappedBy = "taikhoan",cascade = CascadeType.ALL)
    private Hoso hoso;

    @ManyToMany
    Set<Vaitro> vaitro;
}
