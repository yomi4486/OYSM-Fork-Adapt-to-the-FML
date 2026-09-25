package rip.ysm.api.entity.neoforge;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;

public final class EntityDataBridgeImpl {

    private EntityDataBridgeImpl() {
    }

    public static CompoundTag getPersistentData(Entity entity) {
        return entity.getPersistentData();
    }

    // Entity.shouldRiderSit() (a Forge-only patch hint) no longer exists in
    // 1.21.1; there is no direct vanilla/NeoForge replacement, so default to
    // the common case (most vehicles want their rider posed sitting).
    public static boolean shouldRiderSit(Entity vehicle) {
        return true;
    }
}
