package org.academiadecodigo.batmancave.gameobjects.Usables;

import org.academiadecodigo.batmancave.gameobjects.GameObject;

public class SpeedBooster{

    //properties
    private int charges;
    private int maxCharges = 4;
    private int speed = 2;

    //boostUp method
    public void boostUp() {
        decreaseCharges();
        System.out.println("Speed Up!");
    }

    //addCharges method
    public void increaseCharges() {
        if (charges < maxCharges) {
            charges++;
        } else {
            System.out.println("You can't have any more Boosters!");
        }
    }

    //decreaseCharges method
    public void decreaseCharges() {
        if (charges > 0) {
            charges--;
        } else {
            System.out.println("You can't have any more Boosters!");
        }
    }

    //getCharges method
    public int getCharges() {
        return charges;
    }

    //getSpeed method
    public int getSpeed() {
        return speed;
    }

    //getMessage method
    public void getMessage() {
        System.out.println("You picked up a Speed Booster Charge!");
    }
}
