package Maps;

import Level.*;
import NPCs.AppleTree;
import NPCs.Bosses.Capricorn;
import Scripts.AtlantisMapScripts.*;
import Scripts.StartIslandMap.AppleTreeScript;
import Tilesets.AtlantisTileset;
import java.util.ArrayList;

public class AtlantisMap extends Map {

    
    public AtlantisMap() {
        super("atlantis_map.txt", new AtlantisTileset());
        this.playerStartPosition = getMapTile(2, 6).getLocation();
    }

    // @Override
    // public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    //     ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

    //     return enhancedMapTiles;
    // }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        AppleTree tree = new AppleTree(17, getMapTile(45,45).getLocation());
        tree.setInteractScript(new AppleTreeScript());
        tree.setExistenceFlag("atlantisTreeBroken");
        npcs.add(tree);

        Capricorn capricorn = new Capricorn(18, getMapTile(49,11).getLocation(),50,5,2,2);
        capricorn.setInteractScript(new CapricornScript());
        //tree.setExistenceFlag("atlantisTreeBroken");
        npcs.add(capricorn);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //add triggers below, commented out one is an example.

        triggers.add(new Trigger(getMapTile(0, 6).getLocation().x, getMapTile(0, 6).getLocation().y, 15, 50, new AtlantisExitScript()));
        return triggers;
    }

    @Override
    public void loadScripts() {
        // getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        // getMapTile(37, 5).setInteractScript(new AtlantisHoleScript());
    }

    @Override
    public void loadMusic() {
        Music.playMusic("Music/Atlantis.wav");
    }
}

