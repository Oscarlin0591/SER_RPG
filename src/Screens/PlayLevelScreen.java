package Screens;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Engine.ScreenManager;
import Engine.ImageLoader;
import Game.GameState;
import Game.ScreenCoordinator;
import Engine.GamePanel;
import Level.*;
import MapEditor.EditorMaps;
import Maps.StartIslandMap;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//import NPCs.Shrek;
import Maps.OceanMap;
import Maps.BattleMap;
import Maps.CaveMap;
import Players.SpeedBoat;
import Players.SpeedBoatSteve;
import SpriteFont.SpriteFont;
import Utils.Direction;
import Utils.Point;

import javax.swing.JPanel;
// import javax.swing.JLabel;
// import java.awt.GridLayout;
// import java.awt.Color;
// import java.awt.Dimension;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.Random;

// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected static Map map;
    protected static Map prevMap;
    protected static Utils.Point playerLoc;
    public Player player;
    private Player prevPlayer;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected GameOverScreen gameOverScreen;
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
    private BufferedImage steve = ImageLoader.load("steve.png");
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

        // flag for teleportation
        flagManager.addFlag("interactPortal",false);
        flagManager.addFlag("toggleIsland", false);
        flagManager.addFlag("exitIsland", false);
        flagManager.addFlag("toggleCave", false);
        flagManager.addFlag("exitCave", false);

        // in combat flag (to be toggled by Enemy NPCs)
        flagManager.addFlag("combatTriggered", false);
        flagManager.addFlag("battleWon", false);
        flagManager.addFlag("battleWonText", false);
        flagManager.addFlag("battleLost", false);

        // flag to determine if game is lost
        flagManager.addFlag("gameOver", false);

        // enemy flags (test)
        flagManager.addFlag("shrekEnemy", false);
        flagManager.addFlag("bugEnemy", false);
        flagManager.addFlag("krakenEnemy", false);
        flagManager.addFlag("jvEnemy", false);
        flagManager.addFlag("beetleEnemy", false);

        // player upgrade flags
        flagManager.addFlag("playerRoided", false);

        // boss / enemy kill flags
        flagManager.addFlag("jvBeaten", false);
        flagManager.addFlag("krakenKilled", false);
        flagManager.addFlag("beetleKilled", false);

        // quest / npc progression flags
        flagManager.addFlag("jvSpokenTo", false);

        // item picked up flags
        flagManager.addFlag("startIslandPotion", false);
        flagManager.addFlag("oceanPotion", false);

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

        // setup player
        if(MenuScreen.continueState.getPressedContinue()){
            if(map.getMapFileName().equals("game_map.txt")){
                player = new SpeedBoat(playerContX, playerContY, playerHealthCont, playerStrengthCont);
            }else{
                player = new SpeedBoatSteve(playerContX, playerContY, playerHealthCont, playerStrengthCont);
            }
        }else{
            player = new SpeedBoatSteve(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y,10,2);
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
            }

        // if flag is set at any point during gameplay, game is "lost"
        if (map.getFlagManager().isFlagSet("gameOver")) {
            GamePanel.combatFinished();
            playLevelScreenState = PlayLevelScreenState.GAME_OVER;
        }
        
        // if flag is set for portal interaction, change map
        if (map.getFlagManager().isFlagSet("interactPortal")) {
            System.out.println("DEBUG: Portal interaction flag checker");
            teleport(EditorMaps.getMapByName(map.getChosenMap()), "interactPortal", new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y, player.getHealth(),player.getStrength()));
        }

        // if flag is set for portal interaction, change map
        if (map.getFlagManager().isFlagSet("toggleIsland")) {
            playerLoc = getPlayer().getLocation();
            teleport(new StartIslandMap(), "toggleIsland", new SpeedBoatSteve(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y,player.getHealth(),player.getStrength()));
        }

        if (map.getFlagManager().isFlagSet("exitIsland")) {
            teleport(new OceanMap(), "exitIsland", new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y,player.getHealth(),player.getStrength()));
        }
        // if flag is set for cave icon, change to caves
        if (map.getFlagManager().isFlagSet("toggleCave")) {
            playerLoc = getPlayer().getLocation();
            teleport(new CaveMap(), "toggleCave", new SpeedBoatSteve(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y,player.getHealth(),player.getStrength()));
        }

        if (map.getFlagManager().isFlagSet("exitCave")) { // figuring out location soon
            teleport(new OceanMap(), "exitCave", new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y,player.getHealth(),player.getStrength()));
        }

        // if flag is set for being in combat PRINT DEBUG
        if (map.getFlagManager().isFlagSet("combatTriggered")) {
            //add logic to pull up combat menu here 
            System.out.println("DEBUG Combat Flag Works");

            prevMap = getMap();
            playerLoc = getPlayer().getLocation();
            prevPlayer = getPlayer();
            map = new BattleMap();
            map.setFlagManager(flagManager);
            player = new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y,player.getHealth(),player.getStrength());
            System.out.print(player.getLocation());
            player.setMap(map);
            playLevelScreenState = PlayLevelScreenState.RUNNING;
            map.setPlayer(player);
            map.getTextbox().setInteractKey(player.getInteractKey());
            map.getFlagManager().unsetFlag("combatTriggered");
            GamePanel.combatTriggered();

        }

        if (map.getFlagManager().isFlagSet("battleWon")) {
            System.out.println("Batton won method triggered");
            GamePanel.combatFinished();
            switch(prevMap.getMapFileName()) {
                case "cave_map.txt":
                    returnToPrevMap(new CaveMap(), playerLoc, prevPlayer);
                    break;
                case "game_map.txt":
                    returnToPrevMap(new OceanMap(), playerLoc, prevPlayer);
                    break;
                case "starting_map.txt":
                    returnToPrevMap(new StartIslandMap(), playerLoc, prevPlayer);
                    break;

            }
            
        }
        
        if (map.getChosenMap() != null) {
            teleport(EditorMaps.getMapByName(map.getChosenMap()), "interactPortal", new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y,player.getHealth(),player.getStrength()));
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
    
    // methods to switch map, pending overhaul to shorten code.

    public void returnToPrevMap(Map newMap, Point prevLoc, Player newPlayer) {
        
        map = newMap;
        map.setFlagManager(flagManager);
        player = newPlayer;
        player.setMap(map);
        player.setLocation(prevLoc.x,prevLoc.y);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        map.setPlayer(player);
        map.getTextbox().setInteractKey(player.getInteractKey());
        map.getFlagManager().unsetFlag("battleWon");
        map.getFlagManager().unsetFlag("battleWonText");
        map.getFlagManager().unsetFlag("battleLost");
        
    }

    public void teleport(Map newMap, String flag, Player newPlayer) {
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

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                winScreen.draw(graphicsHandler);
                break;
            case GAME_OVER:
                gameOverScreen.draw(graphicsHandler);
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

    public void setPlayLevelScreenState(PlayLevelScreenState state) {
        playLevelScreenState = state;
    }

    // game over screen
    public void gameOver() {
        playLevelScreenState = PlayLevelScreenState.GAME_OVER;
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
        RUNNING, LEVEL_COMPLETED, GAME_OVER, PAUSED
    }
}
