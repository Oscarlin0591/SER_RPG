package Maps;

//import EnhancedMapTiles.PushableRock;
import Level.*;
import NPCs.*;
import NPCs.Bosses.Kraken;
import NPCs.Interactable.Shipwreck;
import Screens.PlayLevelScreen;
import Scripts.SimpleTextScript;
import Scripts.ShipwreckScripts.KrakenScript;
import Scripts.ShipwreckScripts.ShipwreckScript;
import Scripts.OceanMapScripts.ArcticScript;
import Scripts.OceanMapScripts.AtlantisScript;
import Scripts.OceanMapScripts.BadShipOfTheseusScript;
import Scripts.OceanMapScripts.CaveScript;
import Scripts.OceanMapScripts.GoodShipOfTheseusScript;
import Scripts.OceanMapScripts.IslandScript;
import Scripts.StartIslandMap.*;
import Tilesets.MasterTileset;
import Utils.Point;

import java.util.ArrayList;
import Screens.PlayLevelScreen;

// Represents a test map to be used in a level
public class OceanMap extends Map {
    protected int goodShipXPosition;
    protected int goodShipYPosition;
    
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

        Shipwreck shipwreck1 = new Shipwreck(5, getMapTile(5,20).getLocation(),"Shipwreck.png");
        shipwreck1.setInteractScript(new ShipwreckScript()/*SimpleTextScript("An unfortunate vessel appears to have fallen into the\nmarine abyss. You pray for the sailors' lost souls...")*/);
        npcs.add(shipwreck1);

        PirateShip pirateShip1 = new PirateShip(6, getMapTile(36,33).getLocation(), "pirateShip.png");
        // pirateShip1.setInteractScript(new PirateScript1());
        npcs.add(pirateShip1);
        
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

        //determine position of good ship based on quest progression
        if (PlayLevelScreen.flagManager.isFlagSet("goodShipMoved") && !PlayLevelScreen.flagManager.isFlagSet("goodShipPloy")) {
            this.goodShipXPosition = 6;
            this.goodShipYPosition = 35;
        } else if (PlayLevelScreen.flagManager.isFlagSet("goodShipPloy")) {
            this.goodShipYPosition = 33;
        } else {
            this.goodShipXPosition = 39;
            this.goodShipYPosition = 8;
        }

        ShipOfTheseus goodShipOfTheseus = new ShipOfTheseus(999, getMapTile(this.goodShipXPosition, this.goodShipYPosition).getLocation(), "LEFT");
        goodShipOfTheseus.setInteractScript(new GoodShipOfTheseusScript());
        npcs.add(goodShipOfTheseus);

        ShipOfTheseus badShipOfTheseus = new ShipOfTheseus(666, getMapTile(6, 30).getLocation(), "RIGHT");
        badShipOfTheseus.setInteractScript(new BadShipOfTheseusScript());
        npcs.add(badShipOfTheseus);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //add triggers below, commented out one is an example.

        triggers.add(new Trigger(930, 2285, 300, 20, new ArcticScript()));
        return triggers;
    }

    @Override
    public void loadScripts() {
        getMapTile(28, 34).setInteractScript(new SimpleTextScript("South Pole Below"));

        // getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));

        // getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        // getMapTile(2, 6).setInteractScript(new TreeScript());
    }

    @Override
    public void loadMusic() {
        // Music.playMusic("Music/Seafaring Humdrum.wav");
    }
}

