package com.example.espainour.controller;

import com.example.espainour.model.Voluntario;
import com.example.espainour.service.VoluntarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voluntarios")
public class VoluntarioController {

    private final VoluntarioService voluntarioService;

    public VoluntarioController(VoluntarioService voluntarioService) {
        this.voluntarioService = voluntarioService;
    }

    @GetMapping
    public List<Voluntario> getAllVoluntarios() {
        return voluntarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voluntario> getVoluntarioById(@PathVariable Long id) {
        return voluntarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Voluntario createVoluntario(@RequestBody Voluntario voluntario) {
        return voluntarioService.save(voluntario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voluntario> updateVoluntario(@PathVariable Long id, @RequestBody Voluntario voluntarioDetails) {
        try {
            Voluntario updated = voluntarioService.update(id, voluntarioDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoluntario(@PathVariable Long id) {
        voluntarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}