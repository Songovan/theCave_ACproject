package org.academiadecodigo.batmancave.maze;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Cell {

    private CellType type;
    private int[] position;
    private boolean excavated;
    private Picture cellGfx;


    public Cell(CellType type) {
        this.type = type;
        position = new int[2];
        excavated = false;
    }

    public void setPosition(int col, int row) {
       position[0] = col;
       position[1] = row;
    }

    public int[] getPosition() {
        return position;
    }

    public CellType getType() {
        return type;
    }

    public boolean isExcavated() {
        return excavated;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setCellGfx(Picture cellGfx) {
        this.cellGfx = cellGfx;
    }

    public Picture getCellGfx() {
        return cellGfx;
    }

    public void excavate() {
        excavated = true;
    }
}
