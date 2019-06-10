package org.academiadecodigo.batmancave;

public class Position {

    private int col;
    private int row;

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public void changePosition(int colChange, int rowChange) {
        col += colChange;
        row += rowChange;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
