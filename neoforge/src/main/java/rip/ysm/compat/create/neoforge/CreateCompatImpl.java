package rip.ysm.compat.create.neoforge;

import com.elfmcys.yesstevemodel.client.animation.molang.CtrlBinding;
import net.minecraft.world.entity.player.Player;
import rip.ysm.compat.create.CreateCompat;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class CreateCompatImpl {

    private CreateCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static boolean isPlayerOnCreateContraption(Player player) {
        return false;
    }

    public static void registerCreateFunctions(CtrlBinding binding) {
    }
}
