package rip.ysm.compat.playeranimator.neoforge;

import net.minecraft.client.player.AbstractClientPlayer;
import rip.ysm.compat.playeranimator.PlayerAnimatorCompat;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class PlayerAnimatorCompatImpl {

    private PlayerAnimatorCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static boolean isPlayerAnimated(AbstractClientPlayer abstractClientPlayer) {
        return false;
    }
}
