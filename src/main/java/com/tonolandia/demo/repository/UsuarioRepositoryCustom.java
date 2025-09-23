package com.tonolandia.demo.repository;

import com.tonolandia.demo.entity.Usuario;

import java.util.List;

public interface UsuarioRepositoryCustom {
    List<Usuario> findByRoleUsingFilter(String role);
}
