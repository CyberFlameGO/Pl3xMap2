package net.pl3x.map.api.player;

import java.util.UUID;

public interface Player {
    UUID getUUID();

    String getName();

    boolean isHidden();

    void setHidden(boolean hidden, boolean persistent);
}
