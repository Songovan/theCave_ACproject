package org.academiadecodigo.batmancave;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Menu implements KeyboardHandler {

    private Game game;
    private boolean isGameStart;
    private Picture bg;
    private Picture start;
    private Picture startPressed;
    private Picture quit;
    private Picture quitPressed;
    private Picture title;
    private int picCorrection;


    public Menu(){
        picCorrection = 100;
        bg = new Picture(10,10,"Menu_back.png");
        bg.grow(0,picCorrection);
        bg.translate(0,100);
        bg.draw();
        //title = new Picture(288, 120, "");
        start = new Picture(288, 300 + picCorrection, "Menu/Start.png");
        quit = new Picture(288, 390 + picCorrection, "Menu/Quit.png");
        startPressed = new Picture(288, 300 + picCorrection, "Menu/Start pressed.png");
        quitPressed = new Picture(288, 390 + picCorrection, "Menu/Quit pressed.png");
        //title.draw();
        start.draw();
        quit.draw();
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
                start.delete();
                startPressed.draw();
            }
        } else if (e.getKey() == KeyboardEvent.KEY_ESC){
            if (!isGameStart) {
                quit.delete();
                quitPressed.draw();
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent e) {
            if (e.getKey() == KeyboardEvent.KEY_SPACE) {
                if (!isGameStart) {
                    startPressed.delete();
                    start.draw();
                    start.delete();
                    quit.delete();
                    bg.delete();
                    isGameStart = true;
                }

            } else if (e.getKey() == KeyboardEvent.KEY_ESC){
                System.exit(0);
            }
        }
}
