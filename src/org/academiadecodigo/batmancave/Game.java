package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Maze;
import org.academiadecodigo.batmancave.maze.MovementDetector;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;

import javax.sound.sampled.*;
import java.io.File;

public class Game {

    private Maze maze;
    private MazeGfx mazeGfx;
    private MovementDetector movementDetector;
    private Player player;
    private Ghost ghost;
    private int gameLevel;

    public Game() {
        maze = new Maze(61, 41);
        mazeGfx = new MazeGfx(maze);
        gameLevel = 1;

    }

    public void init() {

        runSound();

        maze.init();

        maze.generate();

        mazeGfx.init();

        player = new Player();

        ghost = new Ghost();

        movementDetector = new MovementDetector(maze, player, ghost);

        player.setMovementDetector(movementDetector);

        player.setMazeGfx(mazeGfx);

        ghost.setMovementDetector(movementDetector);

        ghost.setMazeGfx(mazeGfx);

    }

    public void start() {
        player.walk();

        while(true) {

            // Move Ghost

            // Make condition to win level and raise level
        }
    }

    public void runSound() {
        try {

            File clipFile = new File("./resources/atTheEndOfAllThings.wav"); // path to your clip
            AudioInputStream audioStrmObj = AudioSystem.getAudioInputStream(clipFile);
            AudioFormat format = audioStrmObj.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStrmObj);
            audioClip.start();
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            System.out.println("NOT");
        }
    }

    //gameLevel


    //getGameLevel method
    public int getGameLevel() {
        return gameLevel;
    }

    //setGameLevel method
    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }
}
