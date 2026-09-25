package rip.ysm.api.config;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.fml.config.ModConfig;

public final class ConfigRegistration {

    private ConfigRegistration() {
    }

    @ExpectPlatform
    public static void register(String modId, ModConfig.Type type, ModConfigSpec spec) {
        throw new AssertionError();
    }
}
