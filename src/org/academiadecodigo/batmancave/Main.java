package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.maze.Maze;

public class Main {

    public static void main(String[] args) {


        Maze maze = new Maze(9,9);

        maze.init();

        maze.printMaze();

        maze.generate();

        maze.printMaze();




    }

}
