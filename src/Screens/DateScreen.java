package Screens;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Rectangle;
import Level.Map;
import Level.NPC;
import Level.Player;
import Saves.ContinueState;
import SpriteFont.SpriteFont;

public class DateScreen extends Screen{
    protected PlayLevelScreen playLevelScreen;
    protected Rectangle box;
    protected Rectangle hitBox1;
    protected int xVel = 8;
    protected int yVel = 0;
    protected float count;
    protected boolean battleFinished;
    protected SpriteFont dateLabel; 
    protected KeyLocker keyLocker = new KeyLocker();
    
    private HitBox[] hitboxes;

    public DateScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        box = new Rectangle(0, GameWindow.gamePanel.getHeight()/2, 10, 120);
        box.setColor(Color.lightGray);
        box.setBorderColor(Color.black);
        box.setBorderThickness(2);

        dateLabel = new SpriteFont("Charm your partner!", 150, 50, "Lucida Calligraphy", 30, Color.BLACK);

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
        if(box.getX()+25 < GameWindow.gamePanel.getWidth()) {
            box.moveRight(xVel);

            for (HitBox hitbox : hitboxes) {
                if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
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
        return count*20;
    }

    public boolean battleFinished() {
        return battleFinished;
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawImage(ImageLoader.load("datescreen.png"), 0, 0, 1440, 1080);
        // player.draw(graphicsHandler);
        for (HitBox hitbox : hitboxes) {
            hitbox.draw(graphicsHandler);
        }
        box.draw(graphicsHandler);
        dateLabel.draw(graphicsHandler);
    }
    private class HitBox extends Rectangle {

        private boolean isHit = false;

        public HitBox() {
            super((float) (Math.random()*GameWindow.gamePanel.getWidth())-25, (GameWindow.gamePanel.getHeight()/2)-10, 40, 150);
            this.setColor(Color.BLUE);
            this.setBorderColor(Color.black);
            this.setBorderThickness(2);
        }

        public void boxHit() {
            isHit = true;
            this.setColor(Color.PINK);
        }

        public boolean isBoxHit() {
            return isHit;
        }
    
    }
}


