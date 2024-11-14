package Maps;

import Level.Map;
import Level.Music;
import Level.NPC;
import Level.Trigger;
import NPCs.Bosses.*;
import NPCs.Interactable.BlueWitch;
import NPCs.Interactable.ExitPort;
import NPCs.*;
// import Scripts.EndMapScripts.*;
import Tilesets.EndTileset;
import java.util.ArrayList;

public class EndMap extends Map{

    public EndMap() {
        super("end_map.txt", new EndTileset());
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

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        // triggers.add(new Trigger(getMapTile(7, 3).getLocation().x, getMapTile(7, 3).getLocation().y+30, 100, 15, new EndExitScript()));
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
