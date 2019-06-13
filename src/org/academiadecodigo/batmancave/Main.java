package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Maze;
import org.academiadecodigo.batmancave.maze.MovementDetector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main {

    public static void main(String[] args) throws InterruptedException{

        Game game = new Game();

        game.init();

        game.start();
    }

}
