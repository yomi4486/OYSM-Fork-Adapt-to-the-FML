package rip.ysm.compat.cosmeticarmorreworked.neoforge;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import rip.ysm.compat.cosmeticarmorreworked.CosmeticArmorHelper;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class CosmeticArmorHelperImpl {

    private CosmeticArmorHelperImpl() {
    }

    public static ItemStack getArmorItem(LivingEntity entity, EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    public static ItemStack getElytraItem(LivingEntity livingEntity) {
        return ItemStack.EMPTY;
    }
}
