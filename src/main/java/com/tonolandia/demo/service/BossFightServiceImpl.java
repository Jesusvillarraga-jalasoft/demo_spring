package com.tonolandia.demo.service;

import com.tonolandia.demo.domain.Boss;
import com.tonolandia.demo.domain.Build;
import com.tonolandia.demo.dto.FightResult;
import com.tonolandia.demo.repository.InventoryRepository;
import com.tonolandia.demo.util.FogGateLock;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class BossFightServiceImpl implements BossFightService {

    private final InventoryRepository inventory;

    public BossFightServiceImpl(InventoryRepository inventory) {
        this.inventory = inventory;
    }

    @Override
    public FightResult enfrentarBoss(Boss boss, Build build, int maxReintentos)
            throws GateKeyMissingException, EstusEmptyException, WeaponNotEquippedException,
            ArenaLockException, BossFightException {

        if (boss == null) throw new InvalidBuildException("Boss requerido");       // <-- unchecked (la crean)
        if (build == null) throw new InvalidBuildException("Build requerida");     // <-- unchecked (la crean)
        if (maxReintentos < 0) throw new InvalidBuildException("maxReintentos negativo");

        if (!inventory.hasKey("GATE-KEY-001"))
            throw new GateKeyMissingException("Falta llave de la puerta");
        if (inventory.estusLeft() <= 0)
            throw new EstusEmptyException("Sin Estus Flask");
        if (!inventory.hasWeapon(build.arma()))
            throw new WeaponNotEquippedException("Arma no equipada: " + build.arma());

        Instant start = Instant.now();
        List<String> eventos = new ArrayList<>();

        try (FogGateLock lock = FogGateLock.acquire(boss)) {
            eventos.add("Lock adquirido: " + lock);

            int intento = 0;
            while (true) {
                try {
                    // --- TODO (estudiante): implementar lógica de combate ---
                    // - Usar afinidades de la build vs resistencias del boss.
                    // - Simular fases y posibles errores: PhaseTransitionException (checked).
                    // - Construir 'eventos' y decidir victoria/derrota.
                    // - En caso de éxito: return new FightResult(..., true, eventos, Duration.between(start, Instant.now()));
                    throw new PhaseTransitionException("Fase 2 fallida (simulación)");
                } catch (PhaseTransitionException e) {
                    if (intento++ < maxReintentos) {
                        eventos.add("Reintento por transición de fase: intento " + intento);
                        continue;
                    }
                    throw new BossFightException("No se pudo superar transición de fase tras reintentos", e);
                }
            }
        } finally {
            // Limpieza de estado (si aplica): buffs temporales, etc.
            eventos.add("Limpieza finalizada");
        }
    }
}