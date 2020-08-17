package me.jellysquid.mods.sodium.mixin.core.model;

import me.jellysquid.mods.sodium.client.world.biome.BlockColorsExtended;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.block.BlockColors;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

@Mixin(BlockColors.class)
public class MixinBlockColors implements BlockColorsExtended {
    private Reference2ReferenceMap<Block, BlockColorProvider> blocksToColor;

    private static final BlockColorProvider DEFAULT_PROVIDER = (state, view, pos, tint) -> -1;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        this.blocksToColor = new Reference2ReferenceOpenHashMap<>();
        this.blocksToColor.defaultReturnValue(DEFAULT_PROVIDER);
    }

    @Inject(method = "registerColorProvider", at = @At("HEAD"))
    private void preRegisterColor(BlockColorProvider provider, Block[] blocks, CallbackInfo ci) {
        for (Block block : blocks) {
            this.blocksToColor.put(block, provider);
        }
    }

    @Override
    public BlockColorProvider getColorProvider(BlockState state) {
        return this.blocksToColor.get(state.getBlock());
    }
}
