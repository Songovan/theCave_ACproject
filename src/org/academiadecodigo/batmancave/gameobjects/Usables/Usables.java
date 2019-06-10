package org.academiadecodigo.batmancave.gameobjects.Usables;

import org.academiadecodigo.batmancave.gameobjects.GameObject;

public abstract class Usables extends GameObject {

    //properties
    private boolean possession;

    //is Possessed method
    public boolean isPossessed() {
        return possession;
    }
}
