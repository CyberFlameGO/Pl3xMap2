package net.pl3x.map.fabric.server.player;

import net.minecraft.entity.player.PlayerEntity;
import net.pl3x.map.api.player.Player;

import java.util.UUID;

public class FabricPlayer implements Player {
    private final PlayerEntity fabricPlayer;

    private boolean hidden;

    public FabricPlayer(PlayerEntity player) {
        this.fabricPlayer = player;
    }

    @Override
    public UUID getUUID() {
        return this.fabricPlayer.getUuid();
    }

    @Override
    public String getName() {
        return this.fabricPlayer.getGameProfile().getName();
    }

    @Override
    public boolean isHidden() {
        return this.hidden /* todo || getByte(hiddenPDC) != (byte) 0*/;
    }

    @Override
    public void setHidden(boolean hidden, boolean persistent) {
        this.hidden = hidden;
        if (persistent) {
            // todo
        }
    }
}
