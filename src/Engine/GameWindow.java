package Engine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Color;

import javax.swing.*;

/*
 * The JFrame that holds the GamePanel
 * Just does some setup and exposes the gamePanel's screenManager to allow an external class to setup their own content and attach it to this engine.
 */
public class GameWindow {
	private JFrame gameWindow;
	public static GamePanel gamePanel;
	private JPanel middlePanel;

	public GameWindow() {
		gameWindow = new JFrame("Game");

		middlePanel = new JPanel();
		middlePanel.setLayout(null);
		middlePanel.setBackground(Color.black);

		middlePanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				gamePanel.setLocation(middlePanel.getWidth() / 2 - gamePanel.getWidth() / 2, middlePanel.getHeight() / 2 - gamePanel.getHeight() / 2);
			}
		});

		//make gameWindow open in fullscreen
		gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);

		gamePanel = new GamePanel();
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
		// gamePanel.setMaximumSize(new Dimension(Config.GAME_WINDOW_WIDTH - 150, Config.GAME_WINDOW_HEIGHT - 150));
		gamePanel.setSize(new Dimension(800, 605));

		gamePanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				gamePanel.setLocation(middlePanel.getWidth() / 2 - gamePanel.getWidth() / 2, middlePanel.getHeight() / 2 - gamePanel.getHeight() / 2);
			}
		});

		middlePanel.add(gamePanel, BorderLayout.CENTER);

		gameWindow.setContentPane(middlePanel); //was gamePanel

		gameWindow.setResizable(true);
		//modified to set size of screen to size of gameWindow
		gameWindow.setSize(Config.GAME_WINDOW_WIDTH, Config.GAME_WINDOW_HEIGHT);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setVisible(true);
		
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // it'd be nice if this actually worked more than 1/3rd of the time
		gamePanel.setupGame();
	}

	// triggers the game loop to start as defined in the GamePanel class
	public void startGame() {
		gamePanel.startGame();
	}

	public ScreenManager getScreenManager() {
		return gamePanel.getScreenManager();
	}
}
