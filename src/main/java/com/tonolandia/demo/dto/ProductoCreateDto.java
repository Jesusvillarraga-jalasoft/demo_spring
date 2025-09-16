package com.tonolandia.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para crear o actualizar un producto con ID compuesto")
public record ProductoCreateDto(

        @Schema(description = "Código del producto", example = "P123")
        String codigo,

        @Schema(description = "Lote del producto", example = "L456")
        String lote,

        @Schema(description = "Nombre del producto", example = "Monitor 27 pulgadas")
        String nombre,

        @Schema(description = "Precio del producto", example = "999.99")
        Double precio
) {}