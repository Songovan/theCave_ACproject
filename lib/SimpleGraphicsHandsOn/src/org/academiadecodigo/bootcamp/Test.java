package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class Test implements KeyboardHandler, MouseHandler {

    Rectangle rectangle = new Rectangle(0, 0, 800, 600);
    Rectangle rabbit = new Rectangle(360,460,70,70);

    public void test() {

        rectangle.draw();
        rectangle.setColor(Color.PINK);
        rectangle.fill();

        rabbit.draw();
        rabbit.setColor(Color.BLUE);
        rabbit.fill();

        //keyboard
        Keyboard keyboard = new Keyboard(this);

        //SPACE key pressed
        KeyboardEvent eventSpace = new KeyboardEvent();
        eventSpace.setKey(KeyboardEvent.KEY_SPACE);
        eventSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventSpace);

        //SPACE key released
        KeyboardEvent eventSpaceR = new KeyboardEvent();
        eventSpaceR.setKey(KeyboardEvent.KEY_SPACE);
        eventSpaceR.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(eventSpaceR);

        //D key
        KeyboardEvent eventLetterD = new KeyboardEvent();
        eventLetterD.setKey(KeyboardEvent.KEY_D);
        eventLetterD.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventLetterD);

        //A key
        KeyboardEvent eventLetterA = new KeyboardEvent();
        eventLetterA.setKey(KeyboardEvent.KEY_A);
        eventLetterA.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventLetterA);

        //W key
        KeyboardEvent eventLetterW = new KeyboardEvent();
        eventLetterW.setKey(KeyboardEvent.KEY_W);
        eventLetterW.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventLetterW);

        //S key
        KeyboardEvent eventLetterS = new KeyboardEvent();
        eventLetterS.setKey(KeyboardEvent.KEY_S);
        eventLetterS.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventLetterS);

        //mouse
        Mouse mouse = new Mouse(this);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_A && rabbit.getX() > 0) {
            rabbit.translate(-20, 0);
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_D && rabbit.getX() < rectangle.getWidth() - rabbit.getWidth() - 10) {
            rabbit.translate(20, 0);
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_S && rabbit.getY() < rectangle.getHeight() - rabbit.getHeight() - 10) {
            rabbit.translate(0, 20);
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_W && rabbit.getY() > 0) {
            rabbit.translate(0, -20);
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            rabbit.setColor(Color.MAGENTA);
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            rabbit.setColor(Color.BLUE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
