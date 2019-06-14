package org.academiadecodigo.batmancave.gameobjects.enemies;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Player.PlayerOne;
import org.academiadecodigo.batmancave.Player.PlayerTwo;
import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Directions;
import org.academiadecodigo.batmancave.maze.Excavator;
import org.academiadecodigo.batmancave.maze.MovementDetector;
import org.academiadecodigo.batmancave.maze.RandomRoom;

import java.util.Random;

public class Ghost extends Enemy {

    //properties
    private int ghostLevel;
    private int speed = 1;
    protected Position pos;
    private MovementDetector movementDetector;
    private MazeGfx mazeGfx;

    private boolean playerDetected = false;     //irá no move verificar se tem algum player na range - se tiver, o estado de move altera



    public Ghost(int col, int row) {
        pos = new Position(col, row);
        ghostLevel = 1;
    }

    public Position getPos(){
        return pos;
    }



    //move method
    public void move(){
            boolean[] moveVerifier = {false, false, false, false};  //0 - UP, 1 - RIGHT, 2 - LEFT, 3 - DOWN
            if(playerDetected == false){                                   //Se o player não estiver detetado, continuará randomly. No fim do move, irá ver se o Player está no range e alterar ou não o boolean
                //movementDetector.checkMove(Directions.UP, this) //checkmove: vê a posição atual(row,col)
                //pos.changePosition(); //Para mudar a posição atual

                moveVerifier[0] = movementDetector.checkMove(Directions.UP,this);
                moveVerifier[1] = movementDetector.checkMove(Directions.RIGHT, this);
                moveVerifier[3] = movementDetector.checkMove(Directions.LEFT,this);
                moveVerifier[2] = movementDetector.checkMove(Directions.DOWN, this);

                Directions move = RandomRoom.randomRoom(moveVerifier);

                /*
                int[] trueMoves = new int[4];
                for(int i = 0; i < moveVerifier.length; i++){
                    if(moveVerifier[i]){
                        trueMoves[i] = i;
                    }
                }                                          //now we have the index of the possible moves

                Random generator = new Random();
                int moveIndex = generator.nextInt(trueMoves.length);


                 */
                switch(move){
                    case UP:
                        pos.changePosition(0,-1);
                        System.out.println("col :" + pos.getCol() + " row :" + pos.getRow());
                        mazeGfx.moveGhost(0, -1);
                        break;
                    case RIGHT:
                        pos.changePosition(1,0);
                        System.out.println("col :" + pos.getCol() + " row :" + pos.getRow());
                        mazeGfx.moveGhost(1,0);
                        break;
                    case LEFT:
                        pos.changePosition(-1,0);
                        System.out.println("col :" + pos.getCol() + " row :" + pos.getRow());
                        mazeGfx.moveGhost(-1,0);
                        break;
                    case DOWN:
                        pos.changePosition(0,1);
                        System.out.println("col :" + pos.getCol() + " row :" + pos.getRow());
                        mazeGfx.moveGhost(0,1);
                        break;
                    default:
                        break;
                }

            }
    } /* JÁ ESTÁ: Movimentar-se para uma posição random e que seja permitida
         FALTA: Fazer o supra, apenas se o Player não estiver na Range - tratar da DETEÇÃO DO PLAYER
                    Ao DETETAR O PLAYER, fazer move Seguindo o Player*/

    private class PlayerDetector {
        private PlayerOne player1;
        private PlayerTwo player2;
        private Ghost ghost;
        private boolean detected;
        private MovementDetector movementDetector;

    /*public boolean isDetected(){
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
    }*/
    }

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
