package com.tonolandia.demo.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record AsignacionResponse(
        String ejecutorCodigo,
        Instant fecha,
        List<UUID> contratosAsignados
) {}
