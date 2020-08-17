package me.jellysquid.mods.sodium.mixin.features.options;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.text.Text;

@Mixin(OptionsScreen.class)
public class MixinOptionsScreen extends Screen {
    protected MixinOptionsScreen(Text title) {
        super(title);
    }

    @Dynamic
    @Inject(method = "method_19828(Lnet/minecraft/client/gui/widget/ButtonWidget;)V", at = @At("HEAD"), cancellable = true)
    private void open(ButtonWidget widget, CallbackInfo ci) {
        this.client.openScreen(new SodiumOptionsGUI(this));

        ci.cancel();
    }
}
