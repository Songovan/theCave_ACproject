package org.academiadecodigo.batmancave.gameobjects.enemies;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Player.PlayerOne;
import org.academiadecodigo.batmancave.Player.PlayerTwo;
import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.*;

import java.util.Random;

public class Ghost extends Enemy {

    //properties
    protected Position pos;
    private MovementDetector movementDetector;
    private MazeGfx mazeGfx;
    private Directions currentDirection;

    private boolean playerDetected = false;     //irá no move verificar se tem algum player na range - se tiver, o estado de move altera



    public Ghost(int player) {
        initGhostForPlayer(player);
    }

    public void initGhostForPlayer(int player) {

        int quadrant = 0;

        if( player == 2) {
            quadrant = 1;
        }

        int[] tempPos = randomPos(quadrant);

        pos = new Position(tempPos[0], tempPos[1]);
        currentDirection = Directions.RIGHT;
    }

    public Position getPos(){
        return pos;
    }



    //move method
    public void move(GhostSelector ghostSelector){
            boolean[] moveVerifier = {false, false, false, false};  //0 - UP, 1 - RIGHT, 2 - LEFT, 3 - DOWN
            if(playerDetected == false){                                   //Se o player não estiver detetado, continuará randomly. No fim do move, irá ver se o Player está no range e alterar ou não o boolean
                //movementDetector.checkMove(Directions.UP, this) //checkmove: vê a posição atual(row,col)
                //pos.changePosition(); //Para mudar a posição atual

                moveVerifier[0] = movementDetector.checkMove(Directions.UP,this);
                moveVerifier[1] = movementDetector.checkMove(Directions.RIGHT, this);
                moveVerifier[3] = movementDetector.checkMove(Directions.LEFT,this);
                moveVerifier[2] = movementDetector.checkMove(Directions.DOWN, this);

                int countPossibleMoves = 0;

                for (boolean dir:
                     moveVerifier) {
                    if(dir) {
                        countPossibleMoves++;
                    }
                }

                Directions move;

                if (movementDetector.checkMove(currentDirection, this)) {

                    if(countPossibleMoves > 2) {
                        moveVerifier[currentDirection.getOppositeDirection()] = false;  // If there are more than 2 moves can't got backwards.
                        move = RandomRoom.randomRoom(moveVerifier);                     // Chose random move except back
                        currentDirection = move;                                        // set new current direction
                    } else {
                        move = currentDirection;                                        // keep current direction
                    }

                } else {
                    move = RandomRoom.randomRoom(moveVerifier);
                    currentDirection = move;
                }

                switch(move){
                    case UP:
                        pos.changePosition(0,-1);
                        mazeGfx.moveGhost(0, -1, ghostSelector);
                        break;
                    case RIGHT:
                        pos.changePosition(1,0);
                        mazeGfx.moveGhost(1,0, ghostSelector);
                        break;
                    case LEFT:
                        pos.changePosition(-1,0);
                        mazeGfx.moveGhost(-1,0, ghostSelector);
                        break;
                    case DOWN:
                        pos.changePosition(0,1);
                        mazeGfx.moveGhost(0,1, ghostSelector);
                        break;
                    default:
                        break;
                }

            }
    }

    private int[] randomPos (int quadrant) {

        int col = (int)(Math.random()*(41 / 2) + quadrant * 41 / 2);

        int row = (int)(Math.random()*(31 / 2) + quadrant * 31 / 2);

        int[] pos = {col, row};

        System.out.println(col + ", " + row);

        if(col%2 != 0 && col%2 != 0) {
            return pos;
        } else {
            return randomPos(quadrant);
        }
    }

    public void setMazeGfx(MazeGfx mazeGfx) {
        this.mazeGfx = mazeGfx;
    }

    public void setMovementDetector(MovementDetector movementDetector) {
        this.movementDetector = movementDetector;
    }

    //hit method
    @Override
    public void hit (Player player) {
        //player.die();
        getMessage();
    }

    //getMessage method
    @Override
    public void getMessage() {
        System.out.println("*UUUUUUUUUUUHH*");
    }
}
