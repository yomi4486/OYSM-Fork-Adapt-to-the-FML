package com.elfmcys.yesstevemodel.neoforge.capability;

import com.elfmcys.yesstevemodel.YesSteveModel;
import com.elfmcys.yesstevemodel.capability.AuthModelsCapability;
import com.elfmcys.yesstevemodel.capability.ModelInfoCapability;
import com.elfmcys.yesstevemodel.capability.PlayerCapability;
import com.elfmcys.yesstevemodel.capability.ProjectileCapability;
import com.elfmcys.yesstevemodel.capability.ProjectileModelCapability;
import com.elfmcys.yesstevemodel.capability.StarModelsCapability;
import com.elfmcys.yesstevemodel.capability.VehicleCapability;
import com.elfmcys.yesstevemodel.capability.VehicleModelCapability;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

// Central registration for the mod's own per-entity data. NeoForge dropped
// Forge's Capability + AttachCapabilitiesEvent pattern in favor of
// AttachmentType, which lazily creates its value on first access instead of
// needing an explicit "attach" event, so the old ForgeCapabilityHooks event
// listener isn't needed here.
public final class YsmAttachments {

    private static final DeferredRegister<AttachmentType<?>> REGISTER =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, YesSteveModel.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<ModelInfoCapability>> MODEL_INFO =
            REGISTER.register("model_info", () -> AttachmentType.builder(holder -> new ModelInfoCapability())
                    .serialize(nbtSerializer(ModelInfoCapability::new, ModelInfoCapability::serializeNBT, ModelInfoCapability::deserializeNBT))
                    .build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<AuthModelsCapability>> AUTH_MODELS =
            REGISTER.register("auth_models", () -> AttachmentType.builder(holder -> new AuthModelsCapability())
                    .serialize(listSerializer(AuthModelsCapability::new, AuthModelsCapability::serializeNBT, AuthModelsCapability::deserializeNBT))
                    .build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<StarModelsCapability>> STAR_MODELS =
            REGISTER.register("star_models", () -> AttachmentType.builder(holder -> new StarModelsCapability())
                    .serialize(listSerializer(StarModelsCapability::new, StarModelsCapability::serializeNBT, StarModelsCapability::deserializeNBT))
                    .build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<ProjectileModelCapability>> PROJECTILE_MODEL =
            REGISTER.register("projectile_model", () -> AttachmentType.builder(holder -> new ProjectileModelCapability())
                    .serialize(nbtSerializer(ProjectileModelCapability::new, ProjectileModelCapability::serializeNBT, ProjectileModelCapability::deserializeNBT))
                    .build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<VehicleModelCapability>> VEHICLE_MODEL =
            REGISTER.register("vehicle_model", () -> AttachmentType.builder(holder -> new VehicleModelCapability())
                    .serialize(nbtSerializer(VehicleModelCapability::new, VehicleModelCapability::serializeNBT, VehicleModelCapability::deserializeNBT))
                    .build());

    // Client-only rendering state: never serialized, always freshly built from the owning entity.
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<PlayerCapability>> PLAYER =
            REGISTER.register("player_animatable", () -> AttachmentType.builder(holder -> new PlayerCapability((Player) holder)).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<VehicleCapability>> VEHICLE =
            REGISTER.register("vehicle_animatable", () -> AttachmentType.builder(holder -> new VehicleCapability((Entity) holder)).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<ProjectileCapability>> PROJECTILE =
            REGISTER.register("projectile_animatable", () -> AttachmentType.builder(holder -> new ProjectileCapability((Projectile) holder)).build());

    private YsmAttachments() {
    }

    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
    }

    private interface Writer<T> {
        CompoundTag write(T value);
    }

    private interface Reader<T> {
        void read(T value, CompoundTag tag);
    }

    private static <T> IAttachmentSerializer<CompoundTag, T> nbtSerializer(java.util.function.Supplier<T> factory, Writer<T> writer, Reader<T> reader) {
        return new IAttachmentSerializer<>() {
            @Override
            public T read(net.neoforged.neoforge.attachment.IAttachmentHolder holder, CompoundTag tag, HolderLookup.Provider provider) {
                T value = factory.get();
                reader.read(value, tag);
                return value;
            }

            @Override
            public CompoundTag write(T value, HolderLookup.Provider provider) {
                return writer.write(value);
            }
        };
    }

    private interface ListWriter<T> {
        ListTag write(T value);
    }

    private interface ListReader<T> {
        void read(T value, ListTag tag);
    }

    private static <T> IAttachmentSerializer<ListTag, T> listSerializer(java.util.function.Supplier<T> factory, ListWriter<T> writer, ListReader<T> reader) {
        return new IAttachmentSerializer<>() {
            @Override
            public T read(net.neoforged.neoforge.attachment.IAttachmentHolder holder, ListTag tag, HolderLookup.Provider provider) {
                T value = factory.get();
                reader.read(value, tag);
                return value;
            }

            @Override
            public ListTag write(T value, HolderLookup.Provider provider) {
                return writer.write(value);
            }
        };
    }

    public static boolean isAbstractClientPlayer(Entity entity) {
        return entity instanceof AbstractClientPlayer;
    }
}
