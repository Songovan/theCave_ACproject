package org.academiadecodigo.batmancave.gameobjects.Usables;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Maze;

public class Flag {

    private Position pos;
    private Position startPos;

    //TODO set random start position middle vs corners
    public Flag(int startCol, int startRow) {
        pos = new Position(startCol, startRow);
        startPos = pos;
    }

    public void resetFlag() {
        pos = startPos;
    }

    public Position getPos() {
        return pos;
    }



    public void setPos(Position pos) {
        this.pos = pos;
    }
}
