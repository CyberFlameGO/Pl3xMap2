package net.pl3x.map.api.coordinate;

public class RegionCoordinate extends Coordinate {
    public RegionCoordinate(int x, int z) {
        super(x, z);
    }

    public int getBlockX() {
        return regionToBlock(this.x);
    }

    public int getBlockZ() {
        return regionToBlock(this.z);
    }

    public int getChunkX() {
        return regionToChunk(this.x);
    }

    public int getChunkZ() {
        return regionToChunk(this.z);
    }

    public BlockCoordinate getBlockCoordinate() {
        return new BlockCoordinate(getBlockX(), getBlockZ());
    }

    public ChunkCoordinate getChunkCoordinate() {
        return new ChunkCoordinate(getChunkX(), getChunkZ());
    }

    @Override
    public String toString() {
        return "RegionCoordinate{x=" + this.x + ",z=" + this.z + "}";
    }
}
