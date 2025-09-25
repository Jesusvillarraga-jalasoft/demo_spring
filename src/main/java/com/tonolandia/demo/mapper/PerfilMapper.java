package com.tonolandia.demo.mapper;

import com.tonolandia.demo.dto.PerfilDto;
import com.tonolandia.demo.entity.Perfil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PerfilMapper {
    PerfilDto toDto(Perfil entity);
    Perfil toEntity(PerfilDto dto);
}
