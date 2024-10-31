package Maps;

import Level.Map;
import Level.Music;
import Level.NPC;
import Level.Trigger;
import Scripts.AtlantisMapScripts.AtlantisExitScript;
import Tilesets.ArcticTileset;
import java.util.ArrayList;

public class ArcticMap extends Map{

    public ArcticMap() {
        super("arctic_map.txt", new ArcticTileset());
        
    }
    // @Override
    // public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    //     ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

    //     return enhancedMapTiles;
    // }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //add triggers below, commented out one is an example.

        // triggers.add(new Trigger(getMapTile(0, 6).getLocation().x, getMapTile(0, 6).getLocation().y, 15, 50, new AtlantisExitScript()));
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
