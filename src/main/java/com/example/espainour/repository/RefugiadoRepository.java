package com.example.espainour.repository;

import com.example.espainour.model.Refugiado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RefugiadoRepository extends JpaRepository<Refugiado, Long> {
    List<Refugiado> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT MAX(r.refugiadoNumero) FROM Refugiado r")
    Long findMaxRefugiadoNumero();
}

