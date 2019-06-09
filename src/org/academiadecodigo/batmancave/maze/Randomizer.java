package org.academiadecodigo.batmancave.maze;

public class Randomizer {
    public static int getRandom(int max) {
        return getRandom(0, max);
    }


    public static int getRandom(int min, int max) {
        int range = max - min;

        int randomNum = (int)(Math.random()*range + min);

        return randomNum;
    }
}
