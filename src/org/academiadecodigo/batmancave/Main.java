package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Maze;
import org.academiadecodigo.batmancave.maze.MovementDetector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main {

    public static void main(String[] args) throws InterruptedException{

        /*
        //Menu menu = new Menu();

        Game game = new Game();

        //maze.printMaze();

        maze.generate();

        //maze.printMaze();

        MazeGfx mazeGfx = new MazeGfx(maze);

        mazeGfx.init();



        Player player = new Player();

        MovementDetector movementDetector = new MovementDetector(maze, player);

        player.setMazeGfx(mazeGfx);

        player.setMovementDetector(movementDetector);

        player.walk();

        */

        Game game = new Game();

        game.menu();

        game.init();

        try {
            game.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    //Menu menu = new Menu();
    }



}
