package me.jellysquid.mods.sodium.client.gui.options.storage;

import me.jellysquid.mods.sodium.client.SodiumClientMod;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.MinecraftClient;

public class MinecraftOptionsStorage implements OptionStorage<GameOptions> {
    private final MinecraftClient client;

    public MinecraftOptionsStorage() {
        this.client = MinecraftClient.getInstance();
    }

    @Override
    public GameOptions getData() {
        return this.client.options;
    }

    @Override
    public void save() {
        this.getData().write();

        SodiumClientMod.logger().info("Flushed changes to Minecraft configuration");
    }
}
