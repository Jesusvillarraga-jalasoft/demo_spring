package com.tonolandia.demo.mapper;

import com.tonolandia.demo.dto.UsuarioDto;
import com.tonolandia.demo.entity.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = { PerfilMapperHelper.class })
public interface UsuarioMapper {

    @Mapping(source = "perfil.id", target = "perfilId")
    UsuarioDto toDto(Usuario entity);

    @InheritInverseConfiguration
    @Mapping(source = "perfilId", target = "perfil", qualifiedByName = "perfilFromId")
    Usuario toEntity(UsuarioDto dto);
}
