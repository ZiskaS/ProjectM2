package com.example.espainour.controller;

import com.example.espainour.dto.RefugiadoDTO;
import com.example.espainour.model.Refugiado;
import com.example.espainour.service.RefugiadoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/refugiados")
public class RefugiadoController {

    private final RefugiadoService refugiadoService;

    public RefugiadoController(RefugiadoService refugiadoService) {
        this.refugiadoService = refugiadoService;
    }

    private RefugiadoDTO toDTO(Refugiado r) {
        RefugiadoDTO dto = new RefugiadoDTO();
        dto.setId(r.getId());
        dto.setRefugiadoNumero(r.getRefugiadoNumero());
        dto.setNombre(r.getNombre());
        dto.setApellidos(r.getApellidos());
        dto.setEmail(r.getEmail());
        dto.setTelefono(r.getTelefono());
        dto.setDocumentoIdentidad(r.getDocumentoIdentidad());
        dto.setNacionalidad(r.getNacionalidad());
        dto.setIdioma(r.getIdioma());
        dto.setEstatusLegal(r.getEstatusLegal());
        dto.setFechaRegistro(r.getFechaRegistro());
        return dto;
    }

    private Refugiado toEntity(RefugiadoDTO dto) {
        Refugiado r = new Refugiado();
        r.setId(dto.getId());
        r.setRefugiadoNumero(dto.getRefugiadoNumero());
        r.setNombre(dto.getNombre());
        r.setApellidos(dto.getApellidos());
        r.setEmail(dto.getEmail());
        r.setTelefono(dto.getTelefono());
        r.setDocumentoIdentidad(dto.getDocumentoIdentidad());
        r.setNacionalidad(dto.getNacionalidad());
        r.setIdioma(dto.getIdioma());
        r.setEstatusLegal(dto.getEstatusLegal());
        return r;
    }

    @GetMapping
    public List<RefugiadoDTO> getAllRefugiados() {
        List<Refugiado> refugiados = refugiadoService.findAll();
        return refugiados.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefugiadoDTO> getRefugiadoById(@PathVariable Long id) {
        Optional<Refugiado> opt = refugiadoService.findById(id);
        return opt.map(ref -> ResponseEntity.ok(toDTO(ref)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RefugiadoDTO> createRefugiado(@Valid @RequestBody RefugiadoDTO refugiadoDTO) {
        Refugiado refugiado = toEntity(refugiadoDTO);
        Refugiado saved = refugiadoService.crearRefugiado(refugiado);
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RefugiadoDTO> updateRefugiado(@PathVariable Long id, @Valid @RequestBody RefugiadoDTO refugiadoDTO) {
        Optional<Refugiado> optionalExisting = refugiadoService.findById(id);
        if (optionalExisting.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Refugiado existing = optionalExisting.get();

        // No permitimos cambiar refugiadoNumero
        if (refugiadoDTO.getRefugiadoNumero() != null &&
                !existing.getRefugiadoNumero().equals(refugiadoDTO.getRefugiadoNumero())) {
            return ResponseEntity.badRequest().build();
        }

        // Actualizamos campos excepto refugiadoNumero y genero
        existing.setNombre(refugiadoDTO.getNombre());
        existing.setApellidos(refugiadoDTO.getApellidos());
        existing.setEmail(refugiadoDTO.getEmail());
        existing.setTelefono(refugiadoDTO.getTelefono());
        existing.setDocumentoIdentidad(refugiadoDTO.getDocumentoIdentidad());
        existing.setNacionalidad(refugiadoDTO.getNacionalidad());
        existing.setIdioma(refugiadoDTO.getIdioma());
        existing.setEstatusLegal(refugiadoDTO.getEstatusLegal());

        Refugiado updated = refugiadoService.crearRefugiado(existing);
        return ResponseEntity.ok(toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRefugiado(@PathVariable Long id) {
        refugiadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RefugiadoDTO> patchRefugiado(@PathVariable Long id, @RequestBody java.util.Map<String, Object> updates) {
        Optional<Refugiado> optionalExisting = refugiadoService.findById(id);
        if (optionalExisting.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Refugiado existing = optionalExisting.get();

        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre":
                    existing.setNombre((String) value);
                    break;
                case "apellidos":
                    existing.setApellidos((String) value);
                    break;
                case "email":
                    existing.setEmail((String) value);
                    break;
                case "telefono":
                    existing.setTelefono((String) value);
                    break;
                case "documentoIdentidad":
                    existing.setDocumentoIdentidad((String) value);
                    break;
                case "nacionalidad":
                    existing.setNacionalidad((String) value);
                    break;
                case "idioma":
                    existing.setIdioma((String) value);
                    break;
                case "estatusLegal":
                    existing.setEstatusLegal(Enum.valueOf(com.example.espainour.model.EstatusLegal.class, (String) value));
                    break;
                // NO se cambia refugiadoNumero ni genero
            }
        });

        Refugiado updated = refugiadoService.crearRefugiado(existing);
        return ResponseEntity.ok(toDTO(updated));
    }
}
