package com.elfmcys.yesstevemodel.audio;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import net.minecraft.client.sounds.JOrbisAudioStream;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.BufferUtils;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class OggVorbisAudioStream implements IAudioStreamSupport {

    private static final ByteBuffer EMPTY_BUFFER = BufferUtils.createByteBuffer(0);

    private final JOrbisAudioStream oggStream;

    private final int sourceChannels;

    private final AudioFormat audioFormat;

    @Nullable
    private final AudioCacheBuilder cacheBuilder;

    private final FloatArrayList pendingSamples = new FloatArrayList();

    private volatile boolean isClosed;

    private boolean isEndOfStream;

    public OggVorbisAudioStream(ByteBuffer byteBuffer, @Nullable AudioCacheBuilder cacheBuilder) throws UnsupportedAudioFileException, IOException {
        this.oggStream = new JOrbisAudioStream(new ByteBufInputStream(Unpooled.wrappedBuffer(byteBuffer)));
        this.sourceChannels = this.oggStream.getFormat().getChannels();
        if (this.sourceChannels != 1 && this.sourceChannels != 2) {
            throw new UnsupportedAudioFileException();
        }
        this.audioFormat = new AudioFormat(this.oggStream.getFormat().getSampleRate(), 16, 1, true, false);
        this.cacheBuilder = cacheBuilder;
    }

    @NotNull
    public AudioFormat getFormat() {
        return this.audioFormat;
    }

    // Pulls decoded float samples from the JOrbis push-style decoder until we
    // have enough for at least one more mono output frame, or the stream ends.
    private boolean fillPending() throws IOException {
        boolean more = true;
        while (this.pendingSamples.size() < this.sourceChannels && more) {
            more = this.oggStream.readChunk(this.pendingSamples::add);
        }
        return !this.pendingSamples.isEmpty();
    }

    @NotNull
    public ByteBuffer read(int i) throws IOException {
        if (this.isEndOfStream || this.isClosed) {
            return EMPTY_BUFFER;
        }
        if (!fillPending()) {
            if (this.cacheBuilder != null) {
                this.cacheBuilder.flushToCache();
            }
            this.isEndOfStream = true;
            return EMPTY_BUFFER;
        }

        int framesRequested = Math.max(1, i / 2);
        int framesAvailable = this.pendingSamples.size() / this.sourceChannels;
        int frames = Math.min(framesRequested, framesAvailable);

        ByteBuffer byteBufferSlice = BufferUtils.createByteBuffer(frames * 2).order(ByteOrder.nativeOrder());
        for (int frame = 0; frame < frames; frame++) {
            float sample;
            if (this.sourceChannels == 2) {
                sample = (this.pendingSamples.getFloat(frame * 2) + this.pendingSamples.getFloat((frame * 2) + 1)) / 2.0f;
            } else {
                sample = this.pendingSamples.getFloat(frame);
            }
            short s = (short) Math.round(Mth.clamp(sample, -1.0f, 1.0f) * Short.MAX_VALUE);
            byteBufferSlice.putShort(s);
        }
        byteBufferSlice.flip();
        this.pendingSamples.removeElements(0, frames * this.sourceChannels);

        if (this.cacheBuilder != null) {
            this.cacheBuilder.appendAudio(byteBufferSlice.duplicate());
        }
        return byteBufferSlice;
    }

    public void close() throws IOException {
        if (!this.isClosed) {
            this.oggStream.close();
            this.isClosed = true;
        }
    }

    @Override
    public boolean isClosed() {
        return this.isClosed;
    }
}
