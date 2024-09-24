package Level;

import javax.sound.sampled.*;

import Engine.Config;

import java.io.File;

public class Music {
    public static void playMusic () {
        try {
            Clip clip = AudioSystem.getClip();
            File song = new File(Config.RESOURCES_PATH + "Music/Crystal Caves.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(song);
            clip.open(ais);
        } catch (Exception e) {
            System.out.println("shit");
        }
    }
}
