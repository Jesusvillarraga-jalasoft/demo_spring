package com.tonolandia.demo.mapper;

import com.tonolandia.demo.entity.Perfil;
import com.tonolandia.demo.repository.PerfilRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class PerfilMapperHelper {
    private final PerfilRepository perfilRepository;

    public PerfilMapperHelper(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @Named("perfilFromId")
    public Perfil perfilFromId(Long id) {
        if (id == null) return null;
        return perfilRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Perfil no encontrado: " + id));
    }
}
