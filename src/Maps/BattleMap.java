package Maps;

import Level.*;
import NPCs.*;
import Screens.PlayLevelScreen;
import Scripts.StartIslandMap.*;
import Tilesets.RPGTileset;
import java.util.ArrayList;
import java.util.Random;


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
        
        //set default enemy
        Random rand = new Random();
        int ranEnemy = rand.nextInt(2);
        switch (ranEnemy) {
            case 0:
                enemy = new Bug(999, getMapTile(3, 8).getLocation(), 5, 5);
                break;
            case 1:
                enemy = new Shrek(503, getMapTile(3,8).getLocation(), 10, 1);
                break;
            default:
                break;
        }
        npcs.add(enemy);

        // if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("walrusEnemy")) {
        //     Walrus enemy_1 = new Walrus(500, getMapTile(6, 6).getLocation());
        //     npcs.add(enemy_1);
        // }

        // Dinosaur enemy_2 = new Dinosaur(501, getMapTile(6, 8).getLocation());
        // npcs.add(enemy_2);


        //override default enemy depending on enemy flags
        // if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("bugEnemy")) {
        //     enemy = new Bug(502, getMapTile(3, 8).getLocation(), 20, 5);;
        //     enemy.lock();
        //     npcs.set(0, enemy);
        //     PlayLevelScreen.getMap().getFlagManager().unsetFlag("bugEnemy");
        // }

        // if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("shrekEnemy")) {
        //     enemy = new Shrek(503, getMapTile(4,4).getLocation(), 10, 1);
        //     npcs.set(0, enemy);
        //     PlayLevelScreen.getMap().getFlagManager().unsetFlag("shrekEnemy");
        // }

        if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("jvEnemy")) {
            enemy = new CapJV(101, getMapTile(4, 4).getLocation(), 6, 1);
            npcs.set(0,enemy);
            PlayLevelScreen.getMap().getFlagManager().unsetFlag("jvEnemy");
        }

        if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("krakenEnemy")) {
            enemy = new Kraken(801, getMapTile(4, 4).getLocation(), 20, 6);
            npcs.set(0,enemy);
            PlayLevelScreen.getMap().getFlagManager().unsetFlag("krakenEnemy");
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

    //  public static float getEnemyStrength() {
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
