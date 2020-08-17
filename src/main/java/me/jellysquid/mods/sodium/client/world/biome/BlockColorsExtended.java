package me.jellysquid.mods.sodium.client.world.biome;

import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.block.BlockState;

public interface BlockColorsExtended {
    BlockColorProvider getColorProvider(BlockState state);
}
