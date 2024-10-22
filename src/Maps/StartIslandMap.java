package Maps;

import EnhancedMapTiles.PushableRock;
import Level.*;
import NPCs.*;
import Scripts.SimpleTextScript;
import Scripts.StartIslandMap.*;
import Tilesets.CommonTileset;

import java.util.ArrayList;

import NPCs.Bug;
import NPCs.Shrek;  
import NPCs.CapJV;

// Represents a test map to be used in a level
public class StartIslandMap extends Map {

    public StartIslandMap() {
        super("starting_map.txt", new CommonTileset());
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

        Walrus walrus = new Walrus(1, getMapTile(10, 24).getLocation().subtractY(40));
        walrus.setInteractScript(new WalrusScript());
        npcs.add(walrus);

        Walrus walrus2 = new Walrus(2, getMapTile(15, 24).getLocation().subtractY(40));
        walrus2.setInteractScript(new WalrusScript());
        npcs.add(walrus2);

        Dinosaur dinosaur = new Dinosaur(3, getMapTile(13, 4).getLocation());
        dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);

        //Shrek 'enemy' for combat test
        Shrek shrek = new Shrek(5, getMapTile(15, 20).getLocation().subtractX(20));
        shrek.setExistenceFlag("isInCombat");
        shrek.setInteractScript(new ShrekScript());
        npcs.add(shrek);

        Portal portal = new Portal(6, getMapTile(16, 25).getLocation());
        portal.setExistenceFlag("interactPortal");
        portal.setInteractScript(new PortalScript());
        npcs.add(portal);

        CapJV capJDV = new CapJV(3,getMapTile(20, 20).getLocation().subtractX(20));
        capJDV.setInteractScript(new TutorialScript());
        npcs.add(capJDV);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        // triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
        // triggers.add(new Trigger(790, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        // triggers.add(new Trigger(890, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        return triggers;
    }

    @Override
    public void loadScripts() {
        getMapTile(21, 19).setInteractScript(new SimpleTextScript("Cat's house"));

        getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));

        getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        getMapTile(2, 6).setInteractScript(new TreeScript());
    }

    //all new maps must override
    @Override
    public void loadMusic() {
        // Music.playMusic("Music/Crystal Caves.wav");
    }
}

