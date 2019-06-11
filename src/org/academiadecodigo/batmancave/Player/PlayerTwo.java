package org.academiadecodigo.batmancave.Player;

import org.academiadecodigo.batmancave.maze.Directions;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class PlayerTwo extends Player implements KeyboardHandler {

    public PlayerTwo(int col, int row) {
        super(col, row);
    }

    @Override
    public void walk() {

        Keyboard keyboard = new Keyboard(this);

        // setup events
        KeyboardEvent up = new KeyboardEvent();
        KeyboardEvent right = new KeyboardEvent();
        KeyboardEvent down = new KeyboardEvent();
        KeyboardEvent left = new KeyboardEvent();

        // assign event key
        up.setKey(KeyboardEvent.KEY_UP);
        right.setKey(KeyboardEvent.KEY_RIGHT);
        down.setKey(KeyboardEvent.KEY_DOWN);
        left.setKey(KeyboardEvent.KEY_LEFT);

        // set event type
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        // add event listeners
        keyboard.addEventListener(up);
        keyboard.addEventListener(right);
        keyboard.addEventListener(down);
        keyboard.addEventListener(left);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        int key = keyboardEvent.getKey();

        switch (key) {
            case KeyboardEvent.KEY_UP:
                if (movementDetector.checkMove(Directions.UP, this)) {
                    pos.changePosition(0, -1);
                    mazeGfx.movePlayerTwo(0, -1);
                }
                break;
            case KeyboardEvent.KEY_RIGHT:
                if (movementDetector.checkMove(Directions.RIGHT, this)) {
                    pos.changePosition(1, 0);
                    mazeGfx.movePlayerTwo(1, 0);
                }
                break;
            case KeyboardEvent.KEY_DOWN:
                if (movementDetector.checkMove(Directions.DOWN, this)) {
                    pos.changePosition(0, 1);
                    mazeGfx.movePlayerTwo(0, 1);
                    // MOVE DOWN
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                if (movementDetector.checkMove(Directions.LEFT, this)) {
                    pos.changePosition(-1, 0);
                    mazeGfx.movePlayerTwo(-1, 0);
                    // MOVE LEFT
                }
                break;
            default:
                break;
        }
    }
}
