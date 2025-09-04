package com.tonolandia.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class InMemoryInventoryRepository implements InventoryRepository {
    private final Set<String> keys = new HashSet<>(Set.of("GATE-KEY-001"));
    private final Set<String> weapons = new HashSet<>(Set.of("Katana"));
    private int estus = 3;
    private final Set<String> affinities = new HashSet<>(Set.of("sangrado","fuego"));

    @Override public boolean hasKey(String keyId) { return keys.contains(keyId); }
    @Override public boolean hasWeapon(String weapon) { return weapons.contains(weapon); }
    @Override public int estusLeft() { return estus; }
    @Override public Set<String> affinities() { return Collections.unmodifiableSet(affinities); }
}