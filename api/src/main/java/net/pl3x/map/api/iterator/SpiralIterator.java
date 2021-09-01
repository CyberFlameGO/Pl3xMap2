package net.pl3x.map.api.iterator;

import java.util.Iterator;

public abstract class SpiralIterator<T> implements Iterator<T> {
    protected Direction direction = Direction.WEST;
    protected int currentX;
    protected int currentZ;

    protected final int totalSteps;
    protected int currentStep;

    protected int totalStepsInLeg;
    protected int currentStepInLeg;
    protected int legAxis;

    public SpiralIterator(int centerX, int centerZ, int radius) {
        this.currentX = centerX;
        this.currentZ = centerZ;
        this.totalSteps = (radius * 2 + 1) * (radius * 2 + 1);
    }

    @Override
    public boolean hasNext() {
        return this.currentStep < this.totalSteps;
    }

    public abstract T fromCoordinates(int x, int z);

    @Override
    public T next() {
        T t = this.fromCoordinates(this.currentX, this.currentZ);
        if (!hasNext()) {
            return t;
        }

        switch (this.direction) {
            case SOUTH -> this.currentZ += 1;
            case WEST -> this.currentX -= 1;
            case NORTH -> this.currentZ -= 1;
            default -> this.currentX += 1;
        }

        currentStep++;
        currentStepInLeg++;
        if (currentStepInLeg > totalStepsInLeg) {
            direction = direction.next();
            currentStepInLeg = 0;
            legAxis++;
            if (legAxis > 1) {
                legAxis = 0;
                totalStepsInLeg++;
            }
        }

        return t;
    }

    public enum Direction {
        EAST, SOUTH, WEST, NORTH;

        private static final Direction[] values = values();

        public Direction next() {
            return values[(ordinal() + 1) % values.length];
        }
    }
}
