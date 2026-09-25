package rip.ysm.api.config.neoforge;

import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class ConfigRegistrationImpl {

    private ConfigRegistrationImpl() {
    }

    public static void register(String modId, ModConfig.Type type, ModConfigSpec spec) {
        ModLoadingContext.get().getActiveContainer().registerConfig(type, spec);
    }
}
