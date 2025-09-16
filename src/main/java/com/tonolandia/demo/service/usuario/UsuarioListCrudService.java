package com.tonolandia.demo.service.usuario;

import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.repository.usuario.UsuarioListCrudRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioListCrudService {
    private final UsuarioListCrudRepo repo;

    public UsuarioListCrudService(UsuarioListCrudRepo repo) { this.repo = repo; }

    public List<Usuario> listar() { return repo.findAll(); }
}
