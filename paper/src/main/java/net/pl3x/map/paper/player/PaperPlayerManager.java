package net.pl3x.map.paper.player;

import net.pl3x.map.api.player.Player;
import net.pl3x.map.api.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PaperPlayerManager implements PlayerManager {
    private final Map<UUID, Player> players = new ConcurrentHashMap<>();

    @Override
    public Collection<Player> getPlayers() {
        return Collections.unmodifiableCollection(this.players.values());
    }

    @Override
    public Player getPlayer(UUID uuid) {
        Player player = this.players.get(uuid);
        if (player != null) {
            return player;
        }
        org.bukkit.entity.Player bukkitPlayer = Bukkit.getPlayer(uuid);
        if (bukkitPlayer == null) {
            return null;
        }
        player = new PaperPlayer(bukkitPlayer);
        this.players.put(uuid, player);
        return player;
    }

    @Override
    public Player getPlayer(String name) {
        org.bukkit.entity.Player bukkitPlayer = Bukkit.getPlayer(name);
        return bukkitPlayer == null ? null : getPlayer(bukkitPlayer.getUniqueId());
    }

    @Override
    public void unloadPlayer(UUID uuid) {
        this.players.remove(uuid);
    }

    @Override
    public List<String> getOnlinePlayerNames() {
        return Bukkit.getOnlinePlayers().stream()
                .map(HumanEntity::getName)
                .collect(Collectors.toList());
    }
}
