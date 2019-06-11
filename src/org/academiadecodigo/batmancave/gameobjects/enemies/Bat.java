package org.academiadecodigo.batmancave.gameobjects.enemies;

import org.academiadecodigo.batmancave.Player.Player;

public class Bat extends Enemy implements Defeatable{

    //properties
    private int damage = 1;
    private int speed = 2;


    //hit method
    public void hit (Player player) {
        player.decreaseHp(damage);
        getMessage();
    }

    //move method
    public void move () {

    }

    @Override
    public void getMessage() {
        System.out.println("*GWAAAAH*");
    }
}
