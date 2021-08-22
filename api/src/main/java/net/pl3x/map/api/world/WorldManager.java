package net.pl3x.map.api.world;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface WorldManager {
    /**
     * Get all the loaded map worlds
     *
     * @return map worlds
     */
    Collection<MapWorld> getMapWorlds();

    /**
     * Get map world if enabled
     *
     * @param uuid UUID of world
     * @return map world
     */
    MapWorld getMapWorld(UUID uuid);

    /**
     * Get map world if enabled
     *
     * @param name Name of world
     * @return map world
     */
    MapWorld getMapWorld(String name);

    /**
     * Unload a world
     *
     * @param uuid UUID of world
     */
    void unloadWorld(UUID uuid);

    /**
     * Unload a world
     *
     * @param name Name of world
     */
    void unloadWorld(String name);

    /**
     * Get names of loaded worlds
     *
     * @return list of loaded worlds
     */
    List<String> getLoadedWorldNames();
}
