package net.pl3x.map.paper.player;

import net.pl3x.map.api.player.Player;
import net.pl3x.map.paper.Pl3xMapPaper;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class PaperPlayer implements Player {
    public static final NamespacedKey hiddenPDC = new NamespacedKey(Pl3xMapPaper.getPlugin(Pl3xMapPaper.class), "hidden");

    private final org.bukkit.entity.Player bukkitPlayer;

    private boolean hidden;

    public PaperPlayer(org.bukkit.entity.Player player) {
        this.bukkitPlayer = player;
    }

    @Override
    public UUID getUUID() {
        return this.bukkitPlayer.getUniqueId();
    }

    @Override
    public String getName() {
        return this.bukkitPlayer.getName();
    }

    @Override
    public boolean isHidden() {
        return this.hidden || getByte(hiddenPDC) != (byte) 0;
    }

    @Override
    public void setHidden(boolean hidden, boolean persistent) {
        this.hidden = hidden;
        if (persistent) {
            setByte(hiddenPDC, (byte) (hidden ? 1 : 0));
        }
    }

    private byte getByte(NamespacedKey key) {
        return this.bukkitPlayer.getPersistentDataContainer().getOrDefault(key, PersistentDataType.BYTE, (byte) 0);
    }

    private void setByte(NamespacedKey key, byte value) {
        this.bukkitPlayer.getPersistentDataContainer().set(key, PersistentDataType.BYTE, value);
    }
}
