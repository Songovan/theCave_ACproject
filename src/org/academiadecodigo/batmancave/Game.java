package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Player.PlayerOne;
import org.academiadecodigo.batmancave.Player.PlayerTwo;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Maze;
import org.academiadecodigo.batmancave.maze.MovementDetector;
import org.academiadecodigo.batmancave.gameobjects.Usables.*;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;

public class Game {

    //Game
    private Menu menu;
    private Maze maze;
    private MazeGfx mazeGfx;
    private MovementDetector movementDetector;
    private PlayerOne playerOne;
    private PlayerTwo playerTwo;
    private Player[] players;
    private Ghost ghost;
    private Flag flag;
    private boolean roundEnd;
    private int[] points;

    //Audio
    private Sound sound = new Sound();
    private File mainTheme = new File("./resources/startSong.wav");
    private File boo = new File("./resources/gotcha.wav");
    private File escapeSong = new File("./resources/Danger.wav");
    private File powerUp = new File("./resources/Power.wav");
    private File hit = new File("./resources/moaning-woman.wav");


    //Constructor
    public Game() {
        maze = new Maze(41, 31);
        mazeGfx = new MazeGfx(maze);
        points = new int[]{0,0};
    }

    //Menu method
    public void menu(){

        try{
            menu = new Menu();
            sound.play(mainTheme);
            while (!menu.isGameStart()){
                Thread.sleep(500);
            }
            sound.stop();
            sound.play(escapeSong);
            init();
        } catch (InterruptedException e5){
            System.out.println("Interrupted Exception");
        }
    }

    //Init Method
    public void init() {

        try{
            maze.init();
            maze.generate();
            mazeGfx.init();

            flag = new Flag(21,15);
            playerOne = new PlayerOne(1,1);
            playerTwo = new PlayerTwo(maze.getLayout().length-2, maze.getLayout()[0].length-2);
            players = new Player[2];
            players[0] = playerOne;
            players[1] = playerTwo;
            ghost = new Ghost(31,15);
            movementDetector = new MovementDetector(maze, flag);

            playerOne.setMovementDetector(movementDetector);
            playerOne.setMazeGfx(mazeGfx);

            playerTwo.setMovementDetector(movementDetector);
            playerTwo.setMazeGfx(mazeGfx);

            ghost.setMovementDetector(movementDetector);
            ghost.setMazeGfx(mazeGfx);

            mazeGfx.setPlayers(players);

            playerOne.walk();
            playerTwo.walk();
            start();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }

    }

    public void start() throws InterruptedException {
        while (!roundEnd) {
            Thread.sleep(50);
            ghost.move();
            roundEnd = movementDetector.roundEnd(players);
            if (playerOne.equals(playerTwo)) {
                sound.play(hit);
            }
        }
        if (playerOne.getHasFlag()) {
            points[0]++;
        } else {
            points[1]++;
        }
        restart();
    }


    private void restart() {

        try{
            sound.stop();
            sound.play(escapeSong);

            flag.resetFlag();
            playerOne.reset();
            playerTwo.reset();

            maze.init();
            maze.generate();
            mazeGfx.restartMazeGfx();

            roundEnd = false;
            start();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
    }
}
