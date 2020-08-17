package me.jellysquid.mods.sodium.mixin.features.options;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import me.jellysquid.mods.sodium.client.SodiumClientMod;
import net.minecraft.client.options.CloudRenderMode;
import net.minecraft.client.options.GraphicsMode;
import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GameOptions.class)
public class MixinGameOptions {
    @Shadow
    public int viewDistance;

    @Shadow
    public GraphicsMode graphicsMode;

    /**
     * @author JellySquid
     * @reason Make the cloud render mode user-configurable
     */
    @Overwrite
    public CloudRenderMode getCloudRenderMode() {
        SodiumGameOptions options = SodiumClientMod.options();

        if (this.viewDistance < 4 || !options.quality.enableClouds) {
            return CloudRenderMode.OFF;
        }

        return options.quality.cloudQuality.isFancy(this.graphicsMode) ? CloudRenderMode.FANCY : CloudRenderMode.FAST;
    }
}
