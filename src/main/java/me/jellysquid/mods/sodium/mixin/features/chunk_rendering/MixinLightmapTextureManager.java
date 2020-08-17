package me.jellysquid.mods.sodium.mixin.features.chunk_rendering;

import me.jellysquid.mods.sodium.client.render.chunk.shader.texture.LightmapTextureManagerAccessor;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.texture.AbstractTexture;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Final;

@Mixin(LightmapTextureManager.class)
public class MixinLightmapTextureManager implements LightmapTextureManagerAccessor {
    @Shadow
    @Final
    private NativeImageBackedTexture texture;

    @Override
    public AbstractTexture getTexture() {
        return this.texture;
    }
}
