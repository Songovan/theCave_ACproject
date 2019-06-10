package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.gameobjects.Usables.Flashlight;
import org.academiadecodigo.batmancave.gameobjects.Usables.SpeedBooster;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Directions;
import org.academiadecodigo.batmancave.maze.MovementDetector;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Player implements KeyboardHandler {

    //properties
    private int minHp = 5;
    private int hp = minHp;
    private int maxHp = 10;
    private boolean dead;
    private Position pos;
    private int minSpeed = 4;
    private int speed = minSpeed;
    private int maxSpeed = 10;
    private Flashlight flashlight;
    private SpeedBooster speedBooster = new SpeedBooster();
    private MovementDetector movementDetector;
    private MazeGfx mazeGfx;

    public Player() {
        pos = new Position(0, 1);
    }

    //walk method
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
                if(movementDetector.checkMove(Directions.UP)) {
                    pos.changePosition(0,-1);
                    mazeGfx.movePlayer(0, -1);

                    // MOVE UP
                }
                break;
            case KeyboardEvent.KEY_D:
                if(movementDetector.checkMove(Directions.RIGHT)) {
                    pos.changePosition(1,0);
                    mazeGfx.movePlayer(1,0);
                    // MOVE RIGHT
                }
                break;
            case KeyboardEvent.KEY_S:
                if (movementDetector.checkMove(Directions.DOWN)) {
                    pos.changePosition(0,1);
                    mazeGfx.movePlayer(0,1);
                    // MOVE DOWN
                }
                break;
            case KeyboardEvent.KEY_A:
                if (movementDetector.checkMove(Directions.LEFT)) {
                    pos.changePosition(-1,0);
                    mazeGfx.movePlayer(-1,0);
                    // MOVE LEFT
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        int key = keyboardEvent.getKey();

        switch (key) {

        }

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


    //useFlash method
    public void useFlash(Flashlight flashlight) {
        if (flashlight.isPossessed()) {
            flashlight.lightUp();
        } else {
            System.out.println("You don't have a Flashlight!");
        }
    }

    //useSpeed method
    public void useSpeed(SpeedBooster speedBooster) {
        if (speedBooster.getCharges() > 0) {
            speedBooster.boostUp();
            increaseSpeed(speedBooster.getSpeed());
            //walk a few steps
            //setSpeed(minSpeed);
        } else {
            System.out.println("You're out of Speed Boosters!");
        }
    }

    //pickFlash
    public void pickFlash () {
        if (flashlight == null) {
            flashlight = new Flashlight();
            flashlight.getMessage();
        } else {
            System.out.println("You already have a Flashlight!");
        }
    }

    //getHp method
    public int getHp() {
        return hp;
    }

    //setHp method
    public void decreaseHp(int extra) {
        hp = hp - extra;
        if (hp <= minHp) {
            dead = true;
        }
    }

    //increaseHp method
    public void increaseHp(int extra) {
        hp = hp + extra;
        if (hp >= maxHp) {
            hp = maxHp;
        }
    }

    //isDead method
    public boolean isDead() {
        return dead;
    }

    //die method
    public void die() {
        dead = true;
    }

    //getSpeed method
    public int getSpeed() {
        return speed;
    }

    //setSpeed method
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //increaseSpeed method
    public void increaseSpeed(int extra) {
        speed = speed + extra;
        if (speed >= maxSpeed) {
            speed = maxSpeed;
        }
    }

    //decreaseSpeed methpd
    public void decreaseSpeed(int extra) {
        speed = speed - extra;
        if (speed <= minSpeed) {
            speed = minSpeed;
        }
    }

    //hasFlashlight method
    public boolean hasFlashlight() {
        return flashlight.isPossessed();
    }

    //pickUpBooster method
    public void pickUpBooster() {
        speedBooster.increaseCharges();
    }
}