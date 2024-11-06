package Engine;

import GameObject.Rectangle;
import Level.Map;
import Maps.BattleMap;
import Screens.PlayLevelScreen;
import SpriteFont.SpriteFont;
import Utils.Colors;

import javax.swing.*;

import java.awt.*;


/*
 * This is where the game loop process and render back buffer is setup
 */
public class GamePanel extends JPanel {
	// loads Screens on to the JPanel
	// each screen has its own update and draw methods defined to handle a "section" of the game.
	private ScreenManager screenManager;

	// used to draw graphics to the panel
	private GraphicsHandler graphicsHandler;

	private KeyLocker keyLocker = new KeyLocker();
	private Thread gameLoopProcess;

	private Key showFPSKey = Key.G;
	private SpriteFont fpsDisplayLabel;
	private boolean showFPS = false;
	private int currentFPS;
	private boolean doPaint;

	private Map map;
	//private Player player = PlayLevelScreen.

	// Battle GUI
	public static SpriteFont healthLabel;
	public static float playerHealth;
	public static String healthInfo = ("Player Health: " + playerHealth);
	public static boolean isInBattle = false;

	public static SpriteFont enemyHealthLabel;
	public static float enemyHealth;
	public static String enemyHealthInfo = ("Enemy Health: " + enemyHealth);
	// private static boolean battleInitiated = false;


	// The JPanel and various important class instances are setup here
	public GamePanel() {
		super();
		this.setDoubleBuffered(true);

		// attaches Keyboard class's keyListener to this JPanel
		this.addKeyListener(Keyboard.getKeyListener());

		graphicsHandler = new GraphicsHandler();

		screenManager = new ScreenManager();

		map = PlayLevelScreen.getMap();

		fpsDisplayLabel = new SpriteFont("FPS", 4, 3, "Arial", 12, Color.black);

		currentFPS = Config.TARGET_FPS;

		// this game loop code will run in a separate thread from the rest of the program
		// will continually update the game's logic and repaint the game's graphics
		GameLoop gameLoop = new GameLoop(this);
		gameLoopProcess = new Thread(gameLoop.getGameLoopProcess());
	}

	// this is called later after instantiation, and will initialize screenManager
	public void setupGame() {
		setBackground(Colors.CORNFLOWER_BLUE);
		screenManager.initialize(new Rectangle(getX(), getY(), getWidth(), getHeight()));
	}

	// this starts the timer (the game loop is started here)
	public void startGame() {
		gameLoopProcess.start();
	}

	public ScreenManager getScreenManager() {
		return screenManager;
	}

	public void setCurrentFPS(int currentFPS) {
		this.currentFPS = currentFPS;
	}

	public void setDoPaint(boolean doPaint) {
		this.doPaint = doPaint;
	}

	public void update() {
		updateShowFPSState();
		screenManager.update();

	}

	private void updateShowFPSState() {
		if (Keyboard.isKeyDown(showFPSKey) && !keyLocker.isKeyLocked(showFPSKey)) {
			showFPS = !showFPS;
			keyLocker.lockKey(showFPSKey);
		}

		if (Keyboard.isKeyUp(showFPSKey)) {
			keyLocker.unlockKey(showFPSKey);
		}

		fpsDisplayLabel.setText("FPS: " + currentFPS);
	}

	public void draw() {
		// draw current game state
		screenManager.draw(graphicsHandler);

		if (showFPS) {
			fpsDisplayLabel.draw(graphicsHandler);
		}
	}

	public static void combatTriggered() {
		isInBattle = true;
	}
	
	public static void combatFinished() {
		isInBattle = false;
	}

	public static boolean getBattleStatus() {
		return isInBattle;
	}
	
	public static void updateHealthInfo() {
		playerHealth = PlayLevelScreen.getMap().getPlayer().getHealth();
		healthInfo = String.format("Player Health: %.2f", playerHealth);
		if (playerHealth < 0) {
			healthLabel = new SpriteFont("Player Health: 0", 630, 550, "Arial", 12, Color.WHITE);
		}else healthLabel = new SpriteFont(healthInfo, 630, 550, "Arial", 12, Color.WHITE);

		enemyHealth = BattleMap.getEnemy().getHealth();
		enemyHealthInfo = String.format("Enemy Health: %.2f", enemyHealth);
		if (enemyHealth < 0) {
			enemyHealthLabel = new SpriteFont("Enemy Health: 0", 70, 550, "Arial",12, Color.WHITE);
		}else enemyHealthLabel = new SpriteFont(enemyHealthInfo, 70, 550, "Arial",12, Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (doPaint) {
			// every repaint call will schedule this method to be called
			// when called, it will setup the graphics handler and then call this class's draw method
			graphicsHandler.setGraphics((Graphics2D) g);
			draw();
		}
	}
}
