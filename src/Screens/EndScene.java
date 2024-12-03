package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import Engine.GamePanel;
import Engine.GameWindow;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.TextboxScriptAction;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import SpriteFont.SpriteFont;

public class EndScene extends Screen{
    protected PlayLevelScreen playLevelScreen;
    protected SpriteFont text = new SpriteFont("Press \'E\' To Continue",GameWindow.gamePanel.getWidth(),GameWindow.gamePanel.getHeight(), "Lucida Calligraphy", 36, Color.black);
    protected KeyLocker keyLocker = new KeyLocker();
    // Memories
    protected BufferedImage scene = ImageLoader.load("EndIslandScene.png");

    
    protected int keyPressTimer = 0;

    public EndScene(PlayLevelScreen playLevelScreen) { 
        this.playLevelScreen = playLevelScreen;
        keyLocker.lockKey(Key.E);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
        if(!keyLocker.isKeyLocked(Key.E) && Keyboard.isKeyDown(Key.E)){
            PlayLevelScreen.running();
            keyLocker.lockKey(Key.E);
        } else if (Keyboard.isKeyUp(Key.E)) {
            keyLocker.unlockKey(Key.E);
        }

    }
    
    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawImage(scene, 0,0,GameWindow.gamePanel.getWidth(),GameWindow.gamePanel.getHeight());
        text.draw(graphicsHandler);
    }
}
