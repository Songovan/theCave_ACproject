package org.academiadecodigo.batmancave;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Menu implements KeyboardHandler {

    private Game game;
    private boolean isGameStart;
    private boolean buttonPress;
    private boolean buttonPress1;
    private boolean buttonReleased;
    private Picture bg;
    private Picture start;
    private Picture quit;
    private Picture button;
    private Picture button1;
    private Picture buttonPressed;
    private Picture buttonPressed1;
    private Picture pressSpace;
    private Picture pressEsc;
    private Picture title;



    public Menu(){
        bg = new Picture(10,10,"Menu/background.png");
        bg.draw();
        title = new Picture(376, 180, "Menu/tittle.png");
        start = new Picture(483, 550, "Menu/buttons/Start.png");
        quit = new Picture(483, 650, "Menu/buttons/Quit.png");
        button = new Picture(483, 550, "Menu/buttons/button.png");
        button1 = new Picture(483, 650, "Menu/buttons/button.png");
        buttonPressed = new Picture(483, 550, "Menu/buttons/button pressed.png");
        buttonPressed1 = new Picture(483, 650, "Menu/buttons/button pressed.png");
        pressSpace = new Picture(539, 565, "Menu/buttons/Press space.png");
        pressEsc = new Picture(539, 665, "Menu/buttons/Press esc.png");
        title.draw();
        button.draw();
        button1.draw();
        start.draw();
        quit.draw();
        keyboard();

        while (!isGameStart){
            if (!buttonPress) {
                start.delete();
                pressSpace.draw();
            }
            if (!buttonPress1) {
                quit.delete();
                pressEsc.draw();
            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e ) {
            }
            if (!buttonPress) {
                pressSpace.delete();
                start.draw();
            }
            if (!buttonPress1 && !buttonReleased) {
                pressEsc.delete();
                quit.draw();
            }
            try {
                Thread.sleep(800);
            } catch (InterruptedException e ) {
            }
        }
    }

    public void keyboard(){

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent startGamePressed = new KeyboardEvent();
        startGamePressed.setKey(KeyboardEvent.KEY_SPACE);
        startGamePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(startGamePressed);

        KeyboardEvent startGame = new KeyboardEvent();
        startGame.setKey(KeyboardEvent.KEY_SPACE);
        startGame.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(startGame);

        KeyboardEvent quitGamePressed = new KeyboardEvent();
        quitGamePressed.setKey(KeyboardEvent.KEY_ESC);
        quitGamePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(quitGamePressed);

        KeyboardEvent quitGame = new KeyboardEvent();
        quitGame.setKey(KeyboardEvent.KEY_ESC);
        quitGame.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(quitGame);
    }

    public boolean isGameStart() {
        return isGameStart;
    }

    @Override
    public void keyPressed(KeyboardEvent e) {
        if (e.getKey() == KeyboardEvent.KEY_SPACE) {
            if (!isGameStart) {
                buttonPress = true;
                pressSpace.delete();
                button.delete();
                start.delete();
                buttonPressed.draw();
                start.draw();
            }
        } else if (e.getKey() == KeyboardEvent.KEY_ESC){
            if (!isGameStart) {
                buttonPress1 = true;
                pressEsc.delete();
                button1.delete();
                quit.delete();
                buttonPressed1.draw();
                quit.draw();
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent e) {
        if (e.getKey() == KeyboardEvent.KEY_SPACE) {
            if (!isGameStart) {
                isGameStart = true;
                buttonReleased = true;
                title.delete();
                pressEsc.delete();
                button.delete();
                button1.delete();
                buttonPressed.delete();
                buttonPressed1.delete();
                start.delete();
                quit.delete();
                bg.delete();
            }
        } else if (e.getKey() == KeyboardEvent.KEY_ESC){
            System.exit(0);
        }
    }
}
