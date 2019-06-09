package org.academiadecodigo.batmancave.maze;

public enum CellType {
    WALL(false,false),
    ROOM(false, false);

    private boolean excavated;
    private boolean goodPath;

    CellType(boolean excavated, boolean solution) {
        this.excavated = excavated;
        this.goodPath = solution;
    }

    public void isExcavated() {
        if(this == CellType.ROOM) {
            excavated = true;
        }
    }

    public void setGoodPath(boolean goodPath) {
        this.goodPath = goodPath;
    }

    public boolean getExcavated() {
        return excavated;
    }

    public boolean getGoodPath() {
        return goodPath;
    }
}
