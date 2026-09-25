package rip.ysm.compat.swem.neoforge;

import com.elfmcys.yesstevemodel.client.animation.molang.CtrlBinding;
import net.minecraft.world.entity.LivingEntity;
import rip.ysm.compat.swem.SWEMCompat;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class SWEMCompatImpl {

    private SWEMCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static String getHorseGaitName(LivingEntity livingEntity) {
        return null;
    }

    public static void registerControllerFunctions(CtrlBinding ctrlBinding) {
    }
}
