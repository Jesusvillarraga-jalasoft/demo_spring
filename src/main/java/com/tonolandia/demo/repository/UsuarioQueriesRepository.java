package com.tonolandia.demo.repository;

import com.tonolandia.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioQueriesRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where type(u) = UsuarioAdmin")
    List<Usuario> findAdminsWithType();

    //@Query("select u from Usuario u where u instance of UsuarioAdmin")
    //List<Usuario> findAdminsWithInstanceOf();
}