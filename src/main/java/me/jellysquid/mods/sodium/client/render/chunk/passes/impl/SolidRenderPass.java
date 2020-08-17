package me.jellysquid.mods.sodium.client.render.chunk.passes.impl;

import me.jellysquid.mods.sodium.client.render.chunk.passes.BlockRenderPass;
import me.jellysquid.mods.sodium.client.render.chunk.passes.BlockLayer;
import net.minecraft.client.render.RenderLayer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.util.Identifier;

public class SolidRenderPass extends BlockRenderPass {
    public SolidRenderPass(int ordinal, Identifier id, BlockLayer... layers) {
        super(ordinal, id, true, layers);
    }

    @Override
    public void beginRender() {
        RenderLayer.getSolid().startDrawing();

        RenderSystem.enableAlphaTest();
    }

    @Override
    public void endRender() {
        RenderSystem.disableAlphaTest();

        RenderLayer.getSolid().endDrawing();
    }
}
