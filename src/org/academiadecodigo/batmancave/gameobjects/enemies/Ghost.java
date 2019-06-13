package org.academiadecodigo.batmancave.gameobjects.enemies;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Directions;
import org.academiadecodigo.batmancave.maze.Excavator;
import org.academiadecodigo.batmancave.maze.MovementDetector;

import java.util.Random;

public class Ghost extends Enemy {

    //properties
    private int ghostLevel;
    private int speed = 1;
    protected Position pos;
    private MovementDetector movementDetector;
    private MazeGfx mazeGfx;

    private boolean playerDetected;     //irá no move verificar se tem algum player na range - se tiver, o estado de move altera



    public Ghost() {
        ghostLevel = 1;
    }

    public Position getPos(){
        return pos;
    }



    //move method
    public void move(){
        boolean[] moveVerifier = {false, false, false, false};  //0 - UP, 1 - RIGHT, 2 - LEFT, 3 - DOWN
        if(!playerDetected){                                   //Se o player não estiver detetado, continuará randomly. No fim do move, irá ver se o Player está no range e alterar ou não o boolean
                                                              //movementDetector.checkMove(Directions.UP, this) //checkmove: vê a posição atual(row,col)
                                                             //pos.changePosition(); //Para mudar a posição atual

            moveVerifier[0] = movementDetector.checkMove(Directions.UP,this);
            moveVerifier[1] = movementDetector.checkMove(Directions.RIGHT, this);
            moveVerifier[2] = movementDetector.checkMove(Directions.LEFT,this);
            moveVerifier[3] = movementDetector.checkMove(Directions.DOWN, this);

            int[] trueMoves = new int[4];
            for(int i = 0; i < moveVerifier.length; i++){
                if(moveVerifier[i]){
                    trueMoves[i] = i;
                }
            }                                          //now we have the index of the possible moves

            Random generator = new Random();
            int moveIndex = generator.nextInt(trueMoves.length);

            switch(moveIndex){
                case 0:
                    pos.changePosition(0,-1);
                    mazeGfx.moveGhost(0, -1);
                    break;
                case 1:
                    pos.changePosition(1,0);
                    mazeGfx.moveGhost(1,0);
                    break;
                case 2:
                    pos.changePosition(-1,0);
                    mazeGfx.moveGhost(-1,0);
                    break;
                default:
                    pos.changePosition(0,1);
                    mazeGfx.moveGhost(0,1);
                    break;
            }

        }
    } /* JÁ ESTÁ: Movimentar-se para uma posição random e que seja permitida
         FALTA: Fazer o supra, apenas se o Player não estiver na Range - tratar da DETEÇÃO DO PLAYER
                    Ao DETETAR O PLAYER, fazer move Seguindo o Player*/


   /* public void move () {

        switch (ghostLevel) {
            case 1:
                // Level 1 behaviour: random movement





                break;
            case 2:
                // Level 2 behaviour: follows if player is seen in a straight line

                break;
            case 3:
                // follows player after first encounter

                break;
            default:
                break;
        }
    }*/



    public void setGhostLevel(int ghostLevel) {
        this.ghostLevel = ghostLevel;
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
        player.die();
        getMessage();
    }

    //getMessage method
    @Override
    public void getMessage() {
        System.out.println("*UUUUUUUUUUUHH*");
    }
}
