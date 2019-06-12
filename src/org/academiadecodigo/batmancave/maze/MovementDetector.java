package org.academiadecodigo.batmancave.maze;

import org.academiadecodigo.batmancave.Player.*;
import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;
import org.academiadecodigo.batmancave.gameobjects.Usables.Flag;

public class MovementDetector {

    private Maze maze;
    private Flag flag;
    private Ghost ghost;

    public MovementDetector(Maze maze, Flag flag) {
        this.maze = maze;
        this.flag = flag;
    }

    public boolean checkMove(Directions direction, Player player) {

        int currentCol = player.getPos().getCol();
        int currentRow = player.getPos().getRow();


        switch (direction) {
            case UP:
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

    public Flag checkFlag(Position pos) {

        System.out.println("Flag at: " + flag.getPos().getCol() + ", " + flag.getPos().getRow());
        System.out.println("Player at: " + pos.getCol() + ", " + pos.getRow());

        if(pos.getCol() == flag.getPos().getCol() && pos.getRow() == flag.getPos().getRow()) {
            return flag;
        } else {
            return null;
        }

    }



}
