package Maps;

import Level.*;
import NPCs.Bug;
import NPCs.Island;
import NPCs.Bosses.HolyBeetle;
import NPCs.Interactable.Spider;
import Scripts.SimpleTextScript;
import Scripts.CaveMapScripts.*;
import Tilesets.MasterTileset;
import Tilesets.CaveTileset;
import java.util.ArrayList;

public class CaveMap extends Map {

    
    public CaveMap() {
        super("cave_map.txt", new CaveTileset());
        this.playerStartPosition = getMapTile(17, 22).getLocation();
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

        HolyBeetle beetleBoss = new HolyBeetle(5, getMapTile(40,11).getLocation(), 50, 5);
        beetleBoss.setExistenceFlag("beetleKilled");
        beetleBoss.setInteractScript(new BossBeetleScript());
        // beetleBoss.setIsHidden(true);
        npcs.add(beetleBoss);

        Spider spider = new Spider(6, getMapTile(30,10).getLocation());
        spider.setInteractScript(new SpiderScript());
        npcs.add(spider);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //add triggers below, commented out one is an example.

        triggers.add(new Trigger(790, 1130, 100, 10, new CaveExitScript()));
        return triggers;
    }

    @Override
    public void loadScripts() {
        // getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        getMapTile(37, 5).setInteractScript(new CaveHoleScript());
    }

    @Override
    public void loadMusic() {
        // Music.playMusic("Music/Crystal Caves.wav");
    }
}

