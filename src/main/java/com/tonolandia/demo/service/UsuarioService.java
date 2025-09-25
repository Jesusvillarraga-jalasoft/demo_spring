package com.tonolandia.demo.service;

import com.tonolandia.demo.dto.UsuarioDto;
import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.mapper.UsuarioMapper;
import com.tonolandia.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper; // ModelMapper
    private final UsuarioMapper usuarioMapper; // MapStruct

    // ===== ModelMapper =====
    public UsuarioDto crearConModelMapper(UsuarioDto dto) {
        Usuario entity = modelMapper.map(dto, Usuario.class);
        entity = usuarioRepository.save(entity);
        return modelMapper.map(entity, UsuarioDto.class);
    }

    @Transactional(readOnly = true)
    public List<UsuarioDto> listarConModelMapper() {
        return usuarioRepository.findAll().stream()
                .map(u -> modelMapper.map(u, UsuarioDto.class))
                .toList();
    }

    // ===== MapStruct =====
    public UsuarioDto crearConMapStruct(UsuarioDto dto) {
        Usuario entity = usuarioMapper.toEntity(dto);
        entity = usuarioRepository.save(entity);
        return usuarioMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public List<UsuarioDto> listarConMapStruct() {
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDto).toList();
    }
}
