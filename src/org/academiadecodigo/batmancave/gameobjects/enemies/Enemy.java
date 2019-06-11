package org.academiadecodigo.batmancave.gameobjects.enemies;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.gameobjects.GameObject;

public abstract class Enemy extends GameObject {

    //properties
    private boolean dead;

    //isDead method
    public boolean isDead() {
        return dead;
    }

    //hit method
    public abstract void hit(Player player);

    //move method
    public abstract void move ();
}
