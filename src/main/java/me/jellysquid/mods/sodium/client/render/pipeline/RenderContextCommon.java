package me.jellysquid.mods.sodium.client.render.pipeline;

import me.jellysquid.mods.sodium.client.model.quad.blender.SmoothBiomeColorBlender;
import me.jellysquid.mods.sodium.client.model.quad.blender.FlatBiomeColorBlender;
import me.jellysquid.mods.sodium.client.model.quad.blender.BiomeColorBlender;
import net.minecraft.client.MinecraftClient;

public class RenderContextCommon {
    public static BiomeColorBlender createBiomeColorBlender() {
        return MinecraftClient.getInstance().options.biomeBlendRadius <= 0 ? new FlatBiomeColorBlender() : new SmoothBiomeColorBlender();
    }
}
