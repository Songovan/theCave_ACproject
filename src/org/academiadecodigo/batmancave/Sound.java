package org.academiadecodigo.batmancave;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    //properties
    private AudioInputStream audioStrmObj;
    private AudioFormat format;
    private Clip audioClip;

    //playThemeSound method
    public void play(File sound) {

        try {
            audioStrmObj = AudioSystem.getAudioInputStream(sound);
            format = audioStrmObj.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStrmObj);
            audioClip.start();
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException uns) {
            System.out.println("Unsupported Audio Exception");
        } catch (IOException io) {
            System.out.println("Unsupported IO Exception");
        } catch (LineUnavailableException line) {
            System.out.println("Line Unavailable Exception");
        }
    }

    //playSFX method
    public void playSFX(File sound) {
        try {
            audioStrmObj = AudioSystem.getAudioInputStream(sound);
            format = audioStrmObj.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStrmObj);
            audioClip.start();
        } catch (UnsupportedAudioFileException uns) {
            System.out.println("Unsupported Audio Exception");
        } catch (IOException io) {
            System.out.println("Unsupported IO Exception");
        } catch (LineUnavailableException line) {
            System.out.println("Line Unavailable Exception");
        }
    }

    //stop
    public void stop () {
        audioClip.stop();
    }
}
