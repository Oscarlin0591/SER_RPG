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
    protected KeyLocker keyLocker = new KeyLocker();
    
    private HitBox[] hitboxes;

    public BattleScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        box = new Rectangle(0, ScreenManager.getScreenHeight()/2, 10, 120);
        box.setColor(Color.lightGray);
        box.setBorderColor(Color.black);
        box.setBorderThickness(2);

        hitBox1 = new HitBox();
        hitboxes = new HitBox[8];
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
        count+=0.4;
    }

    @Override
    public void initialize() {
        // background = ImageLoader.load("background.png");
        for (int i = 0; i < hitboxes.length; i++) {
            hitboxes[i] = new HitBox();
        }
    }
    
    public void update() {
        box.update();
        if(box.getX()+25 < ScreenManager.getScreenWidth()) {
            box.moveRight(xVel);

            for (HitBox hitbox : hitboxes) {
                if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
                // && keyPressTimer == 0) {
                    if (box.intersects(hitbox) && !hitbox.isBoxHit()) {
                        hitbox.boxHit();
                        countBox();
                        keyLocker.lockKey(Key.SPACE);
                    }
                    
                } else if (Keyboard.isKeyUp(Key.SPACE)) {
                    keyLocker.unlockKey(Key.SPACE);
                    }

            }
        } else {
            box.setLocation(0, box.getY());
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
        graphicsHandler.drawImage(ImageLoader.load("background.png"), 0, 0, 1440, 1080);
        // player.draw(graphicsHandler);
        for (HitBox hitbox : hitboxes) {
            hitbox.draw(graphicsHandler);
        }
        box.draw(graphicsHandler);
    }
    private class HitBox extends Rectangle {

        private boolean isHit = false;

        public HitBox() {
            super((float) (Math.random()*ScreenManager.getScreenWidth())-25, (ScreenManager.getScreenHeight()/2)-10, 40, 150);
            this.setColor(new Color(255, 0, 0, 255));
            this.setBorderColor(Color.black);
            this.setBorderThickness(2);
        }

        public void boxHit() {
            isHit = true;
            this.setColor(Color.GREEN);
        }

        public boolean isBoxHit() {
            return isHit;
        }
    
    }
}

