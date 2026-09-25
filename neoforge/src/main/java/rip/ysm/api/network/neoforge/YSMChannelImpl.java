package rip.ysm.api.network.neoforge;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import rip.ysm.api.network.PacketContext;
import rip.ysm.api.network.PacketDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

// NeoForge dropped Forge's SimpleChannel (one shared channel multiplexing
// messages by an int id) for a payload-per-type model: every message class
// gets its own CustomPacketPayload.Type + StreamCodec, registered through a
// PayloadRegistrar handed out (once) by RegisterPayloadHandlersEvent.
//
// NetworkHandler.init() (which drives every YSMChannel.register() call) runs
// from Architectury's LifecycleEvent.SETUP, and empirically that fires before
// RegisterPayloadHandlersEvent reaches this mod — the opposite of what the
// FML lifecycle names suggest. Rather than depend on an ordering assumption
// that already proved wrong once, register() below queues its work and
// applies it immediately if the registrar is already available, or defers it
// until setRegistrar() supplies one — correct either way round.
public final class YSMChannelImpl {

    private record Registration<T>(ResourceLocation id, Class<T> type,
                                     BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder,
                                     BiConsumer<T, PacketContext> handler, PacketDirection direction) {
    }

    private static volatile PayloadRegistrar registrar;

    private static ResourceLocation channelId;

    private static final List<Registration<?>> PENDING = new ArrayList<>();

    private static final Map<Class<?>, CustomPacketPayload.Type<YsmPayload>> TYPES_BY_CLASS = new ConcurrentHashMap<>();

    private YSMChannelImpl() {
    }

    public static synchronized void setRegistrar(PayloadRegistrar registrar) {
        YSMChannelImpl.registrar = registrar;
        for (Registration<?> registration : PENDING) {
            apply(registration);
        }
        PENDING.clear();
    }

    public static void init(ResourceLocation channelId, String version) {
        YSMChannelImpl.channelId = channelId;
    }

    public static synchronized <T> void register(int discriminator, Class<T> type, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, PacketContext> handler, PacketDirection direction) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(channelId.getNamespace(), channelId.getPath() + "_" + discriminator);
        Registration<T> registration = new Registration<>(id, type, encoder, decoder, handler, direction);
        if (registrar != null) {
            apply(registration);
        } else {
            PENDING.add(registration);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void apply(Registration<T> registration) {
        CustomPacketPayload.Type<YsmPayload> payloadType = new CustomPacketPayload.Type<>(registration.id());
        TYPES_BY_CLASS.put(registration.type(), payloadType);

        net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, YsmPayload> codec = CustomPacketPayload.codec(
                (payload, buf) -> registration.encoder().accept((T) payload.message, buf),
                buf -> new YsmPayload(registration.decoder().apply(buf), payloadType)
        );

        var payloadHandler = (net.neoforged.neoforge.network.handling.IPayloadHandler<YsmPayload>) (payload, context) ->
                registration.handler().accept((T) payload.message, new PacketContextImpl(context));

        if (registration.direction() == PacketDirection.PLAY_TO_CLIENT) {
            registrar.playToClient(payloadType, codec, payloadHandler);
        } else {
            registrar.playToServer(payloadType, codec, payloadHandler);
        }
    }

    private static YsmPayload wrap(Object packet) {
        CustomPacketPayload.Type<YsmPayload> type = TYPES_BY_CLASS.get(packet.getClass());
        if (type == null) {
            throw new IllegalStateException("Packet class " + packet.getClass() + " was never registered via YSMChannel.register()");
        }
        return new YsmPayload(packet, type);
    }

    public static void sendToServer(Object packet) {
        PacketDistributor.sendToServer(wrap(packet));
    }

    public static void sendToClientPlayer(Object packet, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, wrap(packet));
    }

    public static void sendToAll(Object packet) {
        PacketDistributor.sendToAllPlayers(wrap(packet));
    }

    public static void sendToTrackingEntity(Object packet, Entity entity) {
        PacketDistributor.sendToPlayersTrackingEntity(entity, wrap(packet));
    }

    public static void sendToTrackingEntityAndSelf(Object packet, Player player) {
        PacketDistributor.sendToPlayersTrackingEntityAndSelf(player, wrap(packet));
    }

    public static Packet<?> toClientboundPacket(Object packet) {
        return wrap(packet).toVanillaClientbound();
    }

    public static Packet<?> toServerboundPacket(Object packet) {
        return wrap(packet).toVanillaServerbound();
    }
}
