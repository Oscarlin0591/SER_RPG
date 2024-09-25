package Level;

import javax.sound.sampled.*;
import java.io.File;
import Engine.Config;
import Screens.PlayLevelScreen;

public class Music {
    //iniitialize static variables
    private static Map currentMap = null;
    private static Clip currentClip = null;

    public static void playMusic (String musicFilePath) {
        //initialize currentMap and (re)initialize currentClip if needed
        if (currentMap == null)
            currentMap = PlayLevelScreen.getMap();

        if (currentClip == null || currentMap != PlayLevelScreen.getMap()) {
            try {
                if (currentMap != null)
                    currentClip.stop();
                    
                currentClip = AudioSystem.getClip();
            } catch (Exception e) { //not properly handled yet
                System.out.println(e.toString());
            }
        }

        // //check if map changed and stop former song if so
        // if (currentMap != PlayLevelScreen.getMap()) {
        //     // System.out.println(currentClip.isOpen());
        //     // System.out.println(currentMap.getMapFileName());
        //     currentClip.loop(0);
        //     currentClip.stop();
        //     // System.out.println(currentClip.isOpen());
        // }

        try {
            //create file from argument and create audio input stream from file
            File song = new File(Config.RESOURCES_PATH + musicFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(song);

            //create and play clip
            currentClip.open(audioInputStream);
            currentClip.loop(Clip.LOOP_CONTINUOUSLY);
            currentClip.start();

            //store currentMap when song played
            currentMap = PlayLevelScreen.getMap();

        } catch (Exception e) { //currently not properly handled
            System.out.println(e.toString());
        }
    }
}
