package org.academiadecodigo.batmancave.gameobjects.enemies;

import org.academiadecodigo.batmancave.Game;
import org.academiadecodigo.batmancave.Player;

public class Ghost extends Enemy {

    //properties
    private int ghostLevel;
    private int speed = 1;

    //behaviour method
    public void behaviour () {
        ghostLevel = Game.getGameLevel();
        if (ghostLevel == 1) {

        } else if (ghostLevel == 2) {

        }else if (ghostLevel == 3) {

        } else {

        }
    }

    //move method
    public void move () {

    }

    //hit method
    @Override
    public void hit (Player player) {
        player.die();
        getMessage();
    }

    //getMessage method
    @Override
    public void getMessage() {
        System.out.println("*UUUUUUUUUUUHH*");
    }
}
