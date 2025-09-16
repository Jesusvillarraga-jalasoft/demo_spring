package com.tonolandia.demo.service.usuario;

import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.repository.usuario.UsuarioPagingRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class UsuarioPagingService {
    private final UsuarioPagingRepo repo;

    public UsuarioPagingService(UsuarioPagingRepo repo) { this.repo = repo; }

    public Page<Usuario> listar(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }
}
