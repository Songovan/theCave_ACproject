package org.academiadecodigo.batmancave.gfx;

import javafx.scene.shape.Circle;
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
    private Picture player;
    private Picture ghost;

    public int getPlayerDelay() {
        return playerDelay;
    }

    private int playerDelay = 100;

    public MazeGfx(Maze maze) {
        this.maze = maze;
        cellSize = 20;
        mazeLayout = maze.getLayout();
        window = new Rectangle(PADDING, PADDING, mazeLayout.length * cellSize, mazeLayout[0].length * cellSize);
    }

    public void init() {
        window.draw();

        for (int i = 0; i < mazeLayout.length; i++) {
            for (int j = 0; j < mazeLayout[0].length; j++) {

                drawCell(i,j);

            }
        }

        drawPlayer(0, cellSize);
        drawGhost();
    }



    private void drawCell(int col, int row) {

        CellType cell = mazeLayout[col][row].getType();

        Rectangle cellRectangle;

        switch (cell) {
            case WALL:
                cellRectangle = new Rectangle(col*cellSize + PADDING, row*cellSize + PADDING, cellSize, cellSize);
                if(col == 0 && row == 1 || col == mazeLayout.length - 1 && row == mazeLayout[0].length - 2) {
                    cellRectangle.setColor(Color.BLUE);
                } else {
                    cellRectangle.setColor(Color.BLACK);
                }
                cellRectangle.fill();
                break;
            case ROOM:
                cellRectangle = new Rectangle(col*cellSize + PADDING, row*cellSize + PADDING, cellSize, cellSize);
                cellRectangle.setColor(Color.GRAY);
                cellRectangle.fill();
                break;
            default:
                break;
        }


    }

    private void drawPlayer(int col, int row) {

        player = new Picture(col +5, row+5, "robin.png");

        player.grow(-5,-5);

        player.draw();

    }

    private void drawGhost() {

        int[] pos = randomPos();

        ghost = new Picture(pos[0]*cellSize+5, pos[1]*cellSize+5, "death.png");

        ghost.grow(-5,-5);

        ghost.draw();

    }

    public void movePlayer (int col, int row) {

        player.translate((double)(col*cellSize),(double)(row * cellSize));
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
