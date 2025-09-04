package com.tonolandia.demo.domain;

import java.util.Set;

public record Boss (String nombre, int nivel, Set<String> resistencias) {}