package net.pl3x.map.api.coordinate;

public class ChunkCoordinate extends Coordinate {
    public ChunkCoordinate(int x, int z) {
        super(x, z);
    }

    public int getBlockX() {
        return chunkToBlock(this.x);
    }

    public int getBlockZ() {
        return chunkToBlock(this.z);
    }

    public int getRegionX() {
        return chunkToRegion(this.x);
    }

    public int getRegionZ() {
        return chunkToRegion(this.z);
    }

    public BlockCoordinate getBlockCoordinate() {
        return new BlockCoordinate(getBlockX(), getBlockZ());
    }

    public RegionCoordinate getRegionCoordinate() {
        return new RegionCoordinate(getRegionX(), getRegionZ());
    }

    @Override
    public String toString() {
        return "ChunkCoordinate{x=" + this.x + ",z=" + this.z + "}";
    }
}
