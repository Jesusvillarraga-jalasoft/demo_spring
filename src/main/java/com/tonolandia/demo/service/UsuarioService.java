package com.tonolandia.demo.service;

import com.tonolandia.demo.dto.UsuarioCreateDto;
import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.exception.UsuarioNoEncontradoException;
import com.tonolandia.demo.repository.UsuarioRepository;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;
    private final MessageSource messageSource;

    public UsuarioService(UsuarioRepository repo, MessageSource messageSource) {
        this.repo = repo;
        this.messageSource = messageSource;
    }

    @Transactional
    public Usuario crear(UsuarioCreateDto dto) {
        Usuario u = new Usuario();
        u.setNombre(dto.getNombre());
        u.setEmail(dto.getEmail());
        u.setEdad(dto.getEdad());
        return repo.save(u);
    }

    @Transactional(readOnly = true)
    public Usuario obtenerPorId(Long id, Locale locale) {
        return repo.findById(id).orElseThrow(() -> new UsuarioNoEncontradoException(id));
    }
}
