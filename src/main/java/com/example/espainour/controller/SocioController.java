package com.example.espainour.controller;

import com.example.espainour.dto.SocioDTO;
import com.example.espainour.model.Socio;
import com.example.espainour.service.SocioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/socios")
public class SocioController {

    private final SocioService socioService;

    @Autowired
    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    @GetMapping
    public ResponseEntity<List<SocioDTO>> getAllSocios() {
        List<Socio> socios = socioService.findAll();
        List<SocioDTO> dtos = socios.stream().map(this::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocioDTO> getSocioById(@PathVariable Long id) {
        return socioService.findById(id)
                .map(socio -> ResponseEntity.ok(toDTO(socio)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SocioDTO> createSocio(@Valid @RequestBody SocioDTO socioDTO) {
        Socio socio = toEntity(socioDTO);
        Socio saved = socioService.crearSocio(socio);
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocioDTO> updateSocio(@PathVariable Long id, @Valid @RequestBody SocioDTO socioDTO) {
        return socioService.findById(id).map(existing -> {
            existing.setCuotaMensual(socioDTO.getCuotaMensual());
            existing.setTipoSocio(socioDTO.getTipoSocio());
            existing.setFechaPago(socioDTO.getFechaPago());

            Socio updated = socioService.crearSocio(existing);
            return ResponseEntity.ok(toDTO(updated));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocio(@PathVariable Long id) {
        socioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private SocioDTO toDTO(Socio socio) {
        SocioDTO dto = new SocioDTO();
        dto.setSocioNumero(socio.getSocioNumero());
        dto.setCuotaMensual(socio.getCuotaMensual());
        dto.setTipoSocio(socio.getTipoSocio());
        dto.setFechaPago(socio.getFechaPago());
        return dto;
    }

    private Socio toEntity(SocioDTO dto) {
        Socio socio = new Socio();
        socio.setSocioNumero(dto.getSocioNumero());
        socio.setCuotaMensual(dto.getCuotaMensual());
        socio.setTipoSocio(dto.getTipoSocio());
        socio.setFechaPago(dto.getFechaPago());
        return socio;
    }
}
