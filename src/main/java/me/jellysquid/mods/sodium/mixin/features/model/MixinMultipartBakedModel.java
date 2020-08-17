package me.jellysquid.mods.sodium.mixin.features.model;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.render.model.MultipartBakedModel;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Overwrite;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Final;
import net.minecraft.block.BlockState;

import java.util.function.Predicate;
import java.util.*;

@Mixin(MultipartBakedModel.class)
public class MixinMultipartBakedModel {
    private Map<BlockState, List<BakedModel>> stateCacheFast;

    @Shadow
    @Final
    private List<Pair<Predicate<BlockState>, BakedModel>> components;


    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(List<Pair<Predicate<BlockState>, BakedModel>> components, CallbackInfo ci) {
        this.stateCacheFast = new IdentityHashMap<>();
    }

    /**
     * @author JellySquid
     * @reason Avoid expensive allocations and replace bitfield indirection
     */
    @Overwrite
    public List<BakedQuad> getQuads(BlockState state, Direction face, Random random) {
        if (state == null) {
            return Collections.emptyList();
        }

        List<BakedModel> models = this.stateCacheFast.get(state);

        if (models == null) {
            models = new ArrayList<>(this.components.size());

            for (Pair<Predicate<BlockState>, BakedModel> pair : this.components) {
                if ((pair.getLeft()).test(state)) {
                    models.add(pair.getRight());
                }
            }

            this.stateCacheFast.put(state, models);
        }

        List<BakedQuad> list = new ArrayList<>();

        long seed = random.nextLong();

        for (BakedModel model : models) {
            random.setSeed(seed);

            list.addAll(model.getQuads(state, face, random));
        }

        return list;
    }

}
