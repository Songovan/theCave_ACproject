package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Maze;
import org.academiadecodigo.batmancave.maze.MovementDetector;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;

public class Game {

    private Maze maze;
    private MazeGfx mazeGfx;
    private MovementDetector movementDetector;
    private Player player;
    private Ghost ghost;
    private int gameLevel;

    public Game() {
        maze = new Maze(41, 31);
        mazeGfx = new MazeGfx(maze);
        gameLevel = 1;

    }

    public void init() {

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
