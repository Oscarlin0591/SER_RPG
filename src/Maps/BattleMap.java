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


public class BattleMap extends Map{
    public static Enemy enemy;
    protected boolean beetle = false;
    protected boolean krampus = false;
    protected boolean boss = false;
    protected boolean kraken = false;
    protected boolean capricorn = false;

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
            kraken = true;
            PlayLevelScreen.getMap().getFlagManager().unsetFlag("krakenEnemy");
        }

        if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("beetleEnemy")) {
            enemy = new HolyBeetle(802, getMapTile(8,12).getLocation(), 50, 5, 1, 1);
            npcs.set(0,enemy);
            beetle = true;
            PlayLevelScreen.getMap().getFlagManager().unsetFlag("beetleEnemy");
        }

        if(PlayLevelScreen.getMap().getFlagManager().isFlagSet("yetiEnemy")) {
            enemy = new Yeti(803, getMapTile(8,12).getLocation(), 100, 8, 1, 1);
            npcs.set(0,enemy);
            PlayLevelScreen.getMap().getFlagManager().unsetFlag("yetiEnemy");
        }

        if(PlayLevelScreen.getMap().getFlagManager().isFlagSet("krampusEnemy")) {
            enemy = new Krampus(804, getMapTile(8,12).getLocation(), 150, 10, 1, 1);
            npcs.set(0,enemy);
            krampus = true;
            PlayLevelScreen.getMap().getFlagManager().unsetFlag("krampusEnemy");
        }

        if(PlayLevelScreen.getMap().getFlagManager().isFlagSet("finalBoss")) {
            enemy = new GoldenShip(805, getMapTile(8,12).getLocation(), 200, 15, 1, 1);
            boss = true;
            npcs.set(0, enemy);
        }

        enemy.stand(Direction.RIGHT);

        return npcs;
    }


    public static Enemy getEnemy() {
        return enemy;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(20,12).getLocation().x-200, getMapTile(20,12).getLocation().y-150, 400, 300, new BattleScript(), "battleWon"));
        return triggers;
    }

    @Override
    public void loadMusic() {
        // if (beetle == true) {
        //     Music.playMusic("Music/Cave Boss.wav");
        //     beetle = false;
        // } else {
        //     Random rand = new Random();
        // int randMusic = rand.nextInt(4);
        //     switch (randMusic) {
        //         case 1:
        //             Music.playMusic("Music/Fighting The Invisible.wav");
        //             break;
        //         case 2:
        //             Music.playMusic("Music/Splash Thunder.wav");
        //             break;
        //         default:
        //             Music.playMusic("Music/BossThemeOne16bit.wav");
        //             break;
        //     }
        // }
        
    }
}
