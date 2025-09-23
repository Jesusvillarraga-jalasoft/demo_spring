package com.tonolandia.demo.repository;

import com.tonolandia.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryCustom {

    @Query("select u from Usuario u")
    List<Usuario> findAllIncludingDeleted();

    @Query("select u from Usuario u where u.deleted = true")
    List<Usuario> findAllDeleted();
}
