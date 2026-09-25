package com.elfmcys.yesstevemodel.neoforge;

import com.elfmcys.yesstevemodel.YesSteveModel;
import com.elfmcys.yesstevemodel.client.ClientModelManager;
import com.elfmcys.yesstevemodel.client.compat.touhoulittlemaid.TouhouLittleMaidCompat;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

// The other ~20 third-party compat integrations ForgeClientSetupHooks used to
// initialize on Forge (Curios, FirstPerson, TACZ, ParCool, ...) are stubbed on
// NeoForge for now (see rip/ysm/compat/*/neoforge) and need no init() call
// here; only Touhou Little Maid has a real implementation.
// NeoForge dropped the @Mod.EventBusSubscriber annotation-scanning mechanism;
// this listener is registered manually from YesSteveModelNeoForge's constructor.
public final class NeoForgeClientSetupHooks {

    private NeoForgeClientSetupHooks() {
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        if (!YesSteveModel.isAvailable()) {
            return;
        }
        event.enqueueWork(() -> {
            TouhouLittleMaidCompat.init();
            ClientModelManager.loadDefaultModel();
        });
    }
}
