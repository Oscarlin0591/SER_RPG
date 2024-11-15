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


public class DateMap extends Map{
    // public static boolean Date = false;


    public DateMap() {
        super("Date_map.txt", new BattleMapTileset());
        this.playerStartPosition = getMapTile(15,7).getLocation();
    }
    
    //load enemies
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        BlueWitch blueWitch = new BlueWitch(801,getMapTile(7, 7).getLocation());
        npcs.add(blueWitch);
        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(0,0).getLocation().x, getMapTile(0,0).getLocation().y, 400, 300, new DateScript(), "DateWon"));
        return triggers;
    }

    @Override
    public void loadMusic() {
        
    }
}
