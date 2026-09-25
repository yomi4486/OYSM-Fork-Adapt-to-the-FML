package com.elfmcys.yesstevemodel.neoforge.capability;

import com.elfmcys.yesstevemodel.YesSteveModel;
import com.elfmcys.yesstevemodel.client.compat.touhoulittlemaid.capability.MaidCapability;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

// Touhou Little Maid's own MaidCapability rendering state (client-only, never
// serialized) — replaces the old Forge MaidCapabilityProvider + Capability<MaidCapability>.
public final class TlmAttachments {

    private static final DeferredRegister<AttachmentType<?>> REGISTER =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, YesSteveModel.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<MaidCapability>> MAID =
            REGISTER.register("maid_animatable", () -> AttachmentType.builder(holder -> new MaidCapability((EntityMaid) holder, true)).build());

    private TlmAttachments() {
    }

    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
    }
}
