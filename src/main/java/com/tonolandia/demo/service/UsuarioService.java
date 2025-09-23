package com.tonolandia.demo.service;

import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Crea un nuevo usuario
     */
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Lista solo los usuarios activos (deleted = false)
     */
    public List<Usuario> listActivos() {
        return usuarioRepository.findAll();
    }

    /**
     * Lista todos los usuarios incluyendo eliminados
     */
    public List<Usuario> listTodos() {
        return usuarioRepository.findAllIncludingDeleted();
    }

    /**
     * Lista solo los eliminados (deleted = true)
     */
    public List<Usuario> listEliminados() {
        return usuarioRepository.findAllDeleted();
    }

    /**
     * Soft delete por ID
     */
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Restaura un usuario previamente eliminado
     */
    public void restore(Long id) {
        usuarioRepository.findById(id).ifPresent(u -> {
            u.setDeleted(false);
            u.setDeletedAt(null);
            u.setDeletedBy(null);
            usuarioRepository.save(u);
        });
    }

    /**
     * Usando filtro dinámico para traer usuarios por rol
     */
    public List<Usuario> listByRole(String role) {
            return usuarioRepository.findByRoleUsingFilter(role);
    }
}
