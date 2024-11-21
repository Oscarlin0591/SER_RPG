package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Music;
import Level.NPC;
import Level.Trigger;
import NPCs.Bosses.Kraken;
import NPCs.Interactable.Cannibal;
import NPCs.Interactable.CannibalGhost;
import NPCs.Interactable.FlintlockGhost;
import NPCs.Interactable.GhostPirate;
import NPCs.Interactable.SwordGhost;
import Scripts.ShipwreckScripts.*;
import Tilesets.*;

public class ShipwreckMap extends Map{
    public ShipwreckMap() {
        super("shipwreck_map.txt", new ShipwreckTileset());
        this.playerStartPosition = getMapTile(23, 3).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        GhostPirate ghost = new GhostPirate(10, getMapTile(5,20).getLocation());
        ghost.setInteractScript(new GhostPirateScript());
        ghost.setExistenceFlag("appleHaunted");
        npcs.add(ghost);

        SwordGhost swordGhost = new SwordGhost(11, getMapTile(20,50).getLocation());
        swordGhost.setInteractScript(new SwordGhostScript());
        npcs.add(swordGhost);

        Kraken kraken = new Kraken(3, getMapTile(52, 54).getLocation(), -1, -1, -1, -1);
        kraken.setExistenceFlag("krakenKilled");
        kraken.setInteractScript(new KrakenScript());
        npcs.add(kraken);

        Cannibal cannibal = new Cannibal(36, getMapTile(21, 40).getLocation());
        cannibal.setExistenceFlag("doneWithCannibal");
        npcs.add(cannibal);

        CannibalGhost cannibalGhost = new CannibalGhost(37, getMapTile(21, 6).getLocation());
        cannibalGhost.setExistenceFlag("cannibalGhost");
        cannibalGhost.setInteractScript(new CannibalGhostScript());
        npcs.add(cannibalGhost);

        FlintlockGhost flintlockGhost = new FlintlockGhost(38, getMapTile(50, 20).getLocation());
        flintlockGhost.setInteractScript(new FlintlockGhostScript());
        npcs.add(flintlockGhost);
        
        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //add triggers below, commented out one is an example.

        triggers.add(new Trigger(getMapTile(21, 0).getLocation().x, getMapTile(21, 0).getLocation().y, 150, 50, new ShipwreckExitScript()));
        triggers.add(new Trigger(getMapTile(21, 6).getLocation().x, getMapTile(21, 6).getLocation().y, 150, 50, new CannibalRunScript(),"doneWithCannibal"));
        
        return triggers;
    }

    public void loadMusic() {
        //Music.playMusic("Music/Sinking Feeling.wav");
    }
}
