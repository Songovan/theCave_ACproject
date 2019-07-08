package org.academiadecodigo.batmancave;

import javafx.scene.text.Text;
import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Player.PlayerOne;
import org.academiadecodigo.batmancave.Player.PlayerTwo;
import org.academiadecodigo.batmancave.gameobjects.enemies.GhostSelector;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Maze;
import org.academiadecodigo.batmancave.maze.MovementDetector;
import org.academiadecodigo.batmancave.gameobjects.Usables.*;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;

import java.io.File;

public class Game {

    //Game
    private Menu menu;
    private Maze maze;
    private MazeGfx mazeGfx;
    private MovementDetector movementDetector;
    private PlayerOne playerOne;
    private PlayerTwo playerTwo;
    private Player[] players;
    private Ghost[] ghosts;
    private Flag flag;
    private boolean roundEnd;
    private int[] points;
    private GameStage stage;

    //Audio
    private Sound sound = new Sound();
    private File mainTheme = new File("./resources/startSong.wav");
    private File boo = new File("./resources/GOTCHA_BITCH.wav");
    private File escapeSong = new File("./resources/Danger.wav");
    private File powerUp = new File("./resources/Power_1.wav");
    private File hit = new File("./resources/moaning-woman_1.wav");

    //Constructor
    public Game() {
        maze = new Maze(41, 31);
        mazeGfx = new MazeGfx(maze);
        points = new int[]{0,0};
        stage = GameStage.SEARCHING;
        ghosts = new Ghost[2];
    }

    //Menu method
    public void menu(){

        try{
            sound.play(mainTheme);
            menu = new Menu();
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

        maze.init();

        maze.generate();

        flag = new Flag();

        playerOne = new PlayerOne(1,1);

        playerTwo = new PlayerTwo(maze.getLayout().length-2, maze.getLayout()[0].length-2);

        players = new Player[2];

        players[0] = playerOne;

        players[1] = playerTwo;

        movementDetector = new MovementDetector(maze, flag);

        playerOne.setMovementDetector(movementDetector);

        playerOne.setMazeGfx(mazeGfx);

        playerTwo.setMovementDetector(movementDetector);

        playerTwo.setMazeGfx(mazeGfx);

        mazeGfx.setFlag(flag);

        mazeGfx.init();

        mazeGfx.setPlayers(players);

        playerOne.walk();
        playerTwo.walk();

        try {
            start();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }

    }

    public void start() throws InterruptedException {

        int powerUpCounter = 0;

        while (!roundEnd) {
            Thread.sleep(200);
            // Make condition to win level and raise level
            if(stage == GameStage.SEARCHING) {
                if(playerOne.getHasFlag() || playerTwo.getHasFlag()) {
                    stage = GameStage.RETRIEVING;
                    ghosts[0] = new Ghost(1);
                    ghosts[1] = new Ghost(2);

                    ghosts[0].setMazeGfx(mazeGfx);
                    ghosts[0].setMovementDetector(movementDetector);

                    ghosts[1].setMazeGfx(mazeGfx);
                    ghosts[1].setMovementDetector(movementDetector);

                    mazeGfx.drawGhost(ghosts);
                }
            } else if (stage == GameStage.RETRIEVING) {

                if (powerUpCounter == 0) {
                    sound.playSFX(powerUp);
                    powerUpCounter++;
                }

                //System.out.println("RETRIEVING!");
                ghosts[0].move(GhostSelector.ONE);
                ghosts[1].move(GhostSelector.TWO);

                Player dead = movementDetector.killedByGhost(ghosts, players);

                if(dead != null) {
                    if (dead.getHasFlag()) {
                        sound.playSFX(boo);
                        dead.reset();
                        mazeGfx.playerCaught(dead.getType());
                    }
                }

                roundEnd = movementDetector.roundEnd(players);
            }

            if(movementDetector.playersClash(players)) {
                sound.playSFX(hit);
            }

        }

        if (playerOne.getHasFlag()) {
            points[0]++;
        } else {
            points[1]++;
        }

        Text winner = new Text();


        //restart();
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

    private enum GameStage {
        SEARCHING,
        RETRIEVING
    }

}
