package org.academiadecodigo.batmancave.gameobjects.Usables;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Maze;

public class Flag {

    private Position pos;
    private MazeGfx mazeGfx;

    public Flag(int startCol, int startRow) {
        pos = new Position(startCol, startRow);
    }

    public void setMazeGfx(MazeGfx mazeGfx) {
        this.mazeGfx = mazeGfx;
    }

    public void moveFlag(int col, int row) {
        pos.changePosition(col, row);
        mazeGfx.moveFlag(col, row);
    }

    public Position getPos() {
        return pos;
    }
}
