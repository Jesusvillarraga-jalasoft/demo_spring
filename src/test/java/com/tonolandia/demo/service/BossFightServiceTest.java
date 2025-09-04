package com.tonolandia.demo.service;

import com.tonolandia.demo.domain.Boss;
import com.tonolandia.demo.domain.Build;
import com.tonolandia.demo.dto.FightResult;
import com.tonolandia.demo.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BossFightServiceTest {

    @Mock
    InventoryRepository inventory;

    BossFightService service;

    Boss boss = new Boss("Maliketh", 5, Set.of("sangrado"));
    Build build = new Build("Katana", 40, 3, Set.of("sangrado", "fuego"));

    @BeforeEach
    void setUp() {
        service = new BossFightServiceImpl(inventory);
    }

    // ---------- Helpers ----------
    private void mockInventoryHappyPath() {
        when(inventory.hasKey("GATE-KEY-001")).thenReturn(true);
        when(inventory.estusLeft()).thenReturn(3);
        when(inventory.hasWeapon("Katana")).thenReturn(true);
        when(inventory.affinities()).thenReturn(Set.of("sangrado", "fuego"));
    }

    // ---------- Tests de validación temprana (unchecked) ----------
    @Test
    void nullBoss_lanzaInvalidBuildException() {
        mockInventoryHappyPath();
        assertThrows(InvalidBuildException.class,
                () -> service.enfrentarBoss(null, build, 0));
    }

    @Test
    void nullBuild_lanzaInvalidBuildException() {
        mockInventoryHappyPath();
        assertThrows(InvalidBuildException.class,
                () -> service.enfrentarBoss(boss, null, 0));
    }

    @Test
    void reintentosNegativos_lanzaInvalidBuildException() {
        mockInventoryHappyPath();
        assertThrows(InvalidBuildException.class,
                () -> service.enfrentarBoss(boss, build, -1));
    }

    // ---------- Tests de precondiciones (checked) ----------
    @Test
    void sinLlave_lanzaGateKeyMissingException() throws Exception {
        when(inventory.hasKey("GATE-KEY-001")).thenReturn(false);
        when(inventory.estusLeft()).thenReturn(3);
        when(inventory.hasWeapon("Katana")).thenReturn(true);

        assertThrows(GateKeyMissingException.class,
                () -> service.enfrentarBoss(boss, build, 0));
    }

    @Test
    void sinEstus_lanzaEstusEmptyException() throws Exception {
        when(inventory.hasKey("GATE-KEY-001")).thenReturn(true);
        when(inventory.estusLeft()).thenReturn(0);
        when(inventory.hasWeapon("Katana")).thenReturn(true);

        assertThrows(EstusEmptyException.class,
                () -> service.enfrentarBoss(boss, build, 0));
    }

    @Test
    void sinArma_lanzaWeaponNotEquippedException() throws Exception {
        when(inventory.hasKey("GATE-KEY-001")).thenReturn(true);
        when(inventory.estusLeft()).thenReturn(3);
        when(inventory.hasWeapon("Katana")).thenReturn(false);

        assertThrows(WeaponNotEquippedException.class,
                () -> service.enfrentarBoss(boss, build, 0));
    }

    // ---------- Test de reintentos + wrapping ----------
    // Con la implementación base (que lanza PhaseTransitionException dentro del while),
    // esperamos que, tras agotar reintentos, se lance BossFightException con cause PhaseTransitionException.
    @Test
    void reintentosAgotados_envuelveEnBossFightException() throws Exception {
        mockInventoryHappyPath();

        BossFightException ex = assertThrows(BossFightException.class,
                () -> service.enfrentarBoss(boss, build, 2)); // permite 2 reintentos

        assertNotNull(ex.getCause(), "Debe preservar la causa");
        assertTrue(ex.getCause() instanceof PhaseTransitionException,
                "La causa debe ser PhaseTransitionException");
    }


    @Test
    void victoria_devuelveFightResultConEventosYDuracion() throws Exception {
        mockInventoryHappyPath();
        FightResult result = service.enfrentarBoss(boss, build, 3);
        assertNotNull(result);
        assertEquals("Maliketh", result.boss());
        assertTrue(result.victoria());
        assertNotNull(result.eventos());
        assertFalse(result.eventos().isEmpty());
        assertTrue(result.duracion().compareTo(Duration.ZERO) > 0);
    }

    // ---------- (Opcional) Test de la jerarquía sealed ----------
    // Valida que la base esté sellada y lista de subclases esté definida.
    @Test
    void jerarquiaDeExcepciones_esSealed() {
        assertTrue(BossFightException.class.isSealed(),
                "BossFightException debe ser sealed (Java 17)");
        Class<?>[] permitted = BossFightException.class.getPermittedSubclasses();
        // Deben contener las subclases definidas por el estudiante:
        // GateKeyMissingException, EstusEmptyException, WeaponNotEquippedException,
        // PhaseTransitionException, ArenaLockException
        assertNotNull(permitted);
        assertTrue(permitted.length >= 5,
                "Se esperan al menos 5 subclases permitidas en la jerarquía sealed");
    }
}