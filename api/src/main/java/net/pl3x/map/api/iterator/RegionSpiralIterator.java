package net.pl3x.map.api.iterator;

import net.pl3x.map.api.coordinate.RegionCoordinate;

public final class RegionSpiralIterator extends SpiralIterator<RegionCoordinate> {
    public RegionSpiralIterator(int x, int z, int radius) {
        super(x, z, radius);
    }

    @Override
    public RegionCoordinate fromCoordinates(final int x, final int z) {
        return new RegionCoordinate(x, z);
    }
}
