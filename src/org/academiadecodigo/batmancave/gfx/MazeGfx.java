package org.academiadecodigo.batmancave.gfx;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.batmancave.maze.*;

public class MazeGfx {

    private Rectangle window;
    private Cell[][] mazeLayout;
    private final int PADDING = 10;
    private int cellSize;
    private Player[] players;
    private Picture playerOne;
    private Picture playerTwo;
    private Picture ghost;
    private Picture flag;
    private int viewRadius;

    public MazeGfx(Maze maze) {
        cellSize = 20;
        mazeLayout = maze.getLayout();
        window = new Rectangle(PADDING, PADDING, mazeLayout.length * cellSize, mazeLayout[0].length * cellSize);
        viewRadius = 5;
    }

    public void init() {
        window.setColor(Color.BLACK);
        window.draw();
        window.fill();

        for (int i = 0; i < mazeLayout.length; i++) {
            for (int j = 0; j < mazeLayout[0].length; j++) {

                mazeLayout[i][j].setCellGfx(assignCell(i,j));

            }
        }

        playerOne = new Picture( 5, cellSize , "robin.png");

        playerOne.grow(-5,-5);

        playerTwo = new Picture( (mazeLayout.length-1) * cellSize + 5, (mazeLayout[0].length - 2) * cellSize, "robin.png");

        playerTwo.grow(-5,-5);

        flag = new Picture(Math.ceil(21*cellSize + PADDING), Math.ceil(15) * cellSize + PADDING, "flag.png");

        //DRAW MAZE AROUND PLAYER
        drawMaze();
        drawPlayer();


        //drawPlayer(0, cellSize);
        drawGhost();
    }



    private Picture assignCell(int col, int row) {

        CellType cell = mazeLayout[col][row].getType();

        Picture cellTexture;

        switch (cell) {
            case WALL:

                if(row == mazeLayout[0].length - 1) {
                    // Bottom line
                    cellTexture = new Picture(col*cellSize+PADDING, row*cellSize+PADDING, "bottom_wall.png");
                } else if (mazeLayout[col][row+1].getType() == CellType.ROOM) {
                    // Wall with room below
                    cellTexture = new Picture(col*cellSize+PADDING, row*cellSize+PADDING, "bottom_wall.png");
                } else {
                    // regular wall
                    cellTexture = new Picture(col*cellSize+PADDING, row*cellSize+PADDING, "wall.png");
                }
                break;
            case ROOM:
                cellTexture = new Picture(col*cellSize+PADDING, row*cellSize+PADDING, "room.png");
                break;
            default:
                cellTexture = null;
                break;
        }

        return cellTexture;

    }


    private void drawMaze() {

        int distanceOne;
        int distanceTwo;

        for (int i = 0; i < mazeLayout.length; i++) {
            for (int j = 0; j < mazeLayout[0].length; j++) {

                distanceOne = (int)(Math.sqrt((playerOne.getX()/cellSize - i)*(playerOne.getX()/cellSize - i) + (playerOne.getY()/cellSize - j)*(playerOne.getY()/cellSize - j)));

                distanceTwo = (int)(Math.sqrt((playerTwo.getX()/cellSize - i)*(playerTwo.getX()/cellSize - i) + (playerTwo.getY()/cellSize - j)*(playerTwo.getY()/cellSize - j)));

                if(distanceOne < viewRadius || distanceTwo < viewRadius) {
                    mazeLayout[i][j].getCellGfx().draw();
                } else {
                    mazeLayout[i][j].getCellGfx().delete();
                }
            }
        }

        drawFlag();
    }

    private void drawFlag() {

        int distanceOne;
        int distanceTwo;

        distanceOne = (int)(Math.sqrt((playerOne.getX() - flag.getX())*(playerOne.getX() - flag.getX()) + (playerOne.getY() - flag.getY())*(playerOne.getY() - flag.getY()))/cellSize);

        distanceTwo = (int)(Math.sqrt((playerTwo.getX() - flag.getX())*(playerTwo.getX() - flag.getX()) + (playerTwo.getY() - flag.getY())*(playerTwo.getY() - flag.getY()))/cellSize);



        if(distanceOne < viewRadius || distanceTwo < viewRadius) {
            if(!players[0].getHasFlag() && !players[1].getHasFlag()) {
                flag.draw();
            } else {
                flag.delete();
            }
        } else {
            flag.delete();
        }
    }


    private void drawPlayer() {



        playerOne.draw();

        playerTwo.draw();

    }

    private void drawGhost() {

        int[] pos = randomPos();

        ghost = new Picture(pos[0]*cellSize+5, pos[1]*cellSize+5, "death.png");

        ghost.grow(-5,-5);

        ghost.draw();

    }

    public void movePlayerOne (int col, int row) {

        playerOne.translate((double)(col*cellSize),(double)(row * cellSize));
        playerOne.delete();
        drawMaze();
        playerOne.draw();
    }

    public void movePlayerTwo (int col, int row) {

        playerTwo.translate((double)(col*cellSize),(double)(row * cellSize));
        playerTwo.delete();
        drawMaze();
        playerTwo.draw();
    }


    private int[] randomPos () {

        int col = (int)(Math.random()*(mazeLayout.length / 2) + mazeLayout.length / 2);

        int row = (int)(Math.random()*(mazeLayout[0].length / 2) + mazeLayout[0].length / 2);

        int[] pos = {col, row};

        if(mazeLayout[pos[0]][pos[1]].getType() == CellType.ROOM) {
            return pos;
        } else {
            return randomPos();
        }
    }


    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void restartMazeGfx() {
        playerOne.delete();
        playerOne = new Picture( 5, cellSize , "robin.png");
        playerOne.grow(-5,-5);

        playerTwo.delete();
        playerTwo = new Picture( (mazeLayout.length-1) * cellSize + 5, (mazeLayout[0].length - 2) * cellSize, "robin.png");
        playerTwo.grow(-5,-5);

        flag = new Picture(Math.ceil(21*cellSize + PADDING), Math.ceil(15) * cellSize + PADDING, "flag.png");

        drawMaze();
        playerOne.draw();
        playerTwo.draw();
    }
}
