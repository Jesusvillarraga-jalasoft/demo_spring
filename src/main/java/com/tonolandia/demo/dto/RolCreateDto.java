package com.tonolandia.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Schema(description = "DTO para crear o actualizar un rol")
public record RolCreateDto(

        @Schema(description = "Nombre del rol", example = "ADMIN")
        String nombre,

        @Schema(description = "IDs de los usuarios asociados", example = "[1, 2, 3]")
        Set<Long> usuarioIds
) {}