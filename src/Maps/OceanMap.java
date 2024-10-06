package Maps;

import EnhancedMapTiles.PushableRock;
import Level.*;
import NPCs.Island;
import Scripts.SimpleTextScript;
import Scripts.StartIslandMap.*;
// import Tilesets.CommonTileset;
import Tilesets.RPGTileset;
import java.util.ArrayList;
import java.lang.Thread;

// Represents a test map to be used in a level
public class OceanMap extends Map {

    public OceanMap() {
        super("game_map.txt", new RPGTileset());
        this.playerStartPosition = getMapTile(17, 20).getLocation();
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Island island = new Island(2, getMapTile(13, 4).getLocation());
        island.setExistenceFlag("toggleIsland");
        island.setInteractScript(new IslandScript());
        npcs.add(island);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //add triggers below, commented out one is an example.

        // triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
        return triggers;
    }

    @Override
    public void loadScripts() {
        getMapTile(21, 19).setInteractScript(new SimpleTextScript("Cat's house"));

        getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));

        getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        getMapTile(2, 6).setInteractScript(new TreeScript());
    }

    @Override
    public void loadMusic() {
        Music.playMusic("Music/Seafaring Humdrum.wav");
    }
}

