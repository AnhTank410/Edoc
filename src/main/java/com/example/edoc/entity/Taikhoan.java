package com.example.edoc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Taikhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taikhoan;
    private String matkhau;

    @OneToOne(mappedBy = "taikhoan",cascade = CascadeType.ALL)
    private Hoso hoso;

    @ManyToMany
    private Set<Vaitro> vaitro;
}
