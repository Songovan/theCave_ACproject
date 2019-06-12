package org.academiadecodigo.batmancave.Player;

import org.academiadecodigo.batmancave.maze.Directions;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class PlayerOne extends Player implements KeyboardHandler {

    public PlayerOne(int col, int row) {
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
        up.setKey(KeyboardEvent.KEY_W);
        right.setKey(KeyboardEvent.KEY_D);
        down.setKey(KeyboardEvent.KEY_S);
        left.setKey(KeyboardEvent.KEY_A);

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
            case KeyboardEvent.KEY_W:
                if (movementDetector.checkMove(Directions.UP, this)) {
                    pos.changePosition(0, -1);
                    mazeGfx.movePlayerOne(0, -1);
                    super.moveFlag(this);
                }
                break;
            case KeyboardEvent.KEY_D:
                if (movementDetector.checkMove(Directions.RIGHT, this)) {
                    pos.changePosition(1, 0);
                    mazeGfx.movePlayerOne(1, 0);
                    super.moveFlag(this);
                }
                break;
            case KeyboardEvent.KEY_S:
                if (movementDetector.checkMove(Directions.DOWN, this)) {
                    pos.changePosition(0, 1);
                    mazeGfx.movePlayerOne(0, 1);
                    super.moveFlag(this);
                    // MOVE DOWN
                }
                break;
            case KeyboardEvent.KEY_A:
                if (movementDetector.checkMove(Directions.LEFT, this)) {
                    pos.changePosition(-1, 0);
                    mazeGfx.movePlayerOne(-1, 0);
                    super.moveFlag(this);
                    // MOVE LEFT
                }
                break;
            default:
                break;
        }

        super.setFlag(movementDetector.checkFlag(pos));
        System.out.println(super.getFlag());
    }

}
