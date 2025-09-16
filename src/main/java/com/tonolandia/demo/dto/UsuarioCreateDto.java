package com.tonolandia.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para la creación de un usuario")
public record UsuarioCreateDto(

        @Schema(
                description = "Nombre completo del usuario",
                example = "Ana Pérez",
                required = true
        )
        String nombre,

        @Schema(
                description = "Email único del usuario",
                example = "ana@mail.com",
                required = true
        )
        String email
) { }
