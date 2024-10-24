package Maps;

//import EnhancedMapTiles.PushableRock;
import Level.*;
import NPCs.*;
import NPCs.Interactable.Shipwreck;
import Screens.PlayLevelScreen;
import Scripts.SimpleTextScript;
import Scripts.StartIslandMap.*;
import Tilesets.MasterTileset;
import java.util.ArrayList;
import Screens.PlayLevelScreen;

// Represents a test map to be used in a level
public class OceanMap extends Map {

    public OceanMap() {
        super("game_map.txt", new MasterTileset());
        this.playerStartPosition = getMapTile(13, 6).getLocation();
    }

    public OceanMap(int x, int y) {
        super("game_map.txt", new MasterTileset());
        this.playerStartPosition = getMapTile(x, y).getLocation();
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

        RedPotion potion = new RedPotion(77, getMapTile(2,10).getLocation());
        potion.setExistenceFlag("oceanPotion");
        potion.setInteractScript(new PotionScript());
        npcs.add(potion);

        Meat mysteryMeat = new Meat(9, getMapTile(16,30).getLocation());
        mysteryMeat.setInteractScript(new MeatScript());
        npcs.add(mysteryMeat);

        MysteriousMan mysteryMan = new MysteriousMan(10,getMapTile(17,15).getLocation());
        mysteryMan.setInteractScript(new ManScript());
        npcs.add(mysteryMan);

        BluePotion bluePotion = new BluePotion(11,getMapTile(1,5).getLocation());
        bluePotion.setInteractScript(new SuperPotionScript());
        npcs.add(bluePotion);

        Girl girl = new Girl(12,getMapTile(6,4).getLocation());
        girl.setInteractScript(new GirlScript());
        npcs.add(girl);

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

