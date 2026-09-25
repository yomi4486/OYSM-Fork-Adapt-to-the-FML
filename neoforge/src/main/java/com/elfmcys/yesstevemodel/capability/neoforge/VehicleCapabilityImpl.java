package com.elfmcys.yesstevemodel.capability.neoforge;

import com.elfmcys.yesstevemodel.capability.VehicleCapability;
import com.elfmcys.yesstevemodel.neoforge.capability.YsmAttachments;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.Entity;
import rip.ysm.api.PlatformAPI;

import java.util.Optional;

public final class VehicleCapabilityImpl {

    private VehicleCapabilityImpl() {
    }

    public static Optional<VehicleCapability> get(Entity entity) {
        if (PlatformAPI.isServer() || !entity.level().isClientSide() || entity instanceof AbstractClientPlayer) {
            return Optional.empty();
        }
        return Optional.of(entity.getData(YsmAttachments.VEHICLE));
    }
}
