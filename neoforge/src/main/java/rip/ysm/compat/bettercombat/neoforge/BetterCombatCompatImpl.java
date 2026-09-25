package rip.ysm.compat.bettercombat.neoforge;

import com.elfmcys.yesstevemodel.client.animation.molang.CtrlBinding;
import rip.ysm.compat.bettercombat.BetterCombatCompat;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class BetterCombatCompatImpl {

    private BetterCombatCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static void registerBindings(CtrlBinding binding) {
    }
}
