package org.academiadecodigo.batmancave.maze;

public enum Directions {
    UP(2),
    RIGHT(3),
    DOWN(0),
    LEFT(1);

    int oppositeDirection;

    Directions(int oppositeDirection) {
        this.oppositeDirection = oppositeDirection;
    }

    public int getOppositeDirection() {
        return oppositeDirection;
    }
}
