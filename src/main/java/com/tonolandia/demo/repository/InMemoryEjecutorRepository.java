package com.tonolandia.demo.repository;

import com.tonolandia.demo.domain.Ejecutor;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryEjecutorRepository implements EjecutorRepository {
    private final Map<String, Ejecutor> data = new LinkedHashMap<>();

    @PostConstruct
    void seed() {
        data.put("BABA-YAGA", new Ejecutor("BABA-YAGA", 5, Set.of("armas","discreto","vehiculos")));
        data.put("SOFIA",     new Ejecutor("SOFIA",     4, Set.of("armas","perros")));
        data.put("CASSIAN",   new Ejecutor("CASSIAN",   4, Set.of("discreto","armas")));
    }

    @Override public List<Ejecutor> findAll() { return new ArrayList<>(data.values()); }
    @Override public Optional<Ejecutor> findByCodigo(String codigo) { return Optional.ofNullable(data.get(codigo)); }
}