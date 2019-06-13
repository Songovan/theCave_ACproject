package org.academiadecodigo.batmancave.gameobjects.Usables;

import org.academiadecodigo.batmancave.Player.PlayerOne;
import org.academiadecodigo.batmancave.Player.PlayerTwo;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;
import org.academiadecodigo.batmancave.maze.Directions;
import org.academiadecodigo.batmancave.maze.MovementDetector;

public class PlayerDetector {
    private PlayerOne player1;
    private PlayerTwo player2;
    private Ghost ghost;
    private boolean detected;
    private MovementDetector movementDetector;

    public boolean isDetected(){
        boolean[] notWall = new boolean[4];
        for(int i = 0; i < notWall.length; i++){
            switch(i){
                case 0:
                    notWall[0] = movementDetector.checkMove(Directions.UP,ghost);
                    break;
                case 1:
                    notWall[1] = movementDetector.checkMove(Directions.RIGHT,ghost);
                case 2:
                    notWall[2] = movementDetector.checkMove(Directions.LEFT,ghost);
                default:
                    notWall[3] = movementDetector.checkMove(Directions.DOWN,ghost);
            }
        }

        for(int i = 0; i < notWall.length; i++){
            if(notWall[i]){
                if(player1.) //se a col or row do player1/2 for == à col or row do ghost +3, return true
            }
        }
    }

}
