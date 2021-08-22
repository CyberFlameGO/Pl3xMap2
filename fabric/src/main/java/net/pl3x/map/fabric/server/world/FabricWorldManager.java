package net.pl3x.map.fabric.server.world;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.pl3x.map.api.world.MapWorld;
import net.pl3x.map.api.world.WorldManager;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class FabricWorldManager implements WorldManager {
    private final Map<String, MapWorld> mapWorlds = new ConcurrentHashMap<>();

    @Override
    public Collection<MapWorld> getMapWorlds() {
        return Collections.unmodifiableCollection(this.mapWorlds.values());
    }

    @Override
    public MapWorld getMapWorld(UUID uuid) {
        throw new UnsupportedOperationException("Fabric worlds dont have UUIDs");
    }

    @Override
    public MapWorld getMapWorld(String name) {
        MapWorld mapWorld = this.mapWorlds.get(name);
        if (mapWorld != null) {
            return mapWorld;
        }
        ServerWorld world = getServerWorld(name);
        if (world == null) {
            return null;
        }
        mapWorld = new FabricMapWorld(world);
        this.mapWorlds.put(name, mapWorld);
        return mapWorld;
    }

    @Override
    public void unloadWorld(UUID uuid) {
        throw new UnsupportedOperationException("Fabric worlds dont have UUIDs");
    }

    @Override
    public void unloadWorld(String name) {
        this.mapWorlds.remove(name);
    }

    @Override
    public List<String> getLoadedWorldNames() {
        return new HashSet<>((Collection<ServerWorld>) getServer().getWorlds()).stream()
                .map(world -> world.getRegistryKey().getValue().getPath())
                .collect(Collectors.toList());
    }

    private ServerWorld getServerWorld(String name) {
        for (ServerWorld world : getServer().getWorlds()) {
            if (world.getRegistryKey().getValue().getPath().equals(name)) {
                return world;
            }
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    private MinecraftServer getServer() {
        return (MinecraftServer) FabricLoader.getInstance().getGameInstance();
    }
}
