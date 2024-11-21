package Maps;

import Level.Map;
import Level.Music;
import Level.NPC;
import Level.Trigger;
import NPCs.Bosses.*;
import NPCs.Interactable.BlueWitch;
import NPCs.Interactable.ExitPort;
import NPCs.*;
import Scripts.ArcticMapScripts.*;
import Tilesets.ArcticTileset;
import java.util.ArrayList;

public class ArcticMap extends Map{

    public ArcticMap() {
        super("arctic_map.txt", new ArcticTileset());
        this.playerStartPosition = getMapTile(7, 2).getLocation();
    }
    // @Override
    // public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    //     ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

    //     return enhancedMapTiles;
    // }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Yeti yeti = new Yeti(803, getMapTile(27,8).getLocation(), 100, 8,1,1);
        yeti.setInteractScript(new YetiScript());
        npcs.add(yeti);

        Krampus krampus = new Krampus(202, getMapTile(45,41).getLocation(), 150, 10, 1, 1);
        krampus.setInteractScript(new KrampusScript());
        npcs.add(krampus);

        BlueWitch blueWitch = new BlueWitch(203, getMapTile(5, 33).getLocation());
        blueWitch.setInteractScript(new BlueWitchScript());
        npcs.add(blueWitch);

        ExitPort port = new ExitPort(999, getMapTile(6,1).getLocation(), "ArticPort.png");
        port.setIsUncollidable(true);
        npcs.add(port);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(7, 3).getLocation().x, getMapTile(7, 3).getLocation().y+30, 100, 15, new ArcticExitScript()));
        return triggers;
    }

    @Override
    public void loadScripts() {
        // getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        // getMapTile(37, 5).setInteractScript(new AtlantisHoleScript());
    }

    @Override
    public void loadMusic() {
        Music.playMusic("Music/Sparkling_Rime16bit.wav");
    }
    
}
