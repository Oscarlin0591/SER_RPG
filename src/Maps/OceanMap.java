package Maps;

import EnhancedMapTiles.PushableRock;
import Level.*;
// import NPCs.Bug;
// import NPCs.island;
// import NPCs.Walrus;
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

        PushableRock pushableRock = new PushableRock(getMapTile(2, 7).getLocation());
        enhancedMapTiles.add(pushableRock);

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
        triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(790, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(890, 960, 10, 80, new LostBallScript(), "hasLostBall"));
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

