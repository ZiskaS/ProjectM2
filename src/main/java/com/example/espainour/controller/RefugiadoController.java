package com.example.espainour.controller;

import com.example.espainour.dto.RefugiadoDTO;
import com.example.espainour.model.Refugiado;
import com.example.espainour.service.RefugiadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/refugiados")
public class RefugiadoController {

    private final RefugiadoService refugiadoService;

    @Autowired
    public RefugiadoController(RefugiadoService refugiadoService) {
        this.refugiadoService = refugiadoService;
    }

    @GetMapping
    public ResponseEntity<List<RefugiadoDTO>> getAllRefugiados() {
        List<Refugiado> list = refugiadoService.findAll();
        List<RefugiadoDTO> dtos = list.stream().map(this::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefugiadoDTO> getRefugiadoById(@PathVariable Long id) {
        return refugiadoService.findById(id)
                .map(ref -> ResponseEntity.ok(toDTO(ref)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RefugiadoDTO> createRefugiado(@Valid @RequestBody RefugiadoDTO dto) {
        Refugiado ref = toEntity(dto);
        Refugiado saved = refugiadoService.crearRefugiado(ref);
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RefugiadoDTO> updateRefugiado(@PathVariable Long id, @Valid @RequestBody RefugiadoDTO dto) {
        return refugiadoService.findById(id).map(existing -> {
            existing.setNacionalidad(dto.getNacionalidad());
            existing.setIdioma(dto.getIdioma());
            existing.setEstatusLegal(dto.getEstatusLegal());
            Refugiado updated = refugiadoService.crearRefugiado(existing);
            return ResponseEntity.ok(toDTO(updated));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRefugiado(@PathVariable Long id) {
        refugiadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private RefugiadoDTO toDTO(Refugiado ref) {
        RefugiadoDTO dto = new RefugiadoDTO();
        dto.setRefugiadoNumero(ref.getRefugiadoNumero());
        dto.setNacionalidad(ref.getNacionalidad());
        dto.setIdioma(ref.getIdioma());
        dto.setEstatusLegal(ref.getEstatusLegal());
        return dto;
    }

    private Refugiado toEntity(RefugiadoDTO dto) {
        Refugiado ref = new Refugiado();
        ref.setRefugiadoNumero(dto.getRefugiadoNumero());
        ref.setNacionalidad(dto.getNacionalidad());
        ref.setIdioma(dto.getIdioma());
        ref.setEstatusLegal(dto.getEstatusLegal());
        return ref;
    }
}
