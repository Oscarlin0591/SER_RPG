package Maps;

import Level.*;
import NPCs.*;
import NPCs.Bosses.*;
import NPCs.Interactable.BlueWitch;
import Screens.PlayLevelScreen;
import Scripts.StartIslandMap.*;
import Tilesets.BattleMapTileset;
import Tilesets.RPGTileset;
import Utils.Direction;

import java.util.ArrayList;
import java.util.Random;

import Engine.GameWindow;


public class DateMap extends Map{
    // public static boolean Date = false;
public static NPC datePartner;
public NPC defaultNPC;

    public DateMap() {
        super("Date_map.txt", new BattleMapTileset());
        this.playerStartPosition = getMapTile(15,7).getLocation();
    }
    
    //load enemies
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        defaultNPC = new BlueWitch(501,getMapTile(7, 7).getLocation());

        datePartner = defaultNPC;
        npcs.add(datePartner);

        if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("blueWitchDate")) {
            datePartner = new BlueWitch(501,getMapTile(7, 7).getLocation());
            npcs.set(0,datePartner);
        }

        if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("jvDate")) {
            datePartner = new CapJV(502,getMapTile(7, 7).getLocation());
            npcs.set(0,datePartner);
        }

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(0,0).getLocation().x, getMapTile(0,0).getLocation().y, 1000, 1000, new DateScript(), "DateWon"));
        return triggers;
    }

    @Override
    public void loadMusic() {
        Music.playMusic("Music/date.wav");
    }
}
