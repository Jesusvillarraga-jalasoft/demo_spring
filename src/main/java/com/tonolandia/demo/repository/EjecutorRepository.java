package com.tonolandia.demo.repository;

import com.tonolandia.demo.domain.Ejecutor;

import java.util.List;
import java.util.Optional;

public interface EjecutorRepository {
    List<Ejecutor> findAll();
    Optional<Ejecutor> findByCodigo(String codigo);
}