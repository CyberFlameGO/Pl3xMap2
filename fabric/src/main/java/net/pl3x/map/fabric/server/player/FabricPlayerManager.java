package net.pl3x.map.fabric.server.player;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.pl3x.map.api.player.Player;
import net.pl3x.map.api.player.PlayerManager;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class FabricPlayerManager implements PlayerManager {
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
        PlayerEntity fabricPlayer = getPlayerManager().getPlayer(uuid);
        if (fabricPlayer == null) {
            return null;
        }
        player = new FabricPlayer(fabricPlayer);
        this.players.put(uuid, player);
        return player;
    }

    @Override
    public Player getPlayer(String name) {
        PlayerEntity fabricPlayer = getPlayerManager().getPlayer(name);
        return fabricPlayer == null ? null : getPlayer(fabricPlayer.getUuid());
    }

    @Override
    public void unloadPlayer(UUID uuid) {
        this.players.remove(uuid);
    }

    @Override
    public List<String> getOnlinePlayerNames() {
        return getPlayerManager().getPlayerList().stream()
                .map(player -> player.getGameProfile().getName())
                .collect(Collectors.toList());
    }

    private net.minecraft.server.PlayerManager getPlayerManager() {
        return ((MinecraftServer) FabricLoader.getInstance().getGameInstance()).getPlayerManager();
    }
}
