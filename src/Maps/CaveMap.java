package Maps;

import Level.*;
import NPCs.Bug;
import NPCs.Island;
import Scripts.SimpleTextScript;
import Scripts.CaveMapScripts.BugScript;
import Scripts.CaveMapScripts.CaveHoleScript;
import Tilesets.MasterTileset;
import Tilesets.CaveTileset;
import java.util.ArrayList;

public class CaveMap extends Map {
    public CaveMap() {
        super("cave_map.txt", new CaveTileset());
        this.playerStartPosition = getMapTile(17, 23).getLocation();
    }

    // @Override
    // public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    //     ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

    //     return enhancedMapTiles;
    // }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Bug bug = new Bug(4, getMapTile(7, 20).getLocation().subtractX(20));
        bug.setInteractScript(new BugScript());
        npcs.add(bug);

        return npcs;
    }

    // @Override
    // public ArrayList<Trigger> loadTriggers() {
    //     ArrayList<Trigger> triggers = new ArrayList<>();
    //     //add triggers below, commented out one is an example.

    //     // triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
    //     return triggers;
    // }

    @Override
    public void loadScripts() {
        // getMapTile(21, 19).setInteractScript(new SimpleTextScript("Cat's house"));

        // getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));

        // getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        getMapTile(37, 5).setInteractScript(new CaveHoleScript());
    }

    @Override
    public void loadMusic() {
        // Music.playMusic("Music/Crystal Caves.wav");
    }
}

