package net.pl3x.map;

import net.pl3x.map.player.PlayerManager;
import net.pl3x.map.world.WorldManager;

/**
 * Pl3xMap API
 *
 * <p>The API allows other plugins on the server integrate with Pl3xmap.</p>
 *
 * <p>This interface represents the base of the API package. All functions are
 * accessed via this interface.</p>
 */
public interface Pl3xMap {
    /**
     * Get instance to the running Pl3xMap api
     *
     * @return Pl3xMap api
     */
    static Pl3xMap api() {
        return Pl3xMapPlugin.getInstance();
    }

    /**
     * Reload the plugin and configs
     */
    void reload();

    /**
     * Get the player manager
     *
     * @return player manager
     */
    PlayerManager getPlayerManager();

    /**
     * Get the world manager
     *
     * @return world manager
     */
    WorldManager getWorldManager();
}
