package com.tonolandia.demo.domain;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record Contrato(
        UUID id,
        String objetivo,
        int recompensa,
        Estado estado,
        Set<String> etiquetas,
        List<String> ubicaciones
) {}
