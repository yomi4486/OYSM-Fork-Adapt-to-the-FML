package com.elfmcys.yesstevemodel.capability.neoforge;

import com.elfmcys.yesstevemodel.capability.VehicleModelCapability;
import com.elfmcys.yesstevemodel.neoforge.capability.YsmAttachments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;

import java.util.Optional;

public final class VehicleModelCapabilityImpl {

    private VehicleModelCapabilityImpl() {
    }

    public static Optional<VehicleModelCapability> get(Entity entity) {
        if (entity.level().isClientSide() || entity instanceof Player || entity instanceof Projectile) {
            return Optional.empty();
        }
        return Optional.of(entity.getData(YsmAttachments.VEHICLE_MODEL));
    }
}
