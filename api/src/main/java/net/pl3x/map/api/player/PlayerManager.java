package net.pl3x.map.api.player;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface PlayerManager {
    /**
     * Get all online players
     *
     * @return players
     */
    Collection<Player> getPlayers();

    /**
     * Get player if online
     *
     * @param uuid UUID of player
     * @return player
     */
    Player getPlayer(UUID uuid);

    /**
     * Get player if online
     *
     * @param name Name of player
     * @return player
     */
    Player getPlayer(String name);

    /**
     * Unload a player
     *
     * @param uuid UUID of player
     */
    void unloadPlayer(UUID uuid);

    /**
     * Get names of online players
     *
     * @return list of online player names
     */
    List<String> getOnlinePlayerNames();
}
