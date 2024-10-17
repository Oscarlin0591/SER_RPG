package Maps;

import Level.*;
import NPCs.*;
import Screens.PlayLevelScreen;
import Scripts.StartIslandMap.*;
import Tilesets.RPGTileset;
import java.util.ArrayList;


// import Engine.GamePanel;
// import java.lang.Thread;

public class BattleMap extends Map{
    // public static boolean battle = false;
    public static Enemy enemy;
    // public static float enemyHealth;
    // public static float playerHealth;

    public BattleMap() {
        super("battle_map.txt", new RPGTileset());
        this.playerStartPosition = getMapTile(12, 6).getLocation();
    }

    public BattleMap(Enemy newEnemy) {
        super("battle_map.txt", new RPGTileset());
        this.playerStartPosition = getMapTile(12, 6).getLocation();
        enemy = newEnemy;

        battle();
    }
    
    //load enemies
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        
        this.enemy = new Bug(999, getMapTile(3, 8).getLocation(), 5, 5);
        npcs.add(enemy);

        // if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("walrusEnemy")) {
        //     Walrus enemy_1 = new Walrus(500, getMapTile(6, 6).getLocation());
        //     npcs.add(enemy_1);
        // }

        Dinosaur enemy_2 = new Dinosaur(501, getMapTile(6, 8).getLocation());
        npcs.add(enemy_2);

        //redefined Bug as an Enemy object
        // if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("bugEnemy")) {
        //     Bug enemy_3 = new Bug(502, getMapTile(3, 8).getLocation(), 5, 5);
        //     enemy_3.lock();
        //     npcs.add(enemy_3);
        // }

        if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("shrekEnemy")) {
            Enemy enemy_4 = new Shrek(503, getMapTile(4,4).getLocation(), 10, 1);
            npcs.set(0, enemy_4);

         }

        if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("krakenEnemy")) {
            Enemy kraken = new Kraken(504, getMapTile(4, 4).getLocation(), 1, 1);
            npcs.set(0,kraken);
            
        }

        return npcs;
    }

    // redundant method as of now
    public boolean battle() {

        if (this.player.getHealth() <= 0){
            return false;
        } else if (enemy.getHealth() <= 0) {
            return true;
        } else {
            System.out.println("ERROR on battle Method");
            return false;
        }
    }

    public static Enemy getEnemy() {
        return enemy;
    }

    // public static float getEnemyStrength() {
    //     return enemy.getStrength();
    // }

    // public static float getEnemyHealth() {
    //     return enemy.getHealth();
    // }


    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(400, 288, 400, 300, new BattleScript(), "battleWon"));
        System.out.println("DEBUG: Triggers loaded");
        return triggers;
    }

    @Override
    public void loadMusic() {
        Music.playMusic("Music/Gold_in_C_demo.wav");
    }
}
