package com.elfmcys.yesstevemodel.client.compat.touhoulittlemaid.event;

import com.elfmcys.yesstevemodel.neoforge.client.gui.TouhouMaidModelScreen;
import com.github.tartaricacid.touhoulittlemaid.compat.ysm.event.OpenYsmMaidScreenEvent;
import net.minecraft.client.Minecraft;

import net.neoforged.bus.api.SubscribeEvent;

public final class MaidScreenEvent {
    @SubscribeEvent
    public void onOpenMaidScreen(OpenYsmMaidScreenEvent event) {
        Minecraft.getInstance().setScreen(new TouhouMaidModelScreen(event.getMaid()));
    }
}
