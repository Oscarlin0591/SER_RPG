package Maps;

import Level.Map;
import Level.Music;
import Level.NPC;
import Level.Trigger;
import NPCs.Bosses.*;
import NPCs.*;
import Scripts.ArcticMapScripts.*;
import Tilesets.ArcticTileset;
import java.util.ArrayList;

public class ArcticMap extends Map{

    public ArcticMap() {
        super("arctic_map.txt", new ArcticTileset());
        this.playerStartPosition = getMapTile(12, 1).getLocation();
    }
    // @Override
    // public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    //     ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

    //     return enhancedMapTiles;
    // }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Yeti yeti = new Yeti(201, getMapTile(32,6).getLocation(), 100, 8,1,1);
        yeti.setInteractScript(new YetiScript());
        npcs.add(yeti);

        Krampus krampus = new Krampus(202, getMapTile(45,41).getLocation(), 150, 10, 1, 1);
        krampus.setInteractScript(new KrampusScript());
        npcs.add(krampus);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(11, 0).getLocation().x, getMapTile(11, 0).getLocation().y, 144, 25, new ArcticExitScript()));
        return triggers;
    }

    @Override
    public void loadScripts() {
        // getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        // getMapTile(37, 5).setInteractScript(new AtlantisHoleScript());
    }

    @Override
    public void loadMusic() {
        // Music.playMusic("Music/Sparkling_Rime16bit.wav");
    }
    
}
