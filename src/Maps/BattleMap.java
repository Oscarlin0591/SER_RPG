package Maps;

import Level.*;
import NPCs.*;
import Screens.PlayLevelScreen;
import Scripts.SimpleTextScript;
import Scripts.StartIslandMap.*;
import Tilesets.RPGTileset;
import java.util.ArrayList;
import java.lang.Thread;

public class BattleMap extends Map{
    public static boolean battle = false;

    public BattleMap() {
    super("battle_map.txt", new RPGTileset());
    this.playerStartPosition = getMapTile(12, 6).getLocation();
    
    }
    
    //load enemies
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Walrus enemy_1 = new Walrus(500, getMapTile(6, 6).getLocation());
        npcs.add(enemy_1);

        Dinosaur enemy_2 = new Dinosaur(501, getMapTile(6, 8).getLocation());
        npcs.add(enemy_2);

        //redefined Bug as an Enemy object
        Bug enemy_3 = new Bug(502, getMapTile(3, 8).getLocation());
        enemy_3.lock();
        npcs.add(enemy_3);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(576, 288, 50, 10, new BattleScript(), "battleWon"));
        triggers.add(new Trigger(576, 300, 10, 80, new BattleScript(), "battleWon"));
        triggers.add(new Trigger(400, 288, 400, 300, new BattleScript(), "battleWon"));
        System.out.println("DEBUG: Triggers loaded");
        return triggers;
    }

    @Override
    public void loadMusic() {
        Music.playMusic("Music/Gold_in_C_demo.wav");
    }
}
