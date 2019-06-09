package org.academiadecodigo.batmancave.gameobjects;

public abstract class GameObject {

    //properties
    protected boolean possession;

    //is Possessed method
    public boolean isPossessed() {
        return possession;
    }

    //getMessage
    public abstract void getMessage ();
}
