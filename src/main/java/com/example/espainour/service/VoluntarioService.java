package com.example.espainour.service;

import com.example.espainour.model.Voluntario;
import com.example.espainour.repository.VoluntarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoluntarioService {

    private final VoluntarioRepository voluntarioRepository;

    public VoluntarioService(VoluntarioRepository voluntarioRepository) {
        this.voluntarioRepository = voluntarioRepository;
    }

    public List<Voluntario> findAll() {
        return voluntarioRepository.findAll();
    }

    public Optional<Voluntario> findById(Long id) {
        return voluntarioRepository.findById(id);
    }

    public Voluntario save(Voluntario voluntario) {
        return voluntarioRepository.save(voluntario);
    }

    public Voluntario update(Long id, Voluntario voluntarioDetails) {
        return voluntarioRepository.findById(id)
                .map(voluntario -> {
                    voluntario.setNombre(voluntarioDetails.getNombre());
                    voluntario.setAreasInteres(voluntarioDetails.getAreasInteres());
                    voluntario.setTelefono(voluntarioDetails.getTelefono());
                    return voluntarioRepository.save(voluntario);
                })
                .orElseThrow(() -> new RuntimeException("Voluntario no encontrado con id " + id));
    }

    public void deleteById(Long id) {
        voluntarioRepository.deleteById(id);
    }
}