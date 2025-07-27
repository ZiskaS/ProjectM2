package com.example.espainour.controller;

import com.example.espainour.model.Refugiado;
import com.example.espainour.service.RefugiadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/refugiados")
public class RefugiadoController {

    private final RefugiadoService refugiadoService;

    public RefugiadoController(RefugiadoService refugiadoService) {
        this.refugiadoService = refugiadoService;
    }

    @GetMapping
    public List<Refugiado> getAllRefugiados() {
        return refugiadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Refugiado> getRefugiadoById(@PathVariable Long id) {
        Optional<Refugiado> opt = refugiadoService.findById(id);
        return opt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Refugiado> createRefugiado(@RequestBody Refugiado refugiado) {
        Refugiado saved = refugiadoService.crearRefugiado(refugiado);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Refugiado> updateRefugiado(@PathVariable Long id, @RequestBody Refugiado refugiadoDetails) {
        Optional<Refugiado> optionalExisting = refugiadoService.findById(id);
        if (optionalExisting.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Refugiado existing = optionalExisting.get();

        // No permitimos cambiar refugiadoNumero
        if (refugiadoDetails.getRefugiadoNumero() != null &&
                !existing.getRefugiadoNumero().equals(refugiadoDetails.getRefugiadoNumero())) {
            return ResponseEntity.badRequest().build();
        }

        // Actualizamos todos los campos permitidos excepto genero y refugiadoNumero
        existing.setNombre(refugiadoDetails.getNombre());
        existing.setApellidos(refugiadoDetails.getApellidos());
        existing.setEmail(refugiadoDetails.getEmail());
        existing.setTelefono(refugiadoDetails.getTelefono());
        existing.setDocumentoIdentidad(refugiadoDetails.getDocumentoIdentidad());
        existing.setNacionalidad(refugiadoDetails.getNacionalidad());
        existing.setIdioma(refugiadoDetails.getIdioma());
        existing.setEstatusLegal(refugiadoDetails.getEstatusLegal());

        Refugiado updated = refugiadoService.crearRefugiado(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRefugiado(@PathVariable Long id) {
        refugiadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Refugiado> patchRefugiado(@PathVariable Long id, @RequestBody java.util.Map<String, Object> updates) {
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
        return ResponseEntity.ok(updated);
    }
}
