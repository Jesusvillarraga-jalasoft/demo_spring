package com.tonolandia.demo.dto;

import java.time.Duration;
import java.util.List;

public record FightResult(String boss, boolean victoria, List<String> eventos, Duration duracion) {}
