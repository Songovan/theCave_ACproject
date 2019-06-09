package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.gameobjects.Flashlight;
import org.academiadecodigo.batmancave.gameobjects.SpeedBooster;

public class Player {



    //properties
    private int hp = 5;
    private int minHp = hp;
    private int maxHp = 10;
    private boolean dead;
    private Position pos;
    private int speed = 4;
    private int minSpeed = speed;
    private int maxSpeed = 10;
    private Flashlight flashlight;
    private SpeedBooster speedBooster;

    //constructor
    public Player() {
    }

    //walk method
    public void walk() {

    }

    //useFlash method
    public void useFlash(Flashlight flashlight) {
        if (flashlight != null) {
            flashlight.lightUp();
        } else {
            System.out.println("You don't have a Flashlight!");
        }
    }

    //useSpeed method
    public void useSpeed(SpeedBooster speedBooster) {
        if (speedBooster != null) {
            speedBooster.boostUp();
        } else {
            System.out.println("You're out of Speed Boosters!");
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int extra) {
        hp = hp - extra;
        if (hp >= minHp) {
            hp = minHp;
        }
    }

    public void increaseHp (int extra){
        hp = hp + extra;
        if (hp >= maxHp) {
            hp = maxHp;
        }
    }

    public boolean isDead() {
        return dead;
    }

    public void die() {
        dead = true;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void increaseSpeed(int extra) {
        speed = speed + extra;
        if (speed >= maxSpeed) {
            speed = maxSpeed;
        }
    }

    public void decreaseSpeed (int extra) {
        speed = speed - extra;
        if (speed <= minSpeed) {
            speed = minSpeed;
        }
    }

    public boolean getSpeedBooster() {
        if (speedBooster != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getFlashlight() {
        if (flashlight != null) {
            return true;
        } else {
            return false;
        }
    }
}
