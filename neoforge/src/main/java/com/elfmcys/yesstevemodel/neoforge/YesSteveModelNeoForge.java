package com.elfmcys.yesstevemodel.neoforge;

import com.elfmcys.yesstevemodel.YesSteveModel;
import com.elfmcys.yesstevemodel.network.NetworkHandler;
import com.elfmcys.yesstevemodel.neoforge.capability.YsmAttachments;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import rip.ysm.api.network.neoforge.YSMChannelImpl;

@Mod(YesSteveModel.MOD_ID)
public final class YesSteveModelNeoForge {

    public YesSteveModelNeoForge(IEventBus modBus) {
        YsmAttachments.register(modBus);
        modBus.addListener(this::onRegisterPayloadHandlers);
        YesSteveModel.init();
    }

    private void onRegisterPayloadHandlers(RegisterPayloadHandlersEvent event) {
        YSMChannelImpl.setRegistrar(event.registrar(NetworkHandler.VERSION));
    }

    // A separate @Mod(dist = CLIENT) inner class is how NeoForge scopes a
    // mod entrypoint to one physical side; FML only constructs (and so only
    // classloads) it on that side. This keeps client-rendering-only classes
    // like MaidCapability/ClientModelManager from ever being touched on a
    // dedicated server, matching what Forge's old
    // @Mod.EventBusSubscriber(dist = CLIENT) used to guarantee (NeoForge
    // dropped that annotation-scanning mechanism).
    @Mod(value = YesSteveModel.MOD_ID, dist = Dist.CLIENT)
    public static final class YesSteveModelNeoForgeClient {
        public YesSteveModelNeoForgeClient(IEventBus modBus) {
            com.elfmcys.yesstevemodel.neoforge.capability.TlmAttachments.register(modBus);
            modBus.addListener(com.elfmcys.yesstevemodel.neoforge.NeoForgeClientSetupHooks::onClientSetup);
        }
    }
}
