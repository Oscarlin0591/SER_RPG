package Maps;

//import EnhancedMapTiles.PushableRock;
import Level.*;
import NPCs.*;
import NPCs.Interactable.Shipwreck;
import Screens.PlayLevelScreen;
import Scripts.SimpleTextScript;
import Scripts.StartIslandMap.*;
import Tilesets.MasterTileset;
import Utils.Point;

import java.util.ArrayList;
import Screens.PlayLevelScreen;

// Represents a test map to be used in a level
public class OceanMap extends Map {

    public OceanMap() {
        super("game_map.txt", new MasterTileset());
        this.playerStartPosition = getMapTile(13, 6).getLocation();
    }

    public OceanMap(Point playerLoc) {
        super("game_map.txt", new MasterTileset());
        System.out.println((int)(Math.ceil(playerLoc.x/16)) + " " + (int)playerLoc.y);
        this.playerStartPosition = getMapTile((int)(Math.ceil(playerLoc.x/16)), (int)(Math.ceil(playerLoc.y/16))).getLocation();
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
        island.setInteractScript(new IslandScript());
        npcs.add(island);

        Shipwreck shipwreck = new Shipwreck(5, getMapTile(5,20).getLocation(),"Shipwreck.png");
        shipwreck.setInteractScript(new SimpleTextScript("An unfortunate vessel appears to have fallen into the\nmarine abyss. You pray for the sailors' lost souls..."));
        npcs.add(shipwreck);

        //if kraken not killed, add it to npcs
        Kraken kraken = new Kraken(3, getMapTile(20, 16).getLocation(), -1, -1);
        kraken.setExistenceFlag("krakenKilled");
        kraken.setInteractScript(new KrakenScript());
        npcs.add(kraken);
        
        Cave cave = new Cave(3, getMapTile(2, 13).getLocation());
        cave.setInteractScript(new CaveScript());
        npcs.add(cave);

        Atlantis atlantis = new Atlantis(5, getMapTile(36, 18).getLocation());
        atlantis.setInteractScript(new AtlantisScript());
        npcs.add(atlantis);

        RedPotion potion = new RedPotion(77, getMapTile(2,10).getLocation());
        potion.setExistenceFlag("oceanPotion");
        potion.setInteractScript(new PotionScript());
        npcs.add(potion);

        BluePotion bluePotion = new BluePotion(11,getMapTile(1,5).getLocation());
        bluePotion.setInteractScript(new SuperPotionScript());
        npcs.add(bluePotion);

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

