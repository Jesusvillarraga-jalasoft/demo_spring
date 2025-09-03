package com.tonolandia.demo.dto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record ContratoDto(
        UUID id,
        String objetivo,
        int recompensa,
        String estado,
        Set<String> etiquetas,
        List<String> ubicaciones
) {}
