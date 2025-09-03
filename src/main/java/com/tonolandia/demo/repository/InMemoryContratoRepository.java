package com.tonolandia.demo.repository;

import com.tonolandia.demo.domain.Contrato;
import com.tonolandia.demo.domain.Estado;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryContratoRepository implements ContratoRepository {
    private final Map<UUID, Contrato> data = new ConcurrentHashMap<>();

    @PostConstruct
    void seed() {
        var c1 = new Contrato(UUID.randomUUID(), "Santino", 250_000, Estado.ABIERTO,
                Set.of("alto-valor","discreto"), List.of("Roma","Nueva York"));
        var c2 = new Contrato(UUID.randomUUID(), "Zero", 180_000, Estado.ABIERTO,
                Set.of("alto-valor","publico"), List.of("Osaka"));
        var c3 = new Contrato(UUID.randomUUID(), "Tarasov", 90_000, Estado.ABIERTO,
                Set.of("vehiculos"), List.of("Moscú","Roma"));
        var c4 = new Contrato(UUID.randomUUID(), "Ares", 120_000, Estado.ASIGNADO,
                Set.of("armas","discreto"), List.of("París"));
        List.of(c1,c2,c3,c4).forEach(c -> data.put(c.id(), c));
    }

    @Override public List<Contrato> findAll() { return new ArrayList<>(data.values()); }
    @Override public Optional<Contrato> findById(UUID id) { return Optional.ofNullable(data.get(id)); }
    @Override public void save(Contrato c) { data.put(c.id(), c); }
    @Override public void saveAll(Collection<Contrato> cs) { cs.forEach(c -> data.put(c.id(), c)); }
}