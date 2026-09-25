package rip.ysm.api.network.neoforge;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

// Generic wrapper so every registered message class doesn't need its own
// hand-written CustomPacketPayload subtype; the raw message is carried as
// Object and cast back using the Class<T> the caller registered with.
final class YsmPayload implements CustomPacketPayload {

    final Object message;
    final Type<YsmPayload> type;

    YsmPayload(Object message, Type<YsmPayload> type) {
        this.message = message;
        this.type = type;
    }

    @Override
    public Type<YsmPayload> type() {
        return this.type;
    }
}
