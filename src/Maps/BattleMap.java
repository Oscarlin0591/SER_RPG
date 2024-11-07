package Maps;

import Level.*;
import NPCs.*;
import NPCs.Bosses.*;
import Screens.PlayLevelScreen;
import Scripts.StartIslandMap.*;
import Tilesets.BattleMapTileset;
import Tilesets.RPGTileset;
import Utils.Direction;

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
        super("battle_map.txt", new BattleMapTileset());
        this.playerStartPosition = getMapTile(20,12).getLocation();
    }

    public BattleMap(Enemy newEnemy) {
        super("battle_map.txt", new BattleMapTileset());
        this.playerStartPosition = getMapTile(20,12).getLocation();
        enemy = newEnemy;
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
                enemy = new Bug(999, getMapTile(8,12).getLocation(), 5, 5, 1, 1);
                break;
            case 1:
                enemy = new Shrek(503, getMapTile(8,12).getLocation(), 10, 1, 1, 1);
                break;
            default:
            break;
        }
        npcs.add(enemy);

        //override default enemy depending on enemy flags
        if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("bugEnemy")) {
            enemy = new Bug(502, getMapTile(8,12).getLocation(), 10, 1, 1, 1);
            enemy.stand(Direction.RIGHT);
            enemy.lock();
            npcs.set(0, enemy);
            PlayLevelScreen.getMap().getFlagManager().unsetFlag("bugEnemy");
        }

        if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("jvEnemy")) {
            enemy = new CapJV(101, getMapTile(8,12).getLocation(), 6, 1, 1, 1);
            npcs.set(0,enemy);
            PlayLevelScreen.getMap().getFlagManager().unsetFlag("jvEnemy");
        }

        if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("krakenEnemy")) {
            enemy = new Kraken(801, getMapTile(8,12).getLocation(), 20, 6, 1, 1);
            npcs.set(0,enemy);
            PlayLevelScreen.getMap().getFlagManager().unsetFlag("krakenEnemy");
        }

        if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("beetleEnemy")) {
            enemy = new HolyBeetle(802, getMapTile(8,12).getLocation(), 50, 5, 1, 1);
            npcs.set(0,enemy);
            PlayLevelScreen.getMap().getFlagManager().unsetFlag("beetleEnemy");
        }

        if(PlayLevelScreen.getMap().getFlagManager().isFlagSet("yetiEnemy")) {
            enemy = new Yeti(802, getMapTile(8,12).getLocation(), 100, 8, 1, 1);
            npcs.set(0,enemy);
            PlayLevelScreen.getMap().getFlagManager().unsetFlag("yetiEnemy");
        }

        if(PlayLevelScreen.getMap().getFlagManager().isFlagSet("krampusEnemy")) {
            enemy = new Krampus(802, getMapTile(8,12).getLocation(), 150, 10, 1, 1);
            npcs.set(0,enemy);
            PlayLevelScreen.getMap().getFlagManager().unsetFlag("krampusEnemy");
        }

        enemy.stand(Direction.RIGHT);

        return npcs;
    }


    public static Enemy getEnemy() {
        return enemy;
    }

    // public static void enemyHurtAnim() {
    //     float enemyX = BattleMap.getEnemy().getX();
    //     float enemyY = BattleMap.getEnemy().getY();

    //     for (int count = 200; count > 0; count--) {
    //         if(count%5==0) {
    //             BattleMap.getEnemy().setLocation(enemyX-5, enemyY);
    //             int newCount = 100;
    //             while (newCount>0) {
    //                 newCount--;
    //             }
    //             BattleMap.getEnemy().setLocation(enemyX+5, enemyY);
    //         }
    //     }
    //     BattleMap.getEnemy().setLocation(enemyX, enemyY);
    // }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(20,12).getLocation().x-200, getMapTile(20,12).getLocation().y-150, 400, 300, new BattleScript(), "battleWon"));
        return triggers;
    }

    @Override
    public void loadMusic() {
        Random rand = new Random();
        int randMusic = rand.nextInt(4);
        switch (randMusic) {
            case 1:
                Music.playMusic("Music/Fighting The Invisible.wav");
                break;
            case 2:
                Music.playMusic("Music/Splash Thunder.wav");
                break;
            default:
                Music.playMusic("Music/BossThemeOne16bit.wav");
                break;
        }
    }
}
