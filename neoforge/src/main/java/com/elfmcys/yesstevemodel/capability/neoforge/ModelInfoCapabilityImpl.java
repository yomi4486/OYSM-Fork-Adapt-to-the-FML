package com.elfmcys.yesstevemodel.capability.neoforge;

import com.elfmcys.yesstevemodel.capability.ModelInfoCapability;
import com.elfmcys.yesstevemodel.neoforge.capability.YsmAttachments;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;

public final class ModelInfoCapabilityImpl {

    private ModelInfoCapabilityImpl() {
    }

    public static Optional<ModelInfoCapability> get(Player player) {
        if (player.level().isClientSide()) {
            return Optional.empty();
        }
        return Optional.of(player.getData(YsmAttachments.MODEL_INFO));
    }
}
