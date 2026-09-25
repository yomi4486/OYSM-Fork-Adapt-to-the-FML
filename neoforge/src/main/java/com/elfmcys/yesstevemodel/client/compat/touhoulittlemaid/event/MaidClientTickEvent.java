package com.elfmcys.yesstevemodel.client.compat.touhoulittlemaid.event;

import com.elfmcys.yesstevemodel.neoforge.capability.TlmAttachments;
import com.github.tartaricacid.touhoulittlemaid.compat.ysm.event.UpdateRemoteStructEvent;
import com.github.tartaricacid.touhoulittlemaid.compat.ysm.event.YsmMaidClientTickEvent;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

import net.neoforged.bus.api.SubscribeEvent;

public class MaidClientTickEvent {
    @SubscribeEvent
    public void onMaidClientTick(YsmMaidClientTickEvent event) {
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        if (localPlayer == null) {
            return;
        }
        EntityMaid maid = event.getMaid();
        if (localPlayer.getUUID().equals(maid.getOwnerUUID())) {
            tickMaidModel(maid);
        }
    }

    @SubscribeEvent
    public void onUpdateRemoteStruct(UpdateRemoteStructEvent event) {
        event.getMaid().getData(TlmAttachments.MAID).updateRoamingVars(event.getRoamingVars());
    }

    private void tickMaidModel(EntityMaid entityMaid) {
        entityMaid.getData(TlmAttachments.MAID);
    }
}
