package me.jellysquid.mods.sodium.mixin.core.model;

import me.jellysquid.mods.sodium.client.world.biome.ItemColorsExtended;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap;
import net.minecraft.client.color.item.ItemColorProvider;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.color.item.ItemColors;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.item.ItemStack;

@Mixin(ItemColors.class)
public class MixinItemColors implements ItemColorsExtended {
    private Reference2ReferenceMap<ItemConvertible, ItemColorProvider> itemsToColor;

    private static final ItemColorProvider DEFAULT_PROVIDER = (stack, tintIdx) -> -1;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        this.itemsToColor = new Reference2ReferenceOpenHashMap<>();
        this.itemsToColor.defaultReturnValue(DEFAULT_PROVIDER);
    }

    @Inject(method = "register", at = @At("HEAD"))
    private void preRegisterColor(ItemColorProvider mapper, ItemConvertible[] convertibles, CallbackInfo ci) {
        for (ItemConvertible convertible : convertibles) {
            this.itemsToColor.put(convertible.asItem(), mapper);
        }
    }

    @Override
    public ItemColorProvider getColorProvider(ItemStack stack) {
        return this.itemsToColor.get(stack.getItem());
    }
}
