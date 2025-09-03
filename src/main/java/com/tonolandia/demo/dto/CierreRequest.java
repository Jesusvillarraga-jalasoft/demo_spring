package com.tonolandia.demo.dto;

import java.util.List;
import java.util.UUID;

public record CierreRequest(List<UUID> contratoIds) {}
