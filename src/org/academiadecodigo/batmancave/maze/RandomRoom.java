package org.academiadecodigo.batmancave.maze;

import java.util.ArrayList;
import java.util.List;

public class RandomRoom {

    public static Directions randomRoom(boolean[] arr) {

        List helper = new ArrayList();

        for(int i = 0; i < arr.length; i++) {
            if(arr[i]) {
                helper.add(Directions.values()[i]);
            }
        }

        if(helper.size() == 0) {
            return null;
        } else {
            return (Directions) helper.get((int)Math.floor(Math.random() * helper.size()));
        }
    }

}
