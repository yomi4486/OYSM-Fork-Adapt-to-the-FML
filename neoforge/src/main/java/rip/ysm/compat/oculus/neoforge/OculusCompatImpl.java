package rip.ysm.compat.oculus.neoforge;

import rip.ysm.compat.oculus.OculusCompat;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class OculusCompatImpl {

    private OculusCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static boolean isPBRActive() {
        return false;
    }

    public static void updatePBRState() {
    }

    public static boolean isShaderPackInUse() {
        return false;
    }

    public static boolean isRenderingShadowPass() {
        return false;
    }
}
