package org.academiadecodigo.batmancave.gfx;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.PlayersSelector;
import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.batmancave.gameobjects.Usables.Flag;
import org.academiadecodigo.batmancave.gameobjects.enemies.GhostSelector;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.batmancave.maze.*;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;

public class MazeGfx {

    private Rectangle window;
    private Cell[][] mazeLayout;
    private final int PADDING = 10;
    private int cellSize;
    private Player[] players;
    private Picture playerOne;
    private Picture playerTwo;
    private Picture ghost1;
    private Picture ghost2;
    private Flag flag;
    private Position flagStart;
    private int viewRadius;
    private boolean hasGhosts;

    public MazeGfx(Maze maze) {
        cellSize = 30;
        mazeLayout = maze.getLayout();
        window = new Rectangle(PADDING, PADDING, mazeLayout.length * cellSize, mazeLayout[0].length * cellSize);
        viewRadius = 5;
    }

    public void init() {
        window.setColor(Color.BLACK);
        window.draw();
        window.fill();

        setFlagStart();

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
        //drawGhost();
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
                if(col == flagStart.getCol() && row == flagStart.getRow()) {
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

                if(hasGhosts) {
                    int distanceOneToGhost1 = (int)(Math.sqrt((playerOne.getX() - ghost1.getX())*(playerOne.getX() - ghost1.getX()) + (playerOne.getY() - ghost1.getY())*(playerOne.getY() - ghost1.getY()))/cellSize);
                    int distanceOneToGhost2 = (int)(Math.sqrt((playerOne.getX() - ghost2.getX())*(playerOne.getX() - ghost2.getX()) + (playerOne.getY() - ghost2.getY())*(playerOne.getY() - ghost2.getY()))/cellSize);
                    int distanceTwoToGhost1 = (int)(Math.sqrt((playerTwo.getX() - ghost1.getX())*(playerTwo.getX() - ghost1.getX()) + (playerTwo.getY() - ghost1.getY())*(playerTwo.getY() - ghost1.getY()))/cellSize);
                    int distanceTwoToGhost2 = (int)(Math.sqrt((playerTwo.getX() - ghost2.getX())*(playerTwo.getX() - ghost2.getX()) + (playerTwo.getY() - ghost2.getY())*(playerTwo.getY() - ghost2.getY()))/cellSize);

                    if (distanceOneToGhost1 < viewRadius || distanceTwoToGhost1 < viewRadius) {
                        ghost1.draw();
                    } else {
                        ghost1.delete();
                    }

                    if (distanceOneToGhost2 < viewRadius || distanceTwoToGhost2 < viewRadius) {
                        ghost2.draw();
                    } else {
                        ghost2.delete();
                    }
                }




            }
        }


    }

    private void drawPlayer() {

        playerOne.draw();

        playerTwo.draw();

    }

    public void drawGhost(Ghost[] ghosts) {

        ghost1 = new Picture(ghosts[0].getPos().getCol()*cellSize+PADDING, ghosts[0].getPos().getRow()*cellSize + PADDING, "ghost30.png");

        ghost2 = new Picture(ghosts[1].getPos().getCol()*cellSize+PADDING, ghosts[1].getPos().getRow()*cellSize+PADDING, "ghost30.png");

        hasGhosts = true;

    }

    public void movePlayerOne (int col, int row) {

        playerOne.translate((double)(col*cellSize),(double)(row * cellSize));
        playerOne.delete();
        if(players[0].getHasFlag()) {
            playerOne = new Picture(playerOne.getX(), playerOne.getY(), "Player/player 1 30x30 super.png" );
            mazeLayout[flagStart.getCol()][flagStart.getRow()].getCellGfx().delete();
            mazeLayout[flagStart.getCol()][flagStart.getRow()].setCellGfx(new Picture(flagStart.getCol()*cellSize+PADDING, flagStart.getRow()*cellSize+PADDING, "room_stone30.png"));
            mazeLayout[flagStart.getCol()][flagStart.getRow()].getCellGfx().draw();
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
            mazeLayout[flagStart.getCol()][flagStart.getRow()].getCellGfx().delete();
            mazeLayout[flagStart.getCol()][flagStart.getRow()].setCellGfx(new Picture(flagStart.getCol()*cellSize+PADDING, flagStart.getRow()*cellSize+PADDING, "room_stone30.png"));
            mazeLayout[flagStart.getCol()][flagStart.getRow()].getCellGfx().draw();
        } else {
            playerTwo = new Picture( playerTwo.getX(), playerTwo.getY(), "Player/player 2 30x30.png");
        }
        drawMaze();
        //drawFlag();
        playerTwo.draw();
    }

    public void moveGhost (int col, int row, GhostSelector ghost) {
        if(ghost == GhostSelector.ONE) {
            ghost1.translate((double) (col * cellSize), (double) (row * cellSize));
            //ghost1.delete();

        } else {
            ghost2.translate((double) (col * cellSize), (double) (row * cellSize));
            //ghost2.delete();

        }

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

    public void playerCaught(PlayersSelector playersSelector) {
        if (playersSelector == PlayersSelector.ONE) {
            playerOne.delete();
            playerOne = new Picture( cellSize + PADDING, cellSize , "Player/player 1 30x30.png");
        } else {
            playerTwo.delete();
            playerTwo = new Picture( (mazeLayout.length-2) * cellSize + PADDING, (mazeLayout[0].length - 2) * cellSize, "Player/player 2 30x30.png");
        }

        flag.setPos(flagStart);
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

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public void setFlagStart() {
        flagStart = flag.getPos();
    }


}
