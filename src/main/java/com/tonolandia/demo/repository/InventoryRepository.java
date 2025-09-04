package com.tonolandia.demo.repository;

import java.util.Set;

public interface InventoryRepository {
    boolean hasKey(String keyId);
    boolean hasWeapon(String weapon);
    int estusLeft();
    Set<String> affinities();
}
