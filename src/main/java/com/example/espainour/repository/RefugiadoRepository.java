package com.example.espainour.repository;

import com.example.espainour.model.Refugiado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RefugiadoRepository extends JpaRepository<Refugiado, Long> {
    List<Refugiado> findByNombreContainingIgnoreCase(String nombre);
}
