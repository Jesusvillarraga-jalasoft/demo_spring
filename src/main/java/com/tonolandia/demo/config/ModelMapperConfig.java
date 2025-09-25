package com.tonolandia.demo.config;

import com.tonolandia.demo.dto.UsuarioDto;
import com.tonolandia.demo.entity.Perfil;
import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.repository.PerfilRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(PerfilRepository perfilRepository) {
        ModelMapper mm = new ModelMapper();
        mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Entity -> DTO: perfil.id -> perfilId
        TypeMap<Usuario, UsuarioDto> toDto = mm.createTypeMap(Usuario.class, UsuarioDto.class);
        toDto.addMappings(m -> m.map(src -> src.getPerfil().getId(), UsuarioDto::setPerfilId));

        // DTO -> Entity: perfilId (Long) -> Perfil (carga desde repo)
        Converter<Long, Perfil> idToPerfil = ctx -> ctx.getSource() == null ? null
                : perfilRepository.findById(ctx.getSource())
                .orElseThrow(() -> new IllegalArgumentException("Perfil no encontrado: " + ctx.getSource()));

        TypeMap<UsuarioDto, Usuario> toEntity = mm.createTypeMap(UsuarioDto.class, Usuario.class);
        toEntity.addMappings(m -> m.using(idToPerfil).map(UsuarioDto::getPerfilId, Usuario::setPerfil));

        return mm;
    }
}
