package com.tonolandia.demo.repository.usuario;

import com.tonolandia.demo.record.UsuarioConPerfilDto;
import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.entity.UsuarioConPerfilProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioJpaRepo extends JpaRepository<Usuario, Long> {

    // 🔹 Query Methods
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    Optional<Usuario> findByEmail(String email);

    // 🔹 @Query con JQL
    @Query("SELECT u FROM Usuario u WHERE u.activo = true")
    List<Usuario> buscarUsuariosActivos();

    @Query("SELECT u FROM Usuario u WHERE u.nombre LIKE %:nombre%")
    List<Usuario> buscarPorNombreLike(@Param("nombre") String nombre);

    // 🔹 @Query con SQL nativo
    @Query(value = "SELECT * FROM usuarios WHERE email = :email", nativeQuery = true)
    Usuario buscarPorEmailNativo(@Param("email") String email);

    @Query(value = "SELECT * FROM usuarios WHERE activo = true", nativeQuery = true)
    List<Usuario> buscarActivosNativo();

    // 🔹 Named Queries
    List<Usuario> buscarPorDominioEmail(@Param("dominio") String dominio);
    List<Usuario> buscarActivos();

    // 🔹 EntityGraph → forzar fetch de ordenes LAZY
    @EntityGraph(attributePaths = {"ordenes"})
    @Query("SELECT u FROM Usuario u WHERE u.id = :id")
    Usuario findUsuarioConOrdenes(@Param("id") Long id);

    // 🔹 Projection (Interface)
    @Query("SELECT u FROM Usuario u WHERE u.id = :id")
    UsuarioConPerfilProjection findUsuarioConPerfilSoloId(@Param("id") Long id);

    // 🔹 DTO projection
    @Query("SELECT new com.tonolandia.demo.record.UsuarioConPerfilDto(u.id, u.nombre, u.email, p.id) " +
            "FROM Usuario u LEFT JOIN u.perfil p WHERE u.id = :id")
    UsuarioConPerfilDto findUsuarioConPerfilDto(@Param("id") Long id);

    // Proyección usando Query Method
    List<UsuarioConPerfilProjection> findByActivoTrue();
}
