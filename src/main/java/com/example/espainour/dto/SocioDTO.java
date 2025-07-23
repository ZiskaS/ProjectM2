package com.example.espainour.dto;

import com.example.espainour.model.TipoSocio;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class SocioDTO {

    @NotNull(message = "Número de socio es obligatorio")
    private Long socioNumero;

    @NotNull(message = "Cuota mensual es obligatoria")
    @PositiveOrZero(message = "Cuota mensual no puede ser negativa")
    private Double cuotaMensual;

    @NotNull(message = "Tipo de socio es obligatorio")
    private TipoSocio tipoSocio;

    @PastOrPresent(message = "La fecha de pago no puede estar en el futuro")
    private LocalDate fechaPago;

    // --- Getter & Setter ---

    public Long getSocioNumero() {
        return socioNumero;
    }

    public void setSocioNumero(Long socioNumero) {
        this.socioNumero = socioNumero;
    }

    public Double getCuotaMensual() {
        return cuotaMensual;
    }

    public void setCuotaMensual(Double cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }

    public TipoSocio getTipoSocio() {
        return tipoSocio;
    }

    public void setTipoSocio(TipoSocio tipoSocio) {
        this.tipoSocio = tipoSocio;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }
}

