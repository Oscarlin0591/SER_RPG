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
import Maps.StartIslandMap;
import NPCs.Shrek;
import Maps.OceanMap;
import Maps.BattleMap;
import Players.SpeedBoat;
import SpriteFont.SpriteFont;
import Utils.Direction;
import Utils.Point;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;

// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected static Map map;
    public Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected GameOverScreen gameOverScreen;
    protected FlagManager flagManager;
    protected JPanel healthBar;

    //The many many pause screen variables
    private boolean isGamePaused = false;
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
	private final Key enterKey = Key.SPACE;
	private int buttonHover = 0;
	private final Key upKey = Key.UP;
	private final Key downKey = Key.DOWN;
    private KeyLocker keyLocker = new KeyLocker();
    private final Key pauseKey = Key.ESC;


    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        
        pauseLabel = new SpriteFont("PAUSE", 350, 100, "Arial", 24, Color.white);
		pauseLabel.setOutlineColor(Color.black);
		pauseLabel.setOutlineThickness(2.0f);

		quitLabel = new SpriteFont("Quit Game", 325, 375, "Arial", 24, Color.white);
		quitLabel.setOutlineColor(Color.black);
		quitLabel.setOutlineThickness(2.0f);

		returnLabel = new SpriteFont("Return", 325, 215, "Arial", 24, Color.white);
		returnLabel.setOutlineColor(Color.black);
		returnLabel.setOutlineThickness(2.0f);
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
        flagManager.addFlag("interactPortal",false);
        flagManager.addFlag("toggleIsland", false);
        // in combat flag (to be toggled by Enemy NPCs)
        flagManager.addFlag("combatTriggered", false);
        flagManager.addFlag("battleWon", false);
        // flag to determine if game is lost
        flagManager.addFlag("gameOver", false);
        // enemy flags (test)
        flagManager.addFlag("shrekEnemy", false);

        // define/setup map - may need to replicate for all maps
        map = new StartIslandMap();
        map.setFlagManager(flagManager);

        // setup player
        player = new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
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

        // battleGUI();

        
        winScreen = new WinScreen(this);
        gameOverScreen = new GameOverScreen(this);
    }
    
    public void update() {
        updatePauseState();
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
            }
            
            // if flag is set at any point during gameplay, game is "won"
        if (map.getFlagManager().isFlagSet("hasFoundBall")) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
        }

        // if flag is set at any point during gameplay, game is "lost"
        if (map.getFlagManager().isFlagSet("gameOver")) {
            playLevelScreenState = PlayLevelScreenState.GAME_OVER;
        }
        
        // if flag is set for portal interaction, change map
        if (map.getFlagManager().isFlagSet("interactPortal")) {
            System.out.println("DEBUG: Portal interaction flag checker");
            setLocationOceanMap();
        }

        // if flag is set for portal interaction, change map
        if (map.getFlagManager().isFlagSet("toggleIsland")) {
            System.out.println("DEBUG: Island interaction flag checker");
            setLocationStartIslandMap();
        }

        // if flag is set for being in combat PRINT DEBUG
        if (map.getFlagManager().isFlagSet("combatTriggered")) {
            //add logic to pull up combat menu here 
            System.out.println("DEBUG Combat Flag Works");

            // if (map.getFlagManager().isFlagSet("shrekEnemy")) {
            //     map = new BattleMap(new Shrek(503, new Point(12,6), 10, 1));
            // } else {
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
            GamePanel.combatTriggered(player.getHealth());

        }
        if (map.getFlagManager().isFlagSet("battleWon")) {
            System.out.println("Batton won method triggered");
            GamePanel.combatFinished();
            returnToIslandMap();
        }
    }

    private void updatePauseState() {
		if (Keyboard.isKeyDown(pauseKey) && !keyLocker.isKeyLocked(pauseKey)) {
			isGamePaused = !isGamePaused;
			keyLocker.lockKey(pauseKey);
		}
        
		if(Keyboard.isKeyDown(upKey) && buttonHover > 0){
			buttonHover--;
		}

		if(Keyboard.isKeyDown(downKey) && buttonHover < 1){
			buttonHover++;
		}

		if(Keyboard.isKeyDown(enterKey) && isGamePaused){
			switch (buttonHover) {
				case 1:
					// Write to save file
                    //IMPORTANT: DO NOT UNCOMMENT, COULD LEAD TO MULTIPLE MERGE CONFLICTS!
					/*try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Saves/Save.txt"))) {
	        		    writer.write("" + (int)player.getX());
                        writer.write("\n" + (int)player.getY());
                        writer.write("\n" + map.toString());

    			    } catch (IOException e) {
        			    e.printStackTrace();
        			}*/
					System.exit(0);
					break;
				default:
					isGamePaused = false;
					break;
			}
			
		}

		if (Keyboard.isKeyUp(pauseKey)) {
			keyLocker.unlockKey(pauseKey);
		}
	}
    
    // methods to switch map, pending overhaul to shorten code.
    public void setLocationOceanMap() {
        map.getFlagManager().unsetFlag("interactPortal");
        map = new OceanMap();
        map.setFlagManager(flagManager);
        player = new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        map.setPlayer(player);
        map.getTextbox().setInteractKey(player.getInteractKey());
    }

    public void setLocationStartIslandMap() {
        map.getFlagManager().unsetFlag("toggleIsland");
        map = new StartIslandMap();
        map.setFlagManager(flagManager);
        player = new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        map.setPlayer(player);
        map.getTextbox().setInteractKey(player.getInteractKey());
    }

    public void returnToIslandMap() {
        System.out.println("DEBUG: trying to initialize map");
        map = new StartIslandMap();
        System.out.println("DEBUG: map initialized");
        map.setFlagManager(flagManager);
        System.out.println("DEBUG: flag manager set");
        player = new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        System.out.println("DEBUG: player initialized");
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        map.setPlayer(player);
        map.getTextbox().setInteractKey(player.getInteractKey());
        map.getFlagManager().unsetFlag("battleWon");
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
        }

        // if game is paused, draw pause gfx over Screen gfx
		if (isGamePaused) {
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
        RUNNING, LEVEL_COMPLETED, GAME_OVER

    }

}
