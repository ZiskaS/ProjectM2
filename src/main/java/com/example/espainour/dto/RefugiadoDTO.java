package com.example.espainour.dto;

import com.example.espainour.model.EstatusLegal;
import jakarta.validation.constraints.*;

public class RefugiadoDTO {

    @NotNull(message = "Número de refugiado es obligatorio")
    private Long refugiadoNumero;

    @NotBlank(message = "Nacionalidad es obligatoria")
    private String nacionalidad;

    @NotBlank(message = "Idioma es obligatorio")
    private String idioma;

    @NotNull(message = "Estatus legal es obligatorio")
    private EstatusLegal estatusLegal;

    // --- Getter & Setter ---

    public Long getRefugiadoNumero() {
        return refugiadoNumero;
    }

    public void setRefugiadoNumero(Long refugiadoNumero) {
        this.refugiadoNumero = refugiadoNumero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public EstatusLegal getEstatusLegal() {
        return estatusLegal;
    }

    public void setEstatusLegal(EstatusLegal estatusLegal) {
        this.estatusLegal = estatusLegal;
    }
}
