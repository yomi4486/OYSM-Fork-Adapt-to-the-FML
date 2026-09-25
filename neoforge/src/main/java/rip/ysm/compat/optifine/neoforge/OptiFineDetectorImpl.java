package rip.ysm.compat.optifine.neoforge;

import rip.ysm.compat.optifine.OptiFineDetector;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class OptiFineDetectorImpl {

    private OptiFineDetectorImpl() {
    }

    public static boolean isOptifinePresent() {
        return false;
    }
}
