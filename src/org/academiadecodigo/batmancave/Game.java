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

public class Game {

    private Maze maze;
    private MazeGfx mazeGfx;
    private MovementDetector movementDetector;
    private PlayerOne playerOne;
    private PlayerTwo playerTwo;
    private Player[] players;
    private Ghost ghost;
    private Flag flag;
    private int gameLevel;
    private boolean roundEnd;
    private int[] points;
    private File mainTheme = new File("./resources/atTheEndOfAllThings.wav"); // path to your clip
    private File escapeSong = new File("./resources/flee-flag.wav"); // path to your clip



    private AudioInputStream audioStrmObj;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip audioClip;



    public Game() {
        maze = new Maze(41, 31);
        mazeGfx = new MazeGfx(maze);
        gameLevel = 1;
        points = new int[]{0,0};
    }

    public void init() {

        try{
            playThemeSong();
        } catch (UnsupportedAudioFileException e1) {
            System.out.println("NOT");
        } catch (IOException e2) {

        } catch (LineUnavailableException e3) {

        }

        maze.init();

        maze.generate();

        mazeGfx.init();

        flag = new Flag(21,15);

        flag.setMazeGfx(mazeGfx);

        playerOne = new PlayerOne(0,1);

        playerTwo = new PlayerTwo(maze.getLayout().length-1, maze.getLayout()[0].length-2);

        players = new Player[2];

        players[0] = playerOne;

        players[1] = playerTwo;

        ghost = new Ghost();

        movementDetector = new MovementDetector(maze, flag);

        playerOne.setMovementDetector(movementDetector);

        playerOne.setMazeGfx(mazeGfx);

        ghost.setMovementDetector(movementDetector);

        ghost.setMazeGfx(mazeGfx);

        playerTwo.setMovementDetector(movementDetector);

        playerTwo.setMazeGfx(mazeGfx);

        mazeGfx.setPlayers(players);

        roundEnd = false;

    }

    public void start() throws InterruptedException{
        playerOne.walk();
        playerTwo.walk();

        while(!roundEnd) {

            Thread.sleep(50);
            // Move Ghost

            // Make condition to win level and raise level
            roundEnd = movementDetector.roundEnd(players);

        }

        if(playerOne.getHasFlag()) {
            points[0]++;
        } else {
            points[1]++;
        }

        restart();
    }

    private void restart() {

        playerOne.reset();
        playerTwo.reset();

        mazeGfx.restartMazeGfx();

        roundEnd = false;

    }

    private void playThemeSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioStrmObj = AudioSystem.getAudioInputStream(escapeSong);
        format = audioStrmObj.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.open(audioStrmObj);
        audioClip.start();
        audioClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void stopThemeSong() {
        audioClip.stop();
    }

    private void playEscapeSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioStrmObj = AudioSystem.getAudioInputStream(escapeSong);
        format = audioStrmObj.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.open(audioStrmObj);
        audioClip.start();
        audioClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void stopEscapeSong() {
        audioClip.stop();
    }



}
