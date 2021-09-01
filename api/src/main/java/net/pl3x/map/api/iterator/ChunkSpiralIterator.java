package net.pl3x.map.api.iterator;

import net.pl3x.map.api.coordinate.ChunkCoordinate;

public final class ChunkSpiralIterator extends SpiralIterator<ChunkCoordinate> {
    public ChunkSpiralIterator(int x, int z, int radius) {
        super(x, z, radius);
    }

    @Override
    public ChunkCoordinate fromCoordinates(int x, int z) {
        return new ChunkCoordinate(x, z);
    }
}
