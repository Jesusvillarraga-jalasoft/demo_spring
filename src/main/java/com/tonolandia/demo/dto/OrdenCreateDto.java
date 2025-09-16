package com.tonolandia.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para crear o actualizar una orden")
public record OrdenCreateDto(

        @Schema(description = "Descripción de la orden", example = "Compra de 3 monitores")
        String descripcion,

        @Schema(description = "ID del usuario al que pertenece la orden", example = "1")
        Long usuarioId
) {}
