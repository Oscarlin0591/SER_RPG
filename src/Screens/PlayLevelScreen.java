package Screens;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Engine.ScreenManager;
import Game.GameState;
import Game.ScreenCoordinator;
import Engine.GamePanel;
import Level.*;
import MapEditor.EditorMaps;
import Maps.StartIslandMap;
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
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected GameOverScreen gameOverScreen;
    public static FlagManager flagManager; //chaged to public static from protected
    protected JPanel healthBar;

    //The many many pause screen variables
    // private boolean isGamePaused = false; - converted to PlayLevelScreenState.PAUSED
	private SpriteFont pauseLabel;
    private int PAUSE_MENU_WIDTH = 300;
	private int PAUSE_MENU_HEIGHT = 400;
	private int PAUSE_BUTTON_WIDTH = 200;
	private int PAUSE_BUTTON_HEIGHT = 100;
	private int HIGHLIGHT_MARGIN = 10;
	private int HIGHLIGHT_WIDTH = PAUSE_BUTTON_WIDTH + 2*HIGHLIGHT_MARGIN;
	private int HIGHLIGHT_HEIGHT = PAUSE_BUTTON_HEIGHT + 2*HIGHLIGHT_MARGIN;
	private SpriteFont quitLabel;
	private SpriteFont returnLabel;
	private final Key enterKey = Key.E;
	private int buttonHover = 0;
	private final Key upKey = Key.W;
	private final Key downKey = Key.S;
    private KeyLocker keyLocker = new KeyLocker();
    private final Key pauseKey = Key.ESC;

    //random encounter vars
    private float spawnInterval;
    private float timeSinceLastBattle = 0;
    Random rand = new Random();

    //private boolean pressedContinue = true;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;

        //labels are still slightly off + not based fully on screensize, should be handled at some future point
        pauseLabel = new SpriteFont("PAUSE", ScreenManager.getScreenWidth()/2 - 40, ScreenManager.getScreenHeight()/2 - 175, "Arial", 24, Color.white);
		pauseLabel.setOutlineColor(Color.black);
		pauseLabel.setOutlineThickness(2.0f);

		quitLabel = new SpriteFont("Quit Game", ScreenManager.getScreenWidth()/2 - 40, ScreenManager.getScreenHeight()/2 + 75, "Arial", 24, Color.white);
		quitLabel.setOutlineColor(Color.black);
		quitLabel.setOutlineThickness(2.0f);

		returnLabel = new SpriteFont("Return", ScreenManager.getScreenWidth()/2 - 40, ScreenManager.getScreenHeight()/2 - 40, "Arial", 24, Color.white);
		returnLabel.setOutlineColor(Color.black);
		returnLabel.setOutlineThickness(2.0f);

        spawnInterval = rand.nextInt(10,15);
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
        flagManager.addFlag("toggleCave", false);

        // in combat flag (to be toggled by Enemy NPCs)
        flagManager.addFlag("combatTriggered", false);
        flagManager.addFlag("battleWon", false);

        // flag to determine if game is lost
        flagManager.addFlag("gameOver", false);

        // enemy flags (test)
        flagManager.addFlag("shrekEnemy", false);
        flagManager.addFlag("bugEnemy", false);
        flagManager.addFlag("krakenEnemy", false);
        flagManager.addFlag("jvEnemy", false);

        // player upgrade flags
        flagManager.addFlag("playerRoided", false);

        // boss / enemy kill flags
        flagManager.addFlag("jvBeaten", false);
        flagManager.addFlag("krakenKilled", false);

        // define/setup map - may need to replicate for all maps
        int playerContX = 0;
        int playerContY = 0;
        String mapCont = "";
        if(MenuScreen.continueState.getPressedContinue()){
            try{
                File saveFile = new File("src/Saves/Save.txt");
                Scanner in = new Scanner(saveFile);
                playerContX = in.nextInt();
                //System.out.println(playerContX);
                playerContY = in.nextInt();
                //System.out.println(playerContY);
                mapCont = in.next();
                //System.out.println(mapCont);
                in.close();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }

            switch (mapCont) {
                case "game_map.txt":
                    map = new OceanMap();
                    break;
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
            player = new SpeedBoatSteve(playerContX, playerContY);
        }else{
            player = new SpeedBoatSteve(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
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
            teleport(new BattleMap(), "interactPortal", new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y));
        }

        // if flag is set for portal interaction, change map
        if (map.getFlagManager().isFlagSet("toggleIsland")) {
            teleport(new StartIslandMap(), "toggleIsland", new SpeedBoatSteve(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y));
        }

        // if flag is set for cave icon, change to caves
        if (map.getFlagManager().isFlagSet("toggleCave")) {
            teleport(new CaveMap(), "toggleCave", new SpeedBoatSteve(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y));
        }

        // if flag is set for being in combat PRINT DEBUG
        if (map.getFlagManager().isFlagSet("combatTriggered")) {
            //add logic to pull up combat menu here 
            System.out.println("DEBUG Combat Flag Works");

            prevMap = getMap();
            playerLoc = getPlayer().getLocation();
            map = new BattleMap();
            map.setFlagManager(flagManager);
            player = new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
            System.out.print(player.getLocation());
            player.setMap(map);
            playLevelScreenState = PlayLevelScreenState.RUNNING;
            map.setPlayer(player);
            map.getTextbox().setInteractKey(player.getInteractKey());
            map.getFlagManager().unsetFlag("combatTriggered");
            System.out.println(player.getHealth());
            GamePanel.combatTriggered();

        }

        if (map.getFlagManager().isFlagSet("battleWon")) {
            System.out.println("Batton won method triggered");
            GamePanel.combatFinished();
            returnToPrevMap(prevMap, playerLoc);
        }

        if (map.getChosenMap() != null) {
            teleport(EditorMaps.getMapByName(map.getChosenMap()), "interactPortal", new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y));
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
            
			keyLocker.lockKey(pauseKey);
		}
        
		if(Keyboard.isKeyDown(upKey) && buttonHover > 0){
			buttonHover--;
		}

		if(Keyboard.isKeyDown(downKey) && buttonHover < 1){
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

    public void returnToPrevMap(Map prevMap, Point prevLoc) {
        System.out.println("DEBUG: trying to initialize map");

        map = prevMap;
        map.setFlagManager(flagManager);
        player = new SpeedBoatSteve(prevLoc.x, prevLoc.y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        map.setPlayer(player);
        map.getTextbox().setInteractKey(player.getInteractKey());
        map.getFlagManager().unsetFlag("battleWon");
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
                //still draw map
                map.draw(player, graphicsHandler);

                //draw pause menu overtop map
                graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), new Color(0, 0, 0, 100));
                graphicsHandler.drawFilledRectangle(ScreenManager.getScreenWidth()/2 - PAUSE_MENU_WIDTH/2, ScreenManager.getScreenHeight()/2 - PAUSE_MENU_HEIGHT/2, PAUSE_MENU_WIDTH, PAUSE_MENU_HEIGHT, new Color(255,255,255));
                if(buttonHover == 0){
                    graphicsHandler.drawFilledRectangle(ScreenManager.getScreenWidth()/2 - HIGHLIGHT_WIDTH/2, ScreenManager.getScreenHeight()/2 - HIGHLIGHT_HEIGHT + HIGHLIGHT_MARGIN, HIGHLIGHT_WIDTH, HIGHLIGHT_HEIGHT, Color.DARK_GRAY);
                }else{
                    graphicsHandler.drawFilledRectangle(ScreenManager.getScreenWidth()/2 - HIGHLIGHT_WIDTH/2, ScreenManager.getScreenHeight()/2 + HIGHLIGHT_HEIGHT/2 - 2*HIGHLIGHT_MARGIN, HIGHLIGHT_WIDTH, HIGHLIGHT_HEIGHT, Color.DARK_GRAY);
                }
                graphicsHandler.drawFilledRectangle(ScreenManager.getScreenWidth()/2 - PAUSE_BUTTON_WIDTH/2, ScreenManager.getScreenHeight()/2 + PAUSE_BUTTON_HEIGHT/2, PAUSE_BUTTON_WIDTH, PAUSE_BUTTON_HEIGHT, Color.LIGHT_GRAY);
                graphicsHandler.drawFilledRectangle(ScreenManager.getScreenWidth()/2 - PAUSE_BUTTON_WIDTH/2, ScreenManager.getScreenHeight()/2 - PAUSE_BUTTON_HEIGHT, PAUSE_BUTTON_WIDTH, PAUSE_BUTTON_HEIGHT, Color.LIGHT_GRAY);
                quitLabel.draw(graphicsHandler);
                pauseLabel.draw(graphicsHandler);
                returnLabel.draw(graphicsHandler);
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
