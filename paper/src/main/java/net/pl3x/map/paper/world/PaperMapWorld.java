package net.pl3x.map.paper.world;

import net.pl3x.map.api.world.MapWorld;
import org.bukkit.World;

import java.util.UUID;

public class PaperMapWorld implements MapWorld {
    private final World world;

    public PaperMapWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public String getName() {
        return world.getName();
    }

    @Override
    public UUID getUUID() {
        return world.getUID();
    }

    @Override
    public boolean isRendering() {
        return false; // todo
    }
}
