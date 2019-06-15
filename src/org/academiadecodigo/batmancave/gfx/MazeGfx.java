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

        playerOne = new Picture( cellSize + PADDING, cellSize , "Player/player 1 30x30.png");

        playerTwo = new Picture( (mazeLayout.length-2) * cellSize + PADDING, (mazeLayout[0].length - 2) * cellSize, "Player/player 2 30x30.png");


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
                    cellTexture = new Picture(col*cellSize+PADDING, row*cellSize+PADDING, "bWall_stone30.png");
                } else if (mazeLayout[col][row+1].getType() == CellType.ROOM) {
                    // Wall with room below
                    cellTexture = new Picture(col*cellSize+PADDING, row*cellSize+PADDING, "bWall_stone30.png");
                } else {
                    // regular wall
                    cellTexture = new Picture(col*cellSize+PADDING, row*cellSize+PADDING, "mWall_stone30.png");
                }
                break;
            case ROOM:
                if(col == 21 && row == 15) {
                    cellTexture = new Picture(col*cellSize+PADDING, row*cellSize+PADDING, "room_flag30.png");
                } else {
                    cellTexture = new Picture(col*cellSize+PADDING, row*cellSize+PADDING, "room_stone30.png");
                }
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


    }

    private void drawPlayer() {

        playerOne.draw();

        playerTwo.draw();

    }

    private void drawGhost() {

        int[] pos = {31,15}; //randomPos();

        ghost = new Picture(pos[0]*cellSize+5, pos[1]*cellSize+5, "death.png");

        ghost.grow(-5,-5);

        ghost.draw();

    }

    public void movePlayerOne (int col, int row) {

        playerOne.translate((double)(col*cellSize),(double)(row * cellSize));
        playerOne.delete();
        if(players[0].getHasFlag()) {
            playerOne = new Picture(playerOne.getX(), playerOne.getY(), "Player/player 1 30x30 super.png" );
            mazeLayout[21][15].getCellGfx().delete();
            mazeLayout[21][15].setCellGfx(new Picture(21*cellSize+PADDING, 15*cellSize+PADDING, "room_stone30.png"));
            mazeLayout[21][15].getCellGfx().draw();
        } else {
            playerOne = new Picture(playerOne.getX(), playerOne.getY(), "Player/player 1 30x30.png" );
        }
        drawMaze();
        //drawFlag();
        playerOne.draw();
    }

    public void movePlayerTwo (int col, int row) {

        playerTwo.translate((double)(col*cellSize),(double)(row * cellSize));
        playerTwo.delete();
        if(players[1].getHasFlag()) {
            playerTwo = new Picture( playerTwo.getX(), playerTwo.getY(), "Player/player 2 30x30 super.png");
            mazeLayout[21][15].getCellGfx().delete();
            mazeLayout[21][15].setCellGfx(new Picture(21*cellSize+PADDING, 15*cellSize+PADDING, "room_stone30.png"));
            mazeLayout[21][15].getCellGfx().draw();
        } else {
            playerTwo = new Picture( playerTwo.getX(), playerTwo.getY(), "Player/player 2 30x30.png");
        }
        drawMaze();
        //drawFlag();
        playerTwo.draw();
    }

    public void moveGhost (int col, int row) {
        ghost.translate((double) (col*cellSize), (double) (row * cellSize));
        ghost.delete();
        drawMaze();
        ghost.draw();
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
        window.delete();
        playerOne.delete();
        playerTwo.delete();

        for (int i = 0; i < mazeLayout.length; i++) {
            for (int j = 0; j < mazeLayout[0].length; j++) {

                mazeLayout[i][j].setCellGfx(null);

            }
        }

        init();
    }
}
