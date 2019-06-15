package org.academiadecodigo.batmancave.Player;

import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.batmancave.gameobjects.Usables.*;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Directions;
import org.academiadecodigo.batmancave.maze.MovementDetector;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public abstract class Player implements KeyboardHandler {

    //properties
    protected Position pos;
    protected MovementDetector movementDetector;
    protected MazeGfx mazeGfx;
    private boolean hasFlag;

    public Player(int col, int row) {
        pos = new Position(col, row);
        hasFlag = false;
    }

    //walk method
    public abstract void walk();

    @Override
    public abstract void keyPressed(KeyboardEvent keyboardEvent);

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        int key = keyboardEvent.getKey();

        switch (key) {

        }
    }


    public void setHasFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    public boolean getHasFlag() {
        return hasFlag;
    }

    public void setMovementDetector(MovementDetector movementDetector) {
        this.movementDetector = movementDetector;
    }

    public void setMazeGfx (MazeGfx mazeGfx) {
        this.mazeGfx = mazeGfx;
    }

    public Position getPos() {
        return pos;
    }

    public void reset() {

        hasFlag = false;
        pos.resetPos();
    }

    public boolean equals (Player obj) {
        if (this.getPos().getCol() == obj.getPos().getCol() && this.getPos().getRow() == obj.getPos().getRow()) {
            return true;
        }
        return false;
    }
}