package com.elfmcys.yesstevemodel.network.message;

import com.elfmcys.yesstevemodel.YesSteveModel;
import com.elfmcys.yesstevemodel.client.ClientModelManager;
import net.minecraft.network.FriendlyByteBuf;
import rip.ysm.api.network.PacketContext;

import java.nio.ByteBuffer;

public class S2CModelSyncPayload {

    private final ByteBuffer data;

    public S2CModelSyncPayload(ByteBuffer data) {
        this.data = data;
    }

    public static void encode(S2CModelSyncPayload message, FriendlyByteBuf buf) {
        // NeoForge's payload codec can invoke encode() more than once for the
        // same message (e.g. size probing before the real write); writeBytes
        // drains the buffer's position, so a second call would write nothing
        // unless each call starts from an independent view of the data.
        buf.writeBytes(message.data.duplicate());
    }

    public static S2CModelSyncPayload decode(FriendlyByteBuf buf) {
        ByteBuffer data = ByteBuffer.allocateDirect(buf.readableBytes());
        buf.readBytes(data);
        return new S2CModelSyncPayload(data);
    }

    public static void handle(S2CModelSyncPayload message, PacketContext ctx) {
        YesSteveModel.LOGGER.info("[YSM][diag] S2CModelSyncPayload.handle() called, isClientSide=" + ctx.isClientSide() + ", bytes=" + (message.data != null ? message.data.remaining() : -1));
        if (ctx.isClientSide()) {
            ClientModelManager.startSync(ctx.getConnection(), message.data);
        }
    }
}