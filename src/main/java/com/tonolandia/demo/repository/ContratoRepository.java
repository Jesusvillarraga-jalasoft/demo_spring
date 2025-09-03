package com.tonolandia.demo.repository;

import com.tonolandia.demo.domain.Contrato;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContratoRepository {
    List<Contrato> findAll();
    Optional<Contrato> findById(UUID id);
    void save(Contrato c);               // guardar NUEVO objeto (no mutar)
    void saveAll(Collection<Contrato> cs);
}
