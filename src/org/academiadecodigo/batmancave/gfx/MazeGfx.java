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

    public void drawPlayer(int col, int row) {

        player = new Picture(col +5, row+5, "robin.png");

        player.grow(-5,-5);

        player.draw();


    }

    public void movePlayer (int col, int row) {
        player.translate(col*cellSize,row * cellSize);
    }



}
