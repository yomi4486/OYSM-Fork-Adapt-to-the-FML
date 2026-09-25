package com.elfmcys.yesstevemodel.capability.neoforge;

import com.elfmcys.yesstevemodel.capability.PlayerCapability;
import com.elfmcys.yesstevemodel.neoforge.capability.YsmAttachments;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import rip.ysm.api.PlatformAPI;

import java.util.Optional;

public final class PlayerCapabilityImpl {

    private PlayerCapabilityImpl() {
    }

    public static Optional<PlayerCapability> get(Player player) {
        if (PlatformAPI.isServer() || !player.level().isClientSide() || !(player instanceof AbstractClientPlayer)) {
            return Optional.empty();
        }
        return Optional.of(player.getData(YsmAttachments.PLAYER));
    }

    public static Optional<PlayerCapability> get(Entity entity) {
        if (!(entity instanceof Player player)) {
            return Optional.empty();
        }
        return get(player);
    }
}
