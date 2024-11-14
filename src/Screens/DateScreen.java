package Screens;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Engine.*;
import Game.GameState;
import GameObject.Rectangle;
import Level.NPC;
import Level.Player;
import NPCs.Interactable.BlueWitch;
import SpriteFont.SpriteFont;
import Utils.Point;

public class DateScreen extends Screen {
    protected PlayLevelScreen playLevelScreen;
    protected Rectangle healthBar;
    protected Rectangle dateBarBG;
    protected int xVel = 5; // Example speed variable for movement
    protected int timer = 0;
    protected boolean dateFinished;
    protected SpriteFont dateLabel; 
    protected KeyLocker keyLocker = new KeyLocker();
    protected NPC dateNPC;
    // protected BlueWitch blueWitch = new NPCs.Interactable.BlueWitch(1, new Point(ScreenManager.getScreenWidth()/2-100, ScreenManager.getScreenHeight()));

    public DateScreen(PlayLevelScreen playLevelScreenn, NPC npc) {
        this.playLevelScreen = playLevelScreen;
        dateFinished = false;
        dateBarBG = new Rectangle(ScreenManager.getScreenWidth()/2-100, 400, 210, 30);
        dateBarBG.setColor(Color.lightGray);
        dateBarBG.setBorderThickness(2);
        dateBarBG.setBorderColor(Color.BLACK);
        healthBar = new Rectangle(ScreenManager.getScreenWidth()/2-100, 405, 0, 20); // Initialize health bar size and position
        healthBar.setColor(Color.PINK); // Example color for affection or timer bar
        healthBar.setLocation(ScreenManager.getScreenWidth()/2-100, 400);
        dateLabel = new SpriteFont("Date Screen", 150, 50, "Comic Sans", 30, Color.BLACK);
        dateNPC = npc;
        dateNPC.setLocation(ScreenManager.getScreenWidth()/2, ScreenManager.getScreenHeight()/2);
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
                healthBar.setWidth(healthBar.getWidth() + 20);
                if (healthBar.getWidth() >= 200) {
                    dateFinished = true;
                    PlayLevelScreen.running();
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
        dateBarBG.draw(graphicsHandler);
        healthBar.draw(graphicsHandler); // Draw health/affection bar
        dateLabel.draw(graphicsHandler); // Display the title text
        dateNPC.draw(graphicsHandler);
    }
}