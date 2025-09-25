package com.tonolandia.demo.service;

import com.tonolandia.demo.entity.Perfil;
import com.tonolandia.demo.dto.PerfilDto;
import com.tonolandia.demo.mapper.PerfilMapper;
import com.tonolandia.demo.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final PerfilMapper perfilMapper;

    public PerfilDto crearPerfil(PerfilDto dto) {
        Perfil entity = perfilMapper.toEntity(dto);
        entity = perfilRepository.save(entity);
        return perfilMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PerfilDto> listarPerfiles() {
        return perfilRepository.findAll().stream()
                .map(perfilMapper::toDto)
                .toList();
    }
}
