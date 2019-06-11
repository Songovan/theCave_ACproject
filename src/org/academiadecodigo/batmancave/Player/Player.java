package org.academiadecodigo.batmancave.Player;

import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.batmancave.gameobjects.Usables.Flashlight;
import org.academiadecodigo.batmancave.gameobjects.Usables.SpeedBooster;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Directions;
import org.academiadecodigo.batmancave.maze.MovementDetector;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public abstract class Player implements KeyboardHandler {

    //properties
    private int minHp = 5;
    private int hp = minHp;
    private int maxHp = 10;
    private boolean dead;
    protected Position pos;
    private int minSpeed = 4;
    private int speed = minSpeed;
    private int maxSpeed = 10;
    private Flashlight flashlight;
    private SpeedBooster speedBooster = new SpeedBooster();
    protected MovementDetector movementDetector;
    protected MazeGfx mazeGfx;

    public Player(int col, int row) {
        pos = new Position(col, row);
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

    //decreaseSpeed method
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