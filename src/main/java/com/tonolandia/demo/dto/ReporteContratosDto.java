package com.tonolandia.demo.dto;

import java.util.List;
import java.util.Map;

public record ReporteContratosDto(
        long total,
        Map<String, Long> porEstado,
        Map<String, Long> porEtiqueta,
        List<String> topUbicaciones
) {}