package org.academiadecodigo.batmancave.maze;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;

public class MovementDetector {

    private Maze maze;
    private Player player;
    private Ghost ghost;

    public MovementDetector(Maze maze, Player player, Ghost ghost) {
        this.maze = maze;
        this.player = player;
        this.ghost = ghost;
    }

    public boolean checkMove(Directions direction) {

        int currentCol = player.getPos().getCol();
        int currentRow = player.getPos().getRow();



        switch (direction) {
            case UP:
                //if (maze.getLayout()[currentCol-1][currentRow].getType() == CellType.ROOM || maze.getLayout()[currentCol-1][currentRow].getType() == CellType.ROOM ) {
                  //  return false;
                //} else
                if (maze.getLayout()[currentCol][currentRow - 1].getType() == CellType.ROOM) {
                    return true;
                } else {
                    return false;
                }
            case RIGHT:
                if (maze.getLayout()[currentCol + 1][currentRow].getType() == CellType.ROOM) {
                    return true;
                } else {
                    return false;
                }
            case LEFT:
                if (maze.getLayout()[currentCol - 1][currentRow].getType() == CellType.ROOM) {
                    return true;
                } else {
                    return false;
                }
            case DOWN:
                if (maze.getLayout()[currentCol][currentRow + 1].getType() == CellType.ROOM) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }


    }



}
