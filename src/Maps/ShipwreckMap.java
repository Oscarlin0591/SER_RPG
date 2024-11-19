package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Music;
import Level.NPC;
import Level.Trigger;
import NPCs.Bosses.Kraken;
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

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //add triggers below, commented out one is an example.

        triggers.add(new Trigger(getMapTile(21, 0).getLocation().x, getMapTile(21, 0).getLocation().y, 15, 50, new ShipwreckExitScript()));
        return triggers;
    }

    public void loadMusic() {
        Music.playMusic("Music/Sinking Feeling.wav");
    }
}
