package com.elfmcys.yesstevemodel.network.message;

import com.elfmcys.yesstevemodel.model.ServerModelManager;
import net.minecraft.network.FriendlyByteBuf;
import rip.ysm.api.network.PacketContext;

import java.nio.ByteBuffer;

public class C2SModelSyncPayload {

    private final ByteBuffer data;

    public C2SModelSyncPayload(ByteBuffer data) {
        this.data = data;
    }

    public static void encode(C2SModelSyncPayload message, FriendlyByteBuf buf) {
        // See S2CModelSyncPayload.encode(): encode() may be invoked more than
        // once per message, so use a fresh position/limit view each time.
        buf.writeBytes(message.data.duplicate());
    }

    public static C2SModelSyncPayload decode(FriendlyByteBuf buf) {
        ByteBuffer data = ByteBuffer.allocateDirect(buf.readableBytes());
        buf.readBytes(data);
        return new C2SModelSyncPayload(data);
    }

    public static void handle(C2SModelSyncPayload message, PacketContext ctx) {
        if (ctx.isServerSide() && ctx.getSender() != null) {
            ServerModelManager.nativeSendModelData(ctx.getSender().getUUID(), message.data);
        }
    }
}