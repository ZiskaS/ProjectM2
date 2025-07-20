package com.example.espainour.model;
import jakarta.persistence.*;

@Entity
@Table(name = "voluntarios")
@PrimaryKeyJoinColumn(name = "id")
public class Voluntario extends Usuario {

    private String disponibilidad;
    private String habilidades;
    private String areasInteres;

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public String getAreasInteres() {
        return areasInteres;
    }

    public void setAreasInteres(String areasInteres) {
        this.areasInteres = areasInteres;
    }
}