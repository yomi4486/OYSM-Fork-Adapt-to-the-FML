package com.elfmcys.yesstevemodel.capability.neoforge;

import com.elfmcys.yesstevemodel.capability.ProjectileCapability;
import com.elfmcys.yesstevemodel.neoforge.capability.YsmAttachments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import rip.ysm.api.PlatformAPI;

import java.util.Optional;

public final class ProjectileCapabilityImpl {

    private ProjectileCapabilityImpl() {
    }

    public static Optional<ProjectileCapability> get(Entity entity) {
        if (!(entity instanceof Projectile projectile)) {
            return Optional.empty();
        }
        return get(projectile);
    }

    public static Optional<ProjectileCapability> get(Projectile projectile) {
        if (PlatformAPI.isServer() || !projectile.level().isClientSide()) {
            return Optional.empty();
        }
        return Optional.of(projectile.getData(YsmAttachments.PROJECTILE));
    }
}
