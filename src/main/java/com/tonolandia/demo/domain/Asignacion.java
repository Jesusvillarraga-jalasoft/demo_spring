package com.tonolandia.demo.domain;

import java.time.Instant;
import java.util.UUID;

public record Asignacion(
        UUID contratoId,
        String ejecutorCodigo,
        Instant fecha
) {}
