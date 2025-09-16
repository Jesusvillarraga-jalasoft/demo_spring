package com.tonolandia.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para crear o actualizar un perfil")
public record PerfilCreateDto(

        @Schema(description = "Biografía del usuario", example = "Amante del desarrollo con Spring Boot")
        String bio,

        @Schema(description = "ID del usuario asociado", example = "1")
        Long usuarioId
) {}
