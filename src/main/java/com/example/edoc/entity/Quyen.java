package com.example.edoc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Quyen {
    @Id
    private int id;
    private String ten;
    private String description;
}
