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


        System.out.println(player.getHasFlag());

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

    public boolean checkFlag(Position pos) {

        System.out.println("flag at: " + flag.getPos().getCol() + ", " + flag.getPos().getRow());
        System.out.println("player at: " + pos.getCol() + ", " + pos.getRow());

        //System.out.println("Flag at: " + flag.getPos().getCol() + ", " + flag.getPos().getRow());
        //System.out.println("Player at: " + pos.getCol() + ", " + pos.getRow());

        if(pos.getCol() == flag.getPos().getCol() && pos.getRow() == flag.getPos().getRow()) {
            flag.setPos(pos);
            return true;
        } else {
            return false;
        }

    }

    public boolean roundEnd(Player[] players) {

        if(players[0].getHasFlag() &&
            players[0].getPos().getCol() == 1 &&
            players[0].getPos().getRow() == 1) {
            // Player 1 wins the round
            return true;
        }

        if(players[1].getHasFlag() &&
            players[1].getPos().getCol() == (maze.getLayout().length - 2) &&
            players[1].getPos().getRow() == (maze.getLayout()[0].length - 2)) {
            // Player 2 wins the round
            return true;
        }

        return false;

    }



}
