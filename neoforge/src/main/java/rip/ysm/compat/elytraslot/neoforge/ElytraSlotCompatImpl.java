package rip.ysm.compat.elytraslot.neoforge;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import rip.ysm.compat.elytraslot.ElytraSlotCompat;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class ElytraSlotCompatImpl {

    private ElytraSlotCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static ItemStack getElytraItem(LivingEntity livingEntity) {
        return ItemStack.EMPTY;
    }
}
