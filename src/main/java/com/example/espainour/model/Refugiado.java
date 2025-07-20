package com.example.espainour.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "refugiados")
@PrimaryKeyJoinColumn(name = "id")
public class Refugiado extends Usuario {
    private String nacionalidad;
    private String idioma;
    private String estatusLegal;
}