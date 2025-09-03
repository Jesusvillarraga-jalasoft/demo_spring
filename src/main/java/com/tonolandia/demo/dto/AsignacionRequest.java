package com.tonolandia.demo.dto;

import java.util.Set;

public record AsignacionRequest(
        String ejecutorCodigo,
        int maxContratos,
        Set<String> etiquetasRequeridas,
        int recompensaMinima
) {}