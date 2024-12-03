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

public class MemoryScreen extends Screen{
    protected PlayLevelScreen playLevelScreen;
    protected SpriteFont text = new SpriteFont("Press \'E\' To Continue",GameWindow.gamePanel.getWidth(),GameWindow.gamePanel.getHeight(), "Lucida Calligraphy", 36, Color.black);
    protected KeyLocker keyLocker = new KeyLocker();
    protected int memory;
    protected BufferedImage mainMemory;
    
    // Memories
    protected BufferedImage memory1 = ImageLoader.load("memory1.png");
    protected BufferedImage memory2 = ImageLoader.load("memory2.png");
    protected BufferedImage memory3 = ImageLoader.load("memory3.png");
    // protected BufferedImage memory4 = ImageLoader.load("memory4.png");
    
    protected int keyPressTimer = 0;

    public MemoryScreen(PlayLevelScreen playLevelScreen, int memory){ 
        this.playLevelScreen = playLevelScreen;
        keyLocker.lockKey(Key.E);
        this.memory = memory;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
        switch (memory) {
            case 1:
            mainMemory = memory1;
            break;
            case 2:
            mainMemory = memory2;
            break;
            case 3:
            mainMemory = memory3;
            break;
            case 4:
            // mainMemory = memory4;
            break;
        }
        if(!keyLocker.isKeyLocked(Key.E) && Keyboard.isKeyDown(Key.E)){
            PlayLevelScreen.running();
            keyLocker.lockKey(Key.E);
        } else if (Keyboard.isKeyUp(Key.E)) {
            keyLocker.unlockKey(Key.E);
        }

    }
    
    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawImage(mainMemory, 0,0,GameWindow.gamePanel.getWidth(),GameWindow.gamePanel.getHeight());
        text.draw(graphicsHandler);
    }
}
