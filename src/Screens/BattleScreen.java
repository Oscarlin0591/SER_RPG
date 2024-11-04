package Screens;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Engine.ScreenManager;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Rectangle;
import Level.Map;
import Level.Player;
import Maps.TitleScreenMap;
import Players.SpeedBoatSteve;
import Saves.ContinueState;
import SpriteFont.SpriteFont;

public class BattleScreen extends Screen{
    protected ScreenCoordinator screenCoordinator;
    protected PlayLevelScreen playLevelScreen;
    protected SpeedBoatSteve player;
    protected Rectangle box;
    protected Rectangle hitBox1;
    protected int xVel = 8;
    protected int yVel = 0;
    protected int keyPressTimer = 0;
    protected float count;
    protected boolean battleFinished;
    
    private HitBox[] hitboxes;
        public BattleScreen(PlayLevelScreen playLevelScreen) {
            this.playLevelScreen = playLevelScreen;
            box = new Rectangle(0, ScreenManager.getScreenHeight()/2, 10, 120);
            box.setColor(Color.lightGray);
            box.setBorderColor(Color.black);
            box.setBorderThickness(2);
    
            hitBox1 = new HitBox();
            hitboxes = new HitBox[10];
            for (int i = 0; i < hitboxes.length; i++) {
                hitboxes[i] = new HitBox();
                if (i > 0 && hitboxes[i].intersects(hitboxes[i-1])) {
                    hitboxes[i] = new HitBox();
                }
            }
            count = 0;
            // battleFinished = false;
        }
    
        public void countBox() {
            count+=0.2;
        }
    
        @Override
        public void initialize() {
            // background = ImageLoader.load("background.png");
            for (int i = 0; i < hitboxes.length; i++) {
                hitboxes[i] = new HitBox();
            }
        }
    
        public void update() {
            // player.update();
            box.update();
            if(box.getX()+25 < ScreenManager.getScreenWidth()) {
                box.moveRight(xVel);
                for (HitBox hitbox : hitboxes) {
                if (Keyboard.isKeyDown(Key.SPACE) && keyPressTimer == 0) {
                    if (box.intersects(hitbox)) {
                        hitbox.setColor(Color.green);
                        countBox();
                    }
                    keyPressTimer = 15;
                    if(Keyboard.isKeyUp(Key.SPACE)) {
                        keyPressTimer = 0;
                    }
                } else {
                    if (keyPressTimer > 0) {
                        keyPressTimer--;
                    }
                }
            }
        } else {
            box.setLocation(0, box.getY());
            // battleFinished = true;
            PlayLevelScreen.running();
        }
    }

    public float returnMultiplier() {
        return count;
    }

    public boolean battleFinished() {
        return battleFinished;
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawImage(ImageLoader.load("background.png"), 100, 100, 1280, 720);
        // player.draw(graphicsHandler);
        for (HitBox hitbox : hitboxes) {
            hitbox.draw(graphicsHandler);
        }
        box.draw(graphicsHandler);
    }
    private class HitBox extends Rectangle {

        public HitBox() {
            super((float) (Math.random()*ScreenManager.getScreenWidth())-25, (ScreenManager.getScreenHeight()/2)-10, 40, 150);
            this.setColor(Color.red);
            this.setBorderColor(Color.black);
            this.setBorderThickness(2);
        }
    
    }
}

