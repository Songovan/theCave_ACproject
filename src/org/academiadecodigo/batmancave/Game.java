package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Player.PlayerOne;
import org.academiadecodigo.batmancave.Player.PlayerTwo;
import org.academiadecodigo.batmancave.gameobjects.enemies.GhostSelector;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Maze;
import org.academiadecodigo.batmancave.maze.MovementDetector;
import org.academiadecodigo.batmancave.gameobjects.Usables.*;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Game {

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
    private File mainTheme = new File("./resources/atTheEndOfAllThings.wav"); // path to your clip
    private File escapeSong = new File("./resources/flee-flag.wav"); // path to your clip
    private GameStage stage;



    private AudioInputStream audioStrmObj;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip audioClip;



    public Game() {
        maze = new Maze(41, 31);
        mazeGfx = new MazeGfx(maze);
        points = new int[]{0,0};
        stage = GameStage.SEARCHING;
        ghosts = new Ghost[2];
    }


    public void menu(){
        menu = new Menu();

        menu.keyboard();

        try{
            playThemeSong();
        } catch (UnsupportedAudioFileException e1) {
            System.out.println("NOT");
        } catch (IOException e2) {

        } catch (LineUnavailableException e3) {

        }

        while (!menu.isGameStart()){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e ) {
            }
        }
        System.out.println("uftcxydj");
        init();
        System.out.println("2");

    }

    public void init() {

        maze.init();

        maze.generate();



        flag = new Flag();

        //flag.setMazeGfx(mazeGfx);

        playerOne = new PlayerOne(1,1);

        playerTwo = new PlayerTwo(maze.getLayout().length-2, maze.getLayout()[0].length-2);

        players = new Player[2];

        players[0] = playerOne;

        players[1] = playerTwo;

        //ghost = new Ghost(1);

        movementDetector = new MovementDetector(maze, flag);

        playerOne.setMovementDetector(movementDetector);

        playerOne.setMazeGfx(mazeGfx);

        playerTwo.setMovementDetector(movementDetector);

        playerTwo.setMazeGfx(mazeGfx);

        mazeGfx.setPlayers(players);

        mazeGfx.setFlag(flag);

        mazeGfx.init();

        playerOne.walk();

        playerTwo.walk();

        try{
            start();
        } catch (InterruptedException e) {

        }

    }

    public void start() throws InterruptedException {
        while (!roundEnd) {
            Thread.sleep(200);
            // Make condition to win level and raise level
            System.out.println("Stage " + stage);
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
                //System.out.println("RETRIEVING!");
                ghosts[0].move(GhostSelector.ONE);
                ghosts[1].move(GhostSelector.TWO);

                Player dead = movementDetector.killedByGhost(ghosts, players);

                if(dead != null) {
                    if (dead.getHasFlag()) {
                        dead.reset();
                        mazeGfx.playerCaught(dead.getType());
                    }
                }

                roundEnd = movementDetector.roundEnd(players);
                //System.out.println("Ghost 1 at: " + ghosts[0].getPos().getCol() + ", " + ghosts[0].getPos().getRow());
                //System.out.println("Ghost 2 at: " + ghosts[1].getPos().getCol() + ", " + ghosts[1].getPos().getRow());
                //System.out.println(roundEnd);
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


        flag.resetFlag();

        playerOne.reset();
        playerTwo.reset();



        //flag = new Flag(21, 15);

        maze.init();
        maze.generate();

        mazeGfx.restartMazeGfx();

        roundEnd = false;


        try{
            start();
        } catch (InterruptedException e) {

        }

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


    private enum GameStage {
        SEARCHING,
        RETRIEVING
    }


}
