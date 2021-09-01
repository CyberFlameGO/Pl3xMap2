package net.pl3x.map.api.iterator;

import net.pl3x.map.api.coordinate.BlockCoordinate;

public final class BlockSpiralIterator extends SpiralIterator<BlockCoordinate> {
    public BlockSpiralIterator(int x, int z, int radius) {
        super(x, z, radius);
    }

    @Override
    public BlockCoordinate fromCoordinates(int x, int z) {
        return new BlockCoordinate(x, z);
    }
}
