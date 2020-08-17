package me.jellysquid.mods.sodium.client.model.light.cache;

import me.jellysquid.mods.sodium.client.model.light.data.LightDataAccess;
import it.unimi.dsi.fastutil.longs.Long2LongLinkedOpenHashMap;
import net.minecraft.world.BlockRenderView;
import net.minecraft.util.math.BlockPos;

/**
 * A light data cache which uses a hash table to store previously accessed values.
 */
public class HashLightDataCache extends LightDataAccess {
    private final Long2LongLinkedOpenHashMap map = new Long2LongLinkedOpenHashMap(1024, 0.50f);

    public HashLightDataCache(BlockRenderView world) {
        this.world = world;
    }

    @Override
    public long get(int x, int y, int z) {
        long key = BlockPos.asLong(x, y, z);
        long word = this.map.getAndMoveToFirst(key);

        if (word == 0) {
            if (this.map.size() > 1024) {
                this.map.removeLastLong();
            }

            this.map.put(key, word = this.compute(x, y, z));
        }

        return word;
    }

    public void clear() {
        this.map.clear();
    }
}
