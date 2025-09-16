package com.tonolandia.demo.service.usuario;

import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.entity.UsuarioConPerfilProjection;
import com.tonolandia.demo.record.UsuarioConPerfilDto;
import com.tonolandia.demo.repository.usuario.UsuarioJpaRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioJpaService {
    private final UsuarioJpaRepo repo;

    public UsuarioJpaService(UsuarioJpaRepo repo) {
        this.repo = repo;
    }

    // 🔹 Query Methods
    public List<Usuario> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return repo.findByEmail(email);
    }

    // 🔹 @Query con JQL
    public List<Usuario> buscarActivos() {
        return repo.buscarUsuariosActivos();
    }

    public List<Usuario> buscarPorNombreLike(String nombre) {
        return repo.buscarPorNombreLike(nombre);
    }

    // 🔹 @Query con SQL nativo
    public Usuario buscarPorEmailNativo(String email) {
        return repo.buscarPorEmailNativo(email);
    }

    public List<Usuario> buscarActivosNativo() {
        return repo.buscarActivosNativo();
    }

    // 🔹 Named Queries
    public List<Usuario> buscarPorDominio(String dominio) {
        return repo.buscarPorDominioEmail(dominio);
    }

    public List<Usuario> buscarActivosNamed() {
        return repo.buscarActivos();
    }

    // 🔹 EntityGraph (forzar fetch de ordenes)
    public Usuario buscarUsuarioConOrdenes(Long id) {
        return repo.findUsuarioConOrdenes(id);
    }

    // 🔹 Projection (Interface)
    public UsuarioConPerfilProjection buscarUsuarioConPerfilSoloId(Long id) {
        return repo.findUsuarioConPerfilSoloId(id);
    }

    // 🔹 DTO projection
    public UsuarioConPerfilDto buscarUsuarioConPerfilDto(Long id) {
        return repo.findUsuarioConPerfilDto(id);
    }

    // 🔹 Demostración de @Transactional y rollback
    @Transactional
    public void crearUsuariosConTransaccion() {
        Usuario u1 = new Usuario();
        u1.setNombre("Ana");
        u1.setEmail("ana@mail.com");
        repo.save(u1);

        Usuario u2 = new Usuario();
        u2.setNombre("Luis");
        u2.setEmail("luis@mail.com");
        repo.save(u2);

        // Forzamos error → rollback total
        if (true) throw new RuntimeException("Error simulado");
    }

    // 🔹 Sin @Transactional (persistencia parcial)
    public void crearUsuariosSinTransaccion() {
        Usuario u1 = new Usuario();
        u1.setNombre("Pedro");
        u1.setEmail("pedro@mail.com");
        repo.save(u1);

        Usuario u2 = new Usuario();
        u2.setNombre("Marta");
        u2.setEmail("marta@mail.com");
        repo.save(u2);

        // Forzamos error → Pedro se guarda, Marta no
        if (true) throw new RuntimeException("Error simulado");
    }

    // 🔹 Demostración de flush(), saveAndFlush(), saveAllAndFlush(), deleteAllInBatch()
    @Transactional
    public void demoFlushAndBatch() {
        Usuario u1 = new Usuario();
        u1.setNombre("Carlos");
        u1.setEmail("carlos@mail.com");
        repo.save(u1);

        repo.flush(); // ejecuta INSERT inmediato

        Usuario u2 = new Usuario();
        u2.setNombre("Sofía");
        u2.setEmail("sofia@mail.com");
        repo.saveAndFlush(u2); // INSERT inmediato

        List<Usuario> usuarios = List.of(
                new Usuario() {{
                    setNombre("Alicia");
                    setEmail("alicia@mail.com");
                }},
                new Usuario() {{
                    setNombre("Diego");
                    setEmail("diego@mail.com");
                }}
        );

        repo.saveAllAndFlush(usuarios); // múltiples INSERT inmediatos

        repo.deleteAllInBatch(); // DELETE masivo sin callbacks
    }
}
