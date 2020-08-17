package me.jellysquid.mods.sodium.mixin.features.block;

import me.jellysquid.mods.sodium.client.render.pipeline.context.GlobalRenderContext;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.util.math.Matrix4f;

@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {
    /**
     * Reset any global cached state before rendering a frame. This will hopefully ensure that any world state that has
     * changed is reflected in vanilla-style rendering.
     */
    @Inject(method = "render", at = @At("HEAD"))
    private void reset(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera,
                       GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f,
                       CallbackInfo ci) {
        GlobalRenderContext.reset();
    }
}
