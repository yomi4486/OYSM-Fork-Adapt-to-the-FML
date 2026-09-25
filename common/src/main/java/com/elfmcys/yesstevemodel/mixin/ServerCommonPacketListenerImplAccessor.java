package com.elfmcys.yesstevemodel.mixin;

import net.minecraft.network.Connection;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

// MC 1.20.2+ moved the connection field onto the shared
// ServerCommonPacketListenerImpl base (configuration + play phases); it used
// to live directly on ServerGamePacketListenerImpl.
@Mixin(ServerCommonPacketListenerImpl.class)
public interface ServerCommonPacketListenerImplAccessor {
    @Accessor("connection")
    Connection ysm$getConnection();
}
