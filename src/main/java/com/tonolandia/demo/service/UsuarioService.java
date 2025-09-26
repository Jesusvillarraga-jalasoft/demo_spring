package com.tonolandia.demo.service;

import com.tonolandia.demo.dto.UsuarioCreateDto;
import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Usuario crear(@Valid UsuarioCreateDto dto) {
        // Mapeo manual sencillo (para demo)
        Usuario u = new Usuario();
        u.setNombre(dto.getNombre());
        u.setEmail(dto.getEmail());
        u.setEdad(dto.getEdad());
        u.setFechaRegistro(dto.getFechaRegistro());
        u.setRol(dto.getPerfil().getRol());

        return repo.save(u);
    }

    public boolean existePorEmail(@NotBlank @Email String email) {
        return repo.existsByEmail(email);
    }
}
