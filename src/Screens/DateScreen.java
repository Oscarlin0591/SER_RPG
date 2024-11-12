package Screens;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Engine.*;
import Game.GameState;
import GameObject.Rectangle;
import Level.Player;
import SpriteFont.SpriteFont;

public class DateScreen extends Screen {
    protected PlayLevelScreen playLevelScreen;
    protected Rectangle healthBar;
    protected int xVel = 5; // Example speed variable for movement
    protected int timer = 0;
    protected boolean dateFinished;
    protected SpriteFont dateLabel; 
    protected KeyLocker keyLocker = new KeyLocker();

    public DateScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        dateFinished = false;
        healthBar = new Rectangle(50, 50, 200, 20); // Initialize health bar size and position
        healthBar.setColor(Color.PINK); // Example color for affection or timer bar
        dateLabel = new SpriteFont("Date Screen", 150, 50, "Comic Sans", 30, Color.BLACK);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
        if (!dateFinished) {
            if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
                keyLocker.lockKey(Key.SPACE);
                // Example action that reduces the health bar (affection level)
                healthBar.setWidth(healthBar.getWidth() - 10);
                if (healthBar.getWidth() <= 0) {
                    dateFinished = true;
                }
            }
            if (Keyboard.isKeyUp(Key.SPACE)) {
                keyLocker.unlockKey(Key.SPACE);
            }
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawImage(ImageLoader.load("datescreen.jpg"),0,0,ScreenManager.getScreenWidth(),ScreenManager.getScreenHeight());
        healthBar.draw(graphicsHandler); // Draw health/affection bar
        dateLabel.draw(graphicsHandler); // Display the title text
    }
}