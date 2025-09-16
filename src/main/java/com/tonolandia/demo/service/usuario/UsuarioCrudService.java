package com.tonolandia.demo.service.usuario;

import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.repository.usuario.UsuarioCrudRepo;
import org.springframework.stereotype.Service;

@Service
public class UsuarioCrudService {
    private final UsuarioCrudRepo repo;

    public UsuarioCrudService(UsuarioCrudRepo repo) { this.repo = repo; }

    public Iterable<Usuario> listar() { return repo.findAll(); }
}
