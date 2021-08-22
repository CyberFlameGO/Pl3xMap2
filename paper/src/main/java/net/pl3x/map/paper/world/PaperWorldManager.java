package net.pl3x.map.paper.world;

import net.pl3x.map.api.world.MapWorld;
import net.pl3x.map.api.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.generator.WorldInfo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PaperWorldManager implements WorldManager {
    private final Map<UUID, MapWorld> mapWorlds = new ConcurrentHashMap<>();

    @Override
    public Collection<MapWorld> getMapWorlds() {
        return Collections.unmodifiableCollection(this.mapWorlds.values());
    }

    @Override
    public MapWorld getMapWorld(UUID uuid) {
        MapWorld mapWorld = this.mapWorlds.get(uuid);
        if (mapWorld != null) {
            return mapWorld;
        }
        World world = Bukkit.getWorld(uuid);
        if (world == null) {
            return null;
        }
        mapWorld = new PaperMapWorld(world);
        this.mapWorlds.put(uuid, mapWorld);
        return mapWorld;
    }

    @Override
    public MapWorld getMapWorld(String name) {
        World world = Bukkit.getWorld(name);
        return world == null ? null : getMapWorld(world.getUID());
    }

    @Override
    public void unloadWorld(UUID uuid) {
        this.mapWorlds.remove(uuid);
    }

    @Override
    public void unloadWorld(String name) {
        World world = Bukkit.getWorld(name);
        if (world != null) {
            this.mapWorlds.remove(world.getUID());
        }
    }

    @Override
    public List<String> getLoadedWorldNames() {
        return Bukkit.getWorlds().stream()
                .map(WorldInfo::getName)
                .collect(Collectors.toList());
    }
}
