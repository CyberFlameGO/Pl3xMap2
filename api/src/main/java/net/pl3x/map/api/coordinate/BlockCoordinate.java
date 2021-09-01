package net.pl3x.map.api.coordinate;

public class BlockCoordinate extends Coordinate {
    public BlockCoordinate(int x, int z) {
        super(x, z);
    }

    public int getChunkX() {
        return blockToChunk(this.x);
    }

    public int getChunkZ() {
        return blockToChunk(this.z);
    }

    public int getRegionX() {
        return blockToRegion(this.x);
    }

    public int getRegionZ() {
        return blockToRegion(this.z);
    }

    public ChunkCoordinate getChunkCoordinate() {
        return new ChunkCoordinate(getChunkX(), getChunkZ());
    }

    public RegionCoordinate getRegionCoordinate() {
        return new RegionCoordinate(getRegionX(), getRegionZ());
    }

    @Override
    public String toString() {
        return "BlockCoordinate{x=" + this.x + ",z=" + this.z + "}";
    }
}
