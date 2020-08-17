package me.jellysquid.mods.sodium.mixin.features.texture_tracking;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import me.jellysquid.mods.sodium.client.render.texture.SpriteUtil;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.util.SpriteIdentifier;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.client.texture.Sprite;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SpriteIdentifier.class)
public class MixinSpriteIdentifier {
    @Inject(method = "getSprite", at = @At("RETURN"))
    private void preReturnSprite(CallbackInfoReturnable<Sprite> cir) {
        Sprite sprite = cir.getReturnValue();

        if (sprite != null) {
            SpriteUtil.markSpriteActive(sprite);
        }
    }
}
