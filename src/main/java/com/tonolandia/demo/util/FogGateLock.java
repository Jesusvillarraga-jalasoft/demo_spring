package com.tonolandia.demo.util;

import com.tonolandia.demo.domain.Boss;

public final class FogGateLock implements AutoCloseable {
    private final String boss;
    private boolean closed = false;

    private FogGateLock(String boss) { this.boss = boss; }

    public static FogGateLock acquire(Boss boss) throws ArenaLockException {
        if (boss == null) throw new ArenaLockException("Boss nulo");
        return new FogGateLock(boss.nombre());
    }

    @Override public void close() {
        if (!closed) {
            closed = true;
            // liberar lock (no-op)
        }
    }

    @Override public String toString() { return "FogGateLock(" + boss + ")"; }
}
