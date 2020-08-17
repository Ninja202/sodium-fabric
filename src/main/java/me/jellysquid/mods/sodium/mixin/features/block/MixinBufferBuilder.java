package me.jellysquid.mods.sodium.mixin.features.block;

import me.jellysquid.mods.sodium.client.model.quad.ModelQuadViewMutable;
import me.jellysquid.mods.sodium.client.model.quad.sink.ModelQuadSink;
import net.minecraft.client.render.FixedColorVertexConsumer;
import me.jellysquid.mods.sodium.client.util.ModelQuadUtil;
import net.minecraft.client.render.BufferBuilder;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mixin;

import java.nio.ByteBuffer;

@Mixin(BufferBuilder.class)
public abstract class MixinBufferBuilder extends FixedColorVertexConsumer implements ModelQuadSink {
    @Shadow
    private int elementOffset;

    @Shadow
    private ByteBuffer buffer;

    @Shadow
    protected abstract void grow(int size);

    @Shadow
    private int vertexCount;

    @Override
    public void write(ModelQuadViewMutable quad) {
        this.grow(ModelQuadUtil.VERTEX_SIZE_BYTES);

        quad.copyInto(this.buffer, this.elementOffset);

        this.elementOffset += ModelQuadUtil.VERTEX_SIZE_BYTES;
        this.vertexCount += 4;
    }
}
