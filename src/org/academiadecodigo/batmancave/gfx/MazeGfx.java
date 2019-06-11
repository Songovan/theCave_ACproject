package org.academiadecodigo.batmancave.gfx;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.batmancave.maze.*;

public class MazeGfx {

    private Rectangle window;
    private Maze maze;
    private Cell[][] mazeLayout;
    private final int PADDING = 10;
    private int cellSize;
    private Picture player1;
    private Picture player2;
    private Picture ghost;
    private int viewRadius;

    public int getPlayerDelay() {
        return playerDelay;
    }

    private int playerDelay = 50;

    public MazeGfx(Maze maze) {
        this.maze = maze;
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

        player1 = new Picture( 5, cellSize + 5, "robin.png");

        player1.grow(-5,-5);

        player2 = new Picture( mazeLayout.length * cellSize - 5, mazeLayout[0].length+5, "robin.png");

        player2.grow(-5,-5);

        //DRAW MAZE AROUND PLAYER
        drawMaze();
        drawPlayer();


        //drawPlayer(0, cellSize);
        drawGhost();
    }



    private Rectangle assignCell(int col, int row) {

        CellType cell = mazeLayout[col][row].getType();

        Rectangle cellRectangle;

        switch (cell) {
            case WALL:
                cellRectangle = new Rectangle(col*cellSize + PADDING, row*cellSize + PADDING, cellSize, cellSize);
                if(col == 0 && row == 1 || col == mazeLayout.length - 1 && row == mazeLayout[0].length - 2) {
                    cellRectangle.setColor(Color.BLUE);
                } else {
                    cellRectangle.setColor(Color.DARK_GRAY);
                }
                //cellRectangle.fill();
                break;
            case ROOM:
                cellRectangle = new Rectangle(col*cellSize + PADDING, row*cellSize + PADDING, cellSize, cellSize);
                cellRectangle.setColor(Color.LIGHT_GRAY);
                //cellRectangle.fill();
                break;
            default:
                cellRectangle = null;
                break;
        }

        return cellRectangle;

    }


    private void drawMaze() {

        int distance;

        for (int i = 0; i < mazeLayout.length; i++) {
            for (int j = 0; j < mazeLayout[0].length; j++) {

                distance = (int)(Math.sqrt((player1.getX()/cellSize - i)*(player1.getX()/cellSize - i) + (player1.getY()/cellSize - j)*(player1.getY()/cellSize - j)));

                distance = (int)(Math.sqrt((player2.getX()/cellSize - i)*(player2.getX()/cellSize - i) + (player2.getY()/cellSize - j)*(player2.getY()/cellSize - j)));

                if(distance < viewRadius) {
                    mazeLayout[i][j].getCellGfx().fill();
                } else {
                    mazeLayout[i][j].getCellGfx().delete();
                }
            }
        }

    }


    private void drawPlayer() {



        player1.draw();

        player2.draw();

    }

    private void drawGhost() {

        int[] pos = randomPos();

        ghost = new Picture(pos[0]*cellSize+5, pos[1]*cellSize+5, "death.png");

        ghost.grow(-5,-5);

        ghost.draw();

    }

    public void movePlayer (int col, int row) {

        player1.translate((double)(col*cellSize),(double)(row * cellSize));
        player1.delete();
        drawMaze();
        player1.draw();
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



}
