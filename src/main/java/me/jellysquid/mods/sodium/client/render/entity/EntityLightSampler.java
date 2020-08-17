package me.jellysquid.mods.sodium.client.render.entity;

import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;

public interface EntityLightSampler<T extends Entity> {
    int bridge$getBlockLight(T entity, BlockPos pos);

    int bridge$getSkyLight(T entity, BlockPos pos);
}
