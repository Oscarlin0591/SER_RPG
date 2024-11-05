package Screens;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.SpriteSheet;
import Level.*;
import MapEditor.EditorMaps;
import Maps.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import Players.SpeedBoat;
import Players.SpeedBoatSteve;
import SpriteFont.SpriteFont;
import Utils.Direction;
import Utils.Point;

import javax.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.Random;

// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected static Map map;
    protected static Map prevMap;
    protected static Point playerLoc;
    public Player player;
    protected static SpeedBoat speedBoat;
    protected static SpeedBoatSteve speedBoatSteve;
    private Point prevLoc;
    protected static PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected GameOverScreen gameOverScreen;
    public static BattleScreen battleScreen;
    public static FlagManager flagManager; //chaged to public static from protected
    protected JPanel healthBar;
	private int buttonHover = 0;
    private KeyLocker keyLocker = new KeyLocker();

    private final Key enterKey = Key.E;
    private final Key leftKey = Key.A;
	private final Key rightKey = Key.D;
    private final Key pauseKey = Key.ESC;

    private SpriteFont pauseLabel;
	private SpriteFont quitLabel;
    private SpriteFont profileLabel;
    private SpriteFont profileLabel2;
    private SpriteFont healthLabel;
    private SpriteFont strengthLabel;
    private SpriteFont returnLabel;

    private BufferedImage heart = ImageLoader.load("heart.png");
    private BufferedImage sword = ImageLoader.load("sword.png");
    private BufferedImage steve = ImageLoader.load("CharacterPNGs/steve.png");
    private BufferedImage pauseBackground = ImageLoader.load("pauseBackground.jpg");

    //random encounter vars
    private float spawnInterval;
    private float timeSinceLastBattle = 0;
    Random rand = new Random();

    //private boolean pressedContinue = true;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        spawnInterval = rand.nextInt(10,15);

        //labels are still slightly off + not based fully on screensize, should be handled at some future point
        pauseLabel = new SpriteFont("PAUSED", ScreenManager.getScreenWidth()/2 - 40, ScreenManager.getScreenHeight()/20, "Times New Roman", 24, Color.white);
		pauseLabel.setOutlineColor(Color.black);
		pauseLabel.setOutlineThickness(3f);

        profileLabel = new SpriteFont("Speedboat Steve is a brave adventurer who is sailing across the world. He", ScreenManager.getScreenWidth()/2.25f, ScreenManager.getScreenHeight()/6.5f, "Times New Roman", 20, Color.white);
        profileLabel.setOutlineColor(Color.black);
        profileLabel.setOutlineThickness(2f);

        profileLabel2 = new SpriteFont("must find treasure, and navigate through the ocean. Watch out for enemies!", ScreenManager.getScreenWidth()/2.25f, ScreenManager.getScreenHeight()/5.5f, "Times New Roman", 20, Color.white);
        profileLabel2.setOutlineColor(Color.black);
        profileLabel2.setOutlineThickness(2f);

		quitLabel = new SpriteFont("QUIT GAME", ScreenManager.getScreenWidth()/1.5f, ScreenManager.getScreenHeight()/1.3f, "Times New Roman", 28, Color.white);
		quitLabel.setOutlineColor(Color.black);
		quitLabel.setOutlineThickness(2f);

        returnLabel = new SpriteFont("RETURN", ScreenManager.getScreenWidth()/2f, ScreenManager.getScreenHeight()/1.3f, "Times New Roman", 28, Color.white);
		returnLabel.setOutlineColor(Color.black);
		returnLabel.setOutlineThickness(2f);

        healthLabel = new SpriteFont("HEALTH:", ScreenManager.getScreenWidth()/2.25f, ScreenManager.getScreenHeight()/3.5f, "Times New Roman", 36, Color.white);
        healthLabel.setOutlineColor(Color.black);
        healthLabel.setOutlineThickness(3f);

        strengthLabel = new SpriteFont("STRENGTH: ", ScreenManager.getScreenWidth()/2.25f, ScreenManager.getScreenHeight()/2.3f, "Times New Roman", 36, Color.white);
        strengthLabel.setOutlineColor(Color.black);
        strengthLabel.setOutlineThickness(3f);
    }

    //getter for current map
    public static Map getMap () {
        return map;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundBall", false);
        flagManager.addFlag("jdvdialogue", false);

        // flag for teleportation
        flagManager.addFlag("interactPortal",false);
        flagManager.addFlag("toggleIsland", false);
        flagManager.addFlag("exitIsland", false);
        flagManager.addFlag("toggleCave", false);
        flagManager.addFlag("exitCave", false);
        flagManager.addFlag("toggleAtlantis", false);
        flagManager.addFlag("exitAtlantis", false);
        flagManager.addFlag("toggleArctic", false);
        flagManager.addFlag("exitArctic", false);

        // in combat flag (to be toggled by Enemy NPCs)
        flagManager.addFlag("combatTriggered", false);
        flagManager.addFlag("battleWon", false);
        flagManager.addFlag("battleWonText", false);
        flagManager.addFlag("battleLost", false);
        flagManager.addFlag("battlePanel",false);

        // flag to determine if game is lost
        flagManager.addFlag("gameOver", false);

        // enemy flags
        flagManager.addFlag("shrekEnemy", false);
        flagManager.addFlag("bugEnemy", false);
        flagManager.addFlag("krakenEnemy", false);
        flagManager.addFlag("jvEnemy", false);
        flagManager.addFlag("beetleEnemy", false);

        // boss / enemy kill flags
        flagManager.addFlag("jvBeaten", false);
        flagManager.addFlag("krakenKilled", false);
        flagManager.addFlag("beetleKilled", false);

        // quest / npc progression flags
        flagManager.addFlag("jvSpokenTo", false);

        // item picked up flags
        flagManager.addFlag("startIslandPotion", false);
        flagManager.addFlag("oceanPotion", false);

        // misc flags
        flagManager.addFlag("playerRoided", false);
        flagManager.addFlag("attackDodged", false);

        // define/setup map - may need to replicate for all maps
        int playerContX = 0;
        int playerContY = 0;
        String mapCont = "";
        int playerHealthCont = 0;
        int playerStrengthCont = 0;
        if(MenuScreen.continueState.getPressedContinue()){
            try{
                File saveFile = new File("src/Saves/Save.txt");
                Scanner in = new Scanner(saveFile);
                playerContX = in.nextInt();
                System.out.println(playerContX);
                playerContY = in.nextInt();
                System.out.println(playerContY);
                mapCont = in.next();
                System.out.println(mapCont);
                playerHealthCont = in.nextInt();
                playerStrengthCont = in.nextInt();
                //beaten jv
                if(in.nextBoolean()){
                    flagManager.setFlag("jvBeaten");
                }
                //kraken's existence
                if(in.nextBoolean()){
                    flagManager.setFlag("krakenKilled");
                }
                //beetle defeated
                if(in.nextBoolean()){
                    flagManager.setFlag("beetleKilled");
                }
                in.close();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }

            switch (mapCont) {
                case "game_map.txt":
                    map = new OceanMap();
                    break;
                case "cave_map.txt":
                    map = new CaveMap();
                default:
                    map = new StartIslandMap();
                    break;
            }
        }else{
            map = new StartIslandMap();
        }
        
        map.setFlagManager(flagManager);

        // static players
        // speedBoat = new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y,10,2);
        speedBoatSteve = new SpeedBoatSteve(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y,10,4, 1, 1);
        speedBoat = new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y, speedBoatSteve.getHealth(), speedBoatSteve.getStrength(), speedBoatSteve.getDodgeChance(), speedBoatSteve.getCritChance());

        // setup player
        if(MenuScreen.continueState.getPressedContinue()){
            if(map.getMapFileName().equals("game_map.txt")){
                player = speedBoat;
                // new SpeedBoat(playerContX, playerContY, playerHealthCont, playerStrengthCont);
            }else{
                player = speedBoatSteve;
                // new SpeedBoatSteve(playerContX, playerContY, playerHealthCont, playerStrengthCont);
            }
            player.setLocation(playerContX, playerContY);
            player.setStrength(playerStrengthCont);
            player.setMaxHealth(playerHealthCont);
        }else{
            player = speedBoatSteve;
            // new SpeedBoatSteve(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y,10,2);
        }
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        player.setFacingDirection(Direction.LEFT);
        System.out.println(player.getLocation());
        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // preloads all scripts ahead of time rather than loading them dynamically
        // both are supported, however preloading is recommended
        map.preloadScripts();

        winScreen = new WinScreen(this);
        gameOverScreen = new GameOverScreen(this);
        battleScreen = new BattleScreen(this);
    }
    
    public void update() {
        updatePauseState();

        if(map.getMapFileName().equals("game_map.txt") && !player.getIsLocked()){
            timeSinceLastBattle += .01;
            if (timeSinceLastBattle >= spawnInterval) {
                timeSinceLastBattle = 0;
                spawnInterval = rand.nextInt(10,15);
                flagManager.setFlag("combatTriggered");
            }
        }
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
            case GAME_OVER:
                gameOverScreen.update();
                break;
            case PAUSED:
                //currently same as if running
                player.update();
                map.update(player);
                break;
            case BATTLE:
                battleScreen.update();
            }

        // if flag is set at any point during gameplay, game is "lost"
        if (map.getFlagManager().isFlagSet("gameOver")) {
            GamePanel.combatFinished();
            playLevelScreenState = PlayLevelScreenState.GAME_OVER;
        }
        
        // if flag is set for portal interaction, change map
        if (map.getFlagManager().isFlagSet("interactPortal")) {
            System.out.println("DEBUG: Portal interaction flag checker");
            teleport(EditorMaps.getMapByName(map.getChosenMap()), "interactPortal", new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y, player.getHealth(),player.getStrength(), player.getCritChance(), player.getDodgeChance()));
        }

        // if flag is set for portal interaction, change map
        if (map.getFlagManager().isFlagSet("toggleIsland")) {
            playerLoc = getPlayer().getLocation();
            teleport(new StartIslandMap(), "toggleIsland", speedBoatSteve, new StartIslandMap().getPlayerStartPosition());
        }

        if (map.getFlagManager().isFlagSet("exitIsland")) {
            teleport(new OceanMap(), "exitIsland", speedBoat, new OceanMap().getPlayerStartPosition());
        }
        // if flag is set for cave icon, change to caves
        if (map.getFlagManager().isFlagSet("toggleCave")) {
            playerLoc = getPlayer().getLocation();
            teleport(new CaveMap(), "toggleCave", speedBoatSteve, new CaveMap().getPlayerStartPosition());
        }

        if (map.getFlagManager().isFlagSet("exitCave")) { // figuring out location soon
            teleport(new OceanMap(), "exitCave", speedBoat, prevLoc);
        }

        // if flag is set for atlantis icon, change to atlantis
        if (map.getFlagManager().isFlagSet("toggleAtlantis")) {
            playerLoc = getPlayer().getLocation();
            teleport(new AtlantisMap(), "toggleAtlantis", speedBoatSteve, new AtlantisMap().getPlayerStartPosition());
        }

        if (map.getFlagManager().isFlagSet("exitAtlantis")) {
            playerLoc = getPlayer().getLocation();
            teleport(new OceanMap(), "exitAtlantis", speedBoat,  prevLoc);
        }

        if (map.getFlagManager().isFlagSet("toggleArctic")) {
            playerLoc = getPlayer().getLocation();
            teleport(new ArcticMap(), "toggleArctic", speedBoatSteve, new ArcticMap().getPlayerStartPosition());
        }

        if (map.getFlagManager().isFlagSet("exitArctic")) {
            playerLoc = getPlayer().getLocation();
            teleport(new OceanMap(), "exitArctic", speedBoat,  new Point(prevLoc.x, prevLoc.y-2));
        }

        if (map.getFlagManager().isFlagSet("battlePanel")) {
            battle();
            refreshBattle();
            map.getFlagManager().unsetFlag("battlePanel");
        }

        // if flag is set for being in combat PRINT DEBUG
        if (map.getFlagManager().isFlagSet("combatTriggered")) {
            //add logic to pull up combat menu here 
            prevMap = getMap();
            playerLoc = getPlayer().getLocation();
            // prevPlayer = getPlayer();
            map = new BattleMap();
            map.setFlagManager(flagManager);
            // player = new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y,player.getMaxHealth(),player.getStrength());
            player.setLocation(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
            System.out.print(player.getLocation());
            player.setMap(map);
            playLevelScreenState = PlayLevelScreenState.RUNNING;
            map.setPlayer(player);
            map.getTextbox().setInteractKey(player.getInteractKey());
            map.getFlagManager().unsetFlag("combatTriggered");
            GamePanel.combatTriggered();

        }

        if (map.getFlagManager().isFlagSet("battleWon")) {
            GamePanel.combatFinished();
            switch(prevMap.getMapFileName()) {
                case "cave_map.txt":
                    returnToPrevMap(new CaveMap(), playerLoc, getPlayer());
                    break;
                case "game_map.txt":
                    returnToPrevMap(new OceanMap(), playerLoc, getPlayer());
                    break;
                case "starting_map.txt":
                    returnToPrevMap(new StartIslandMap(), playerLoc, getPlayer());
                    break;
            }
            player.fullHealth();
        }
        
        if (map.getChosenMap() != null) {
            teleport(EditorMaps.getMapByName(map.getChosenMap()), "interactPortal", new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y,player.getHealth(),player.getStrength(), player.getCritChance(), player.getDodgeChance()));
            map.setChosenMap(null);
        }
    }

    private void updatePauseState() {
		if (Keyboard.isKeyDown(pauseKey) && !keyLocker.isKeyLocked(pauseKey)) {
            //if screen currently running, it is now paused
            if (playLevelScreenState == PlayLevelScreenState.RUNNING) {
                playLevelScreenState = PlayLevelScreenState.PAUSED;

                //lock player movement
                getPlayer().lock();

                //lock npc movement - seems to only lock movement, not animations, so we will want to add that as well later on (possibly by updating NPC.lock() method)
                for (NPC npc : getMap().getNPCs()) {
                    npc.lock();
                }
            }
            //if screen currently paused, it is now running
            else if (playLevelScreenState == PlayLevelScreenState.PAUSED) {
                playLevelScreenState = PlayLevelScreenState.RUNNING;
                System.out.println("DEBUG: UNLOCKED");

                //unlock player movement
                getPlayer().unlock();

                //unlock npc movement
                for (NPC npc : getMap().getNPCs()) {
                    npc.unlock();
                }
            }
		}

        keyLocker.lockKey(pauseKey);
        
		if(Keyboard.isKeyDown(leftKey) && buttonHover > 0){
			buttonHover--;
		}

		if(Keyboard.isKeyDown(rightKey) && buttonHover < 1){
			buttonHover++;
		}

		if(Keyboard.isKeyDown(enterKey) && playLevelScreenState == PlayLevelScreenState.PAUSED){
			switch (buttonHover) {
				case 1:
					// Write to save file
                    //IMPORTANT: DO NOT UNCOMMENT, COULD LEAD TO MULTIPLE MERGE CONFLICTS!
					try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Saves/Save.txt"))) {
	        		    writer.write("" + (int)player.getX());
                        writer.write("\n" + (int)player.getY());
                        writer.write("\n" + map.getMapFileName());
                        writer.write("\n" + (int)player.getHealth());
                        writer.write("\n" + (int)player.getStrength());
                        writer.write("\n" + flagManager.isFlagSet("jvBeaten"));
                        writer.write("\n" + flagManager.isFlagSet("krakenKilled"));
                        writer.write("\n" + flagManager.isFlagSet("beetleKilled"));
                        //writer.write("\n" + flagManager.print());
    			    } catch (IOException e) {
        			    e.printStackTrace();
        			}
					System.exit(0);
					break;
				default:
					playLevelScreenState = PlayLevelScreenState.RUNNING; //may run into issues if unpausing while game state was not previously running, but dont think thats possible anyways
					
                    //unlock player movement
                    this.getPlayer().unlock();
                    break;
			}
			
		}

		if (Keyboard.isKeyUp(pauseKey)) {
			keyLocker.unlockKey(pauseKey);
		}
	}
    
    // methods to switch map

    public void returnToPrevMap(Map newMap, Point prevLoc, Player newPlayer) {
        map = newMap;
        map.setFlagManager(flagManager);
        player = newPlayer;
        player.setMap(map);
        player.setLocation(prevLoc.x,prevLoc.y);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        map.setPlayer(player);
        player.unlock();
        map.getTextbox().setInteractKey(player.getInteractKey());
        map.getFlagManager().unsetFlag("battleWon");
        map.getFlagManager().unsetFlag("battleWonText");
        map.getFlagManager().unsetFlag("battleLost");
        
    }

    public void teleport(Map newMap, String flag, Player newPlayer) {
        playerLoc = getPlayer().getLocation();
        map.getFlagManager().unsetFlag(flag);
        map = newMap;
        map.setFlagManager(flagManager);
        player = newPlayer;
        player.setLocation(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        map.setPlayer(player);
        map.getTextbox().setInteractKey(player.getInteractKey());
    }
    
    public void teleport(Map newMap, String flag, Player newPlayer, Point location) {
        float playerHealth = getPlayer().getHealth();
        float playerMaxHealth = getPlayer().getMaxHealth();
        float playerStrength = getPlayer().getStrength();

        prevLoc = getPlayer().getLocation();
        map.getFlagManager().unsetFlag(flag);
        map = newMap;
        map.setFlagManager(flagManager);
        player = newPlayer;
        player.setHealth(playerHealth);
        player.setMaxHealth(playerMaxHealth);
        player.setStrength(playerStrength);
        player.setLocation(location.x, location.y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        map.setPlayer(player);
        map.getTextbox().setInteractKey(player.getInteractKey());
        player.unlock();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                //player and game
                map.draw(player, graphicsHandler);

                //veith npc visual
                if (getMap().getFlagManager().isFlagSet("jdvdialogue"))
                    graphicsHandler.drawImage(ImageLoader.load("CharacterPNGs/Captain_Jack_Veith.png"), ScreenManager.getScreenWidth()-400, Textbox.getOptionBottomY()-400, 400, 400);

                //health bars in combat - moved from GamePanel
                if (GamePanel.isInBattle) {
                    GamePanel.updateHealthInfo();

                    //draw empty health bars behind health
                    graphicsHandler.drawFilledRectangleWithBorder(585, 525, 200, 75, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 2);
                    graphicsHandler.drawFilledRectangleWithBorder(40, 525, 200, 75, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 2);

                    //draw filled health bar at health percentage
                    int playerHealthPercent = Math.round((player.getHealth() / player.getMaxHealth()) * 200);
			        graphicsHandler.drawFilledRectangleWithBorder(585, 525, playerHealthPercent, 75, Color.RED, Color.LIGHT_GRAY, 2);
			        GamePanel.healthLabel.draw(graphicsHandler);

                    int enemyHealthPercent = Math.round((BattleMap.enemy.getHealth() / BattleMap.enemy.getMaxHealth()) * 200);
			        graphicsHandler.drawFilledRectangleWithBorder(40, 525, enemyHealthPercent, 75, Color.RED, Color.LIGHT_GRAY, 2);
			        GamePanel.enemyHealthLabel.draw(graphicsHandler);
			    }
                break;
            case LEVEL_COMPLETED:
                winScreen.draw(graphicsHandler);
                break;
            case GAME_OVER:
                gameOverScreen.draw(graphicsHandler);
                break;
            case BATTLE:
                map.draw(player, graphicsHandler);
                battleScreen.draw(graphicsHandler);
                break;
            case PAUSED:
                int currentHealth = Math.round(player.getHealth());
                int currentStrength = Math.round(player.getStrength());
                int hearts = currentHealth/10;
                int swords = currentStrength/2;
                int heartXPos = Math.round(ScreenManager.getScreenWidth()/2.25f);
                int heartYPos = ScreenManager.getScreenHeight()/3;
                int swordXPos = Math.round(ScreenManager.getScreenWidth()/2.25f);
                int swordYPos = ScreenManager.getScreenHeight()/2;

                int backgroundEdge = ScreenManager.getScreenWidth()/8+ScreenManager.getScreenWidth()-(ScreenManager.getScreenWidth()/8*2);
                //still draw map
                map.draw(player, graphicsHandler);

                //draw pause menu overtop map
                graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), new Color(0, 0, 0, 200));                
           
                graphicsHandler.drawImage(pauseBackground,ScreenManager.getScreenWidth()/8, ScreenManager.getScreenHeight()/8, ScreenManager.getScreenWidth()-(ScreenManager.getScreenWidth()/8*2),ScreenManager.getScreenHeight()-(ScreenManager.getScreenHeight()/8*2));
            
                graphicsHandler.drawImage(steve, ScreenManager.getScreenWidth()/6, ScreenManager.getScreenHeight()/6, ScreenManager.getScreenWidth()/4, ScreenManager.getScreenHeight()/2+ScreenManager.getScreenHeight()/7);
               
                
                if (buttonHover == 0){
                    graphicsHandler.drawFilledRectangle(ScreenManager.getScreenWidth()/2-8, ScreenManager.getScreenHeight()/2+220, 130,80, Color.BLACK);
                }else{
                    graphicsHandler.drawFilledRectangle(ScreenManager.getScreenWidth()/2+235, ScreenManager.getScreenHeight()/2+220,170,80,Color.BLACK);
                }

                for(int i = 0; i < hearts; i++) {
                    graphicsHandler.drawImage(heart, heartXPos, heartYPos,50,50);
                    heartXPos += 55;
                    if (heartXPos > backgroundEdge - 55) {
                        heartXPos = Math.round(ScreenManager.getScreenWidth()/2.25f);
                        heartYPos += 55;
                    }
                }

                for(int i = 0; i <= swords+1; i++) {
                    graphicsHandler.drawImage(sword, swordXPos, swordYPos,50,50);
                    if (swordXPos > backgroundEdge - 70) {
                        swordXPos = Math.round(ScreenManager.getScreenWidth()/2.25f);
                        swordYPos += 55;
                    }
                    swordXPos += 55;
                }
                
                profileLabel.draw(graphicsHandler);
                profileLabel2.draw(graphicsHandler);
                quitLabel.draw(graphicsHandler);
                pauseLabel.draw(graphicsHandler);
                returnLabel.draw(graphicsHandler);
                healthLabel.draw(graphicsHandler);
                strengthLabel.draw(graphicsHandler);


        }
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    public static void setPlayLevelScreenState(PlayLevelScreenState state) {
        playLevelScreenState = state;
    }

    // game over screen
    public void gameOver() {
        playLevelScreenState = PlayLevelScreenState.GAME_OVER;
    }

    public static void battle() {
        setPlayLevelScreenState(PlayLevelScreenState.BATTLE);
    }

    public void refreshBattle() {
        battleScreen = new BattleScreen(this);
    }

    public static void running() {
        setPlayLevelScreenState(PlayLevelScreenState.RUNNING);
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    public Player getPlayer() {
        return this.player;
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED, GAME_OVER, PAUSED, BATTLE
    }
}
