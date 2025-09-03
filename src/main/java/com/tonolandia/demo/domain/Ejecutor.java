package com.tonolandia.demo.domain;

import java.util.Set;

public record Ejecutor(
        String codigo,
        int nivel,
        Set<String> habilidades
) {}