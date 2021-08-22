package net.pl3x.map.fabric.server.world;

import net.minecraft.server.world.ServerWorld;
import net.pl3x.map.api.world.MapWorld;

import java.util.UUID;

public class FabricMapWorld implements MapWorld {
    private final ServerWorld world;

    public FabricMapWorld(ServerWorld world) {
        this.world = world;
    }

    public ServerWorld getWorld() {
        return world;
    }

    @Override
    public String getName() {
        return world.getRegistryKey().getValue().getPath();
    }

    @Override
    public UUID getUUID() {
        throw new UnsupportedOperationException("Fabric worlds dont have UUIDs");
    }

    @Override
    public boolean isRendering() {
        return false; // todo
    }
}
