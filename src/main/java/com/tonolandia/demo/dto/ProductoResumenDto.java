package com.tonolandia.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO proyección: solo código, lote y precio del producto")
public record ProductoResumenDto(

        @Schema(description = "Código del producto", example = "P123")
        String codigo,

        @Schema(description = "Lote del producto", example = "L456")
        String lote,

        @Schema(description = "Precio del producto", example = "499.99")
        Double precio
) {}
