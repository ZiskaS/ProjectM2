package com.example.espainour.model;
import jakarta.persistence.*;

@Entity
@Table(name = "socios")
@PrimaryKeyJoinColumn(name = "id")
public class Socio extends Usuario {
    private Double cuotaMensual;
    private String tipoSocio;
    private String fechaPago;
}