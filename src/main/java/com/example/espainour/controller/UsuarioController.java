package com.example.espainour.controller;

import com.example.espainour.dto.UsuarioDTO;
import com.example.espainour.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Obtener todos los usuarios con genero incluido
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getTodosUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.getTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        boolean eliminado = usuarioService.deleteById(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();  // 204 No Content si borró bien
        } else {
            return ResponseEntity.notFound().build();   // 404 si no encontró el usuario
        }
    }

}
