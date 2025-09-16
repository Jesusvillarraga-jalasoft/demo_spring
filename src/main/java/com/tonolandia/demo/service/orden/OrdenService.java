package com.tonolandia.demo.service.orden;

import com.tonolandia.demo.dto.OrdenCreateDto;
import com.tonolandia.demo.entity.Orden;
import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.repository.orden.OrdenRepository;
import com.tonolandia.demo.repository.usuario.UsuarioJpaRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdenService {
    private final OrdenRepository ordenRepo;
    private final UsuarioJpaRepo usuarioRepo;

    public OrdenService(OrdenRepository ordenRepo, UsuarioJpaRepo usuarioRepo) {
        this.ordenRepo = ordenRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public List<Orden> listarOrdenes() {
        return ordenRepo.findAll();
    }

    public Orden buscarPorId(Long id) {
        return ordenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con id " + id));
    }

    public List<Orden> listarPorUsuario(Long usuarioId) {
        return ordenRepo.findByUsuarioId(usuarioId);
    }

    @Transactional
    public Orden crearOrden(OrdenCreateDto dto) {
        Usuario usuario = usuarioRepo.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.usuarioId()));

        Orden orden = new Orden();
        orden.setDescripcion(dto.descripcion());
        orden.setUsuario(usuario);

        return ordenRepo.save(orden);
    }

    @Transactional
    public Orden actualizarOrden(Long id, OrdenCreateDto dto) {
        Orden existente = buscarPorId(id);
        existente.setDescripcion(dto.descripcion());

        if (!existente.getUsuario().getId().equals(dto.usuarioId())) {
            Usuario usuario = usuarioRepo.findById(dto.usuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.usuarioId()));
            existente.setUsuario(usuario);
        }

        return ordenRepo.save(existente);
    }

    @Transactional
    public void eliminarOrden(Long id) {
        ordenRepo.deleteById(id);
    }
}
