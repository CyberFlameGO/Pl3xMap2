package net.pl3x.map.api;

import net.pl3x.map.api.player.PlayerManager;
import net.pl3x.map.api.world.WorldManager;

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
     * Get instance to the running Pl3xMap implementation
     *
     * @return Pl3xMap implementation
     */
    static Pl3xMap api() {
        return Instance.get();
    }

    /**
     * Get the prefix for command output
     *
     * @return command prefix
     */
    String getCommandPrefix();

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

    /**
     * For internal use only
     *
     * <p>Use the {@link Pl3xMap#api} method to get an instance of the API</p>
     */
    class Instance {
        private static Pl3xMap instance;

        /**
         * For internal use only
         *
         * <p>Registers the instance of the currently running Pl3xMap implementation</p>
         *
         * @param pl3xmap Pl3xMap implementation
         */
        public static void register(Pl3xMap pl3xmap) {
            instance = pl3xmap;
        }

        /**
         * Get instance to the running Pl3xMap implementation
         *
         * @return Pl3xMap implementation
         */
        public static Pl3xMap get() {
            return instance;
        }
    }
}
