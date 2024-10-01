package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.*;
import Maps.TestMap;
import Maps.GameMap;
import Maps.BattleMap;
import Players.SpeedBoat;
import Utils.Direction;
import Utils.Point;

// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected static Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected GameOverScreen gameOverScreen;
    protected FlagManager flagManager;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
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

        // define/setup map - may need to replicate for all maps
        map = new TestMap();
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

        
        winScreen = new WinScreen(this);
        gameOverScreen = new GameOverScreen(this);
    }
    
    public void update() {
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
            setLocationGameMap();
        }

        // if flag is set for portal interaction, change map
        if (map.getFlagManager().isFlagSet("toggleIsland")) {
            System.out.println("DEBUG: Island interaction flag checker");
            setLocationIslandMap();
        }

        // if flag is set for being in combat PRINT DEBUG
        if (map.getFlagManager().isFlagSet("combatTriggered")) {
            //add logic to pull up combat menu here 
            System.out.println("DEBUG Combat Flag Works");
            map = new BattleMap();
            map.setFlagManager(flagManager);
            player = new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
            System.out.print(player.getLocation());
            player.setMap(map);
            playLevelScreenState = PlayLevelScreenState.RUNNING;
            map.setPlayer(player);
            map.getTextbox().setInteractKey(player.getInteractKey());
            map.getFlagManager().unsetFlag("combatTriggered");

        }
        if (map.getFlagManager().isFlagSet("battleWon")) {
            System.out.println("Batton won method triggered");
            returnToIslandMap();
        }
    }
    
    // methods to switch map, pending overhaul to shorten code.
    public void setLocationGameMap() {
        map.getFlagManager().unsetFlag("interactPortal");
        map = new GameMap();
        map.setFlagManager(flagManager);
        player = new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        map.setPlayer(player);
        map.getTextbox().setInteractKey(player.getInteractKey());
    }

    public void setLocationIslandMap() {
        map.getFlagManager().unsetFlag("toggleIsland");
        map = new TestMap();
        map.setFlagManager(flagManager);
        player = new SpeedBoat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        map.setPlayer(player);
        map.getTextbox().setInteractKey(player.getInteractKey());
    }

    public void returnToIslandMap() {
        System.out.println("DEBUG: trying to initialize map");
        map = new TestMap();
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
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    public void setPlayLevelScreenState(PlayLevelScreenState state) {
        playLevelScreenState = state;
    }

    public void gameOver() {
        playLevelScreenState = PlayLevelScreenState.GAME_OVER;
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }



    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED, GAME_OVER

    }

}
