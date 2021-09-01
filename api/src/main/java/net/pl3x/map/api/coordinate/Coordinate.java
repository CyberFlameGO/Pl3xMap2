package net.pl3x.map.api.coordinate;

public abstract class Coordinate {
    protected final int x;
    protected final int z;

    public Coordinate(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public int getX() {
        return this.x;
    }

    public int getZ() {
        return this.z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coordinate other)) {
            return false;
        }
        return this.x == other.x && this.z == other.z;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.x;
        result = prime * result + this.z;
        return result;
    }

    public static int regionToBlock(int n) {
        return n << 9;
    }

    public static int blockToRegion(int n) {
        return n >> 9;
    }

    public static int regionToChunk(int n) {
        return n << 5;
    }

    public static int chunkToRegion(int n) {
        return n >> 5;
    }

    public static int chunkToBlock(int n) {
        return n << 4;
    }

    public static int blockToChunk(int n) {
        return n >> 4;
    }
}
