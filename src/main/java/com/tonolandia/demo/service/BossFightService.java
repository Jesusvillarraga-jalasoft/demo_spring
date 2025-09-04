package com.tonolandia.demo.service;

import com.tonolandia.demo.domain.Boss;
import com.tonolandia.demo.domain.Build;
import com.tonolandia.demo.dto.FightResult;

/**
 * Enfrenta a un boss con la build actual.
 * @param boss  jefe a enfrentar
 * @param build configuración de la build (inmutable)
 * @param maxReintentos reintentos ante transición de fase
 * @return resultado de la pelea
 * @throws GateKeyMissingException si falta la llave de acceso.
 * @throws EstusEmptyException si no quedan frascos para curación.
 * @throws WeaponNotEquippedException si el arma de la build no está en inventario.
 * @throws ArenaLockException si no se pudo bloquear la arena (fog gate).
 * @throws BossFightException para otras condiciones de pelea del dominio.
 *
 * Requisito: la jerarquía de excepciones debe ser una SEALED HIERARCHY.
 */
public interface BossFightService {
    FightResult enfrentarBoss(Boss boss, Build build, int maxReintentos)
            throws GateKeyMissingException, EstusEmptyException, WeaponNotEquippedException,
            ArenaLockException, BossFightException;
}