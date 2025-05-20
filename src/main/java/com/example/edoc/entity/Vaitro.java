package com.example.edoc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
public class Vaitro {
    @Id
    private int id;
    private String ten;

    @ManyToMany
    Set<Quyen> quyen;

}
