package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.Player.Player;

public class Position {

    private int col;
    private int row;
    private int startCol;
    private int startRow;

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
        startCol = col;
        startRow = row;
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

    public void resetPos() {
        col = startCol;
        row = startRow;
    }
}
