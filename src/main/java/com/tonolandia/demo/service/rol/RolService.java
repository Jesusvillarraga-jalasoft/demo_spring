package com.tonolandia.demo.service.rol;

import com.tonolandia.demo.dto.RolCreateDto;
import com.tonolandia.demo.entity.Rol;
import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.repository.rol.RolRepository;
import com.tonolandia.demo.repository.usuario.UsuarioJpaRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RolService {
    private final RolRepository rolRepo;
    private final UsuarioJpaRepo usuarioRepo; // para manejar la relación con usuarios

    public RolService(RolRepository rolRepo, UsuarioJpaRepo usuarioRepo) {
        this.rolRepo = rolRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public List<Rol> listarRoles() {
        return rolRepo.findAll();
    }

    public Rol buscarPorId(Long id) {
        return rolRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id " + id));
    }

    @Transactional
    public Rol crearRol(RolCreateDto dto) {
        Rol rol = new Rol();
        rol.setNombre(dto.nombre());

        if (dto.usuarioIds() != null && !dto.usuarioIds().isEmpty()) {
            Set<Usuario> usuarios = new HashSet<>(usuarioRepo.findAllById(dto.usuarioIds()));
            rol.setUsuarios(usuarios);
        }

        return rolRepo.save(rol);
    }

    @Transactional
    public Rol actualizarRol(Long id, RolCreateDto dto) {
        Rol existente = buscarPorId(id);
        existente.setNombre(dto.nombre());

        if (dto.usuarioIds() != null) {
            Set<Usuario> usuarios = new HashSet<>(usuarioRepo.findAllById(dto.usuarioIds()));
            existente.setUsuarios(usuarios);
        }

        return rolRepo.save(existente);
    }

    @Transactional
    public void eliminarRol(Long id) {
        rolRepo.deleteById(id);
    }
}
