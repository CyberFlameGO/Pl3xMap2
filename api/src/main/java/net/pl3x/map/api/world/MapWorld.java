package net.pl3x.map.api.world;

import java.util.UUID;

public interface MapWorld {
    /**
     * Get the name of this world
     *
     * @return World name
     */
    String getName();

    /**
     * Get the UUID of this world
     *
     * @return World UUID
     */
    UUID getUUID();

    /**
     * Check if a render is currently in progress on this world
     *
     * @return True if a render is in progress
     */
    boolean isRendering();
}
