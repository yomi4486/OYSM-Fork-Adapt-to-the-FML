package rip.ysm.compat.touhoulittlemaid.neoforge;

import com.elfmcys.yesstevemodel.neoforge.capability.TlmAttachments;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import net.minecraft.world.entity.Entity;

import java.util.Optional;

public final class MaidCapabilityBridgeImpl {

    private MaidCapabilityBridgeImpl() {
    }

    public static Optional<Object> get(Entity entity) {
        if (!(entity instanceof EntityMaid maid)) {
            return Optional.empty();
        }
        return Optional.of(maid.getData(TlmAttachments.MAID));
    }
}
