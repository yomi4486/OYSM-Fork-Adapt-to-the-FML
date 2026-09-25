package rip.ysm.compat.firstperson.neoforge;

import rip.ysm.compat.firstperson.FirstPersonCompat;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class FirstPersonCompatImpl {

    private FirstPersonCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static boolean isFirstPersonActive() {
        return false;
    }

    public static boolean shouldHideHead() {
        return false;
    }

    public static void setCameraDistance(float distance) {
    }
}
