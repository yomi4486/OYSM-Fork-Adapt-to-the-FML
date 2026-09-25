package rip.ysm.compat.carryon.neoforge;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import rip.ysm.compat.carryon.CarryOnDataHelper;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class CarryOnDataHelperImpl {

    private CarryOnDataHelperImpl() {
    }

    public static boolean isPlayerCarrying(LivingEntity livingEntity) {
        return false;
    }

    public static CarryOnDataHelper.CarryType getCarryType(Player player) {
        return CarryOnDataHelper.CarryType.NONE;
    }
}
