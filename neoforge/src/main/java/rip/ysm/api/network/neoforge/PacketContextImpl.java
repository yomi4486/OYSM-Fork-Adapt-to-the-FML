package rip.ysm.api.network.neoforge;

import net.minecraft.network.Connection;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.Nullable;
import rip.ysm.api.network.PacketContext;

final class PacketContextImpl implements PacketContext {

    private final IPayloadContext context;

    PacketContextImpl(IPayloadContext context) {
        this.context = context;
    }

    @Override
    public boolean isClientSide() {
        return this.context.flow() == PacketFlow.CLIENTBOUND;
    }

    @Override
    @Nullable
    public ServerPlayer getSender() {
        return this.context.player() instanceof ServerPlayer serverPlayer ? serverPlayer : null;
    }

    @Override
    public Connection getConnection() {
        return this.context.connection();
    }

    @Override
    public void enqueueWork(Runnable runnable) {
        this.context.enqueueWork(runnable);
    }
}
