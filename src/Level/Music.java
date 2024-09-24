package Level;

import javax.sound.sampled.*;
import java.io.File;
import Engine.Config;

public class Music extends Thread {
    public static void playMusic () {
        try {
            File song = new File(Config.RESOURCES_PATH + "Music/Seafaring Humdrum.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(song);

            Clip clip = AudioSystem.getClip();

            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

            while(true) {
                System.out.println("");
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
