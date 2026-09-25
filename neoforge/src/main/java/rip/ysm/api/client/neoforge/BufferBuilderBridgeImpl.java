package rip.ysm.api.client.neoforge;

import com.mojang.blaze3d.vertex.BufferBuilder;

import java.nio.ByteBuffer;

// putBulkData was removed from BufferBuilder in 1.21.1 along with the rest of
// the old fixed-layout vertex API; no direct bulk-copy path is available
// anymore, so callers should always fall back to the per-vertex path.
public final class BufferBuilderBridgeImpl {

    private BufferBuilderBridgeImpl() {
    }

    public static boolean putBulkData(BufferBuilder builder, ByteBuffer buffer) {
        return false;
    }

    public static boolean supportsDirectTransfer() {
        return false;
    }
}
