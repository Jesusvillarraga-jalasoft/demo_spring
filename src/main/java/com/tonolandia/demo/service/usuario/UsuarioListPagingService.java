package com.tonolandia.demo.service.usuario;

import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.repository.usuario.UsuarioListPagingRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioListPagingService {
    private final UsuarioListPagingRepo repo;

    public UsuarioListPagingService(UsuarioListPagingRepo repo) { this.repo = repo; }

    public Page<Usuario> listar(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
