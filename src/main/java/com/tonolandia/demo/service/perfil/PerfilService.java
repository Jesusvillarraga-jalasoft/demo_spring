package com.tonolandia.demo.service.perfil;

import com.tonolandia.demo.dto.PerfilCreateDto;
import com.tonolandia.demo.entity.Perfil;
import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.repository.perfil.PerfilRepository;
import com.tonolandia.demo.repository.usuario.UsuarioJpaRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerfilService {
    private final PerfilRepository perfilRepo;
    private final UsuarioJpaRepo usuarioRepo; // usamos el repo de Usuario

    public PerfilService(PerfilRepository perfilRepo, UsuarioJpaRepo usuarioRepo) {
        this.perfilRepo = perfilRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public List<Perfil> listarPerfiles() {
        return perfilRepo.findAll();
    }

    public Perfil buscarPorId(Long id) {
        return perfilRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado con id " + id));
    }

    @Transactional
    public Perfil crearPerfil(PerfilCreateDto dto) {
        Usuario usuario = usuarioRepo.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.usuarioId()));

        Perfil perfil = new Perfil();
        perfil.setBio(dto.bio());
        perfil.setUsuario(usuario);

        return perfilRepo.save(perfil);
    }

    @Transactional
    public Perfil actualizarPerfil(Long id, PerfilCreateDto dto) {
        Perfil existente = buscarPorId(id);
        existente.setBio(dto.bio());

        if (!existente.getUsuario().getId().equals(dto.usuarioId())) {
            Usuario usuario = usuarioRepo.findById(dto.usuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.usuarioId()));
            existente.setUsuario(usuario);
        }
        return perfilRepo.save(existente);
    }

    @Transactional
    public void eliminarPerfil(Long id) {
        perfilRepo.deleteById(id);
    }
}
