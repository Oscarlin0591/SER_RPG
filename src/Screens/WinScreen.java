package Screens;

import Engine.*;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the win level screen
public class WinScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont lore1;
    protected SpriteFont lore2;
    protected SpriteFont lore3;
    protected SpriteFont lore4;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;

    public WinScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("You win!", 350, 239, "Lucida Calligraphy", 30, Color.white, Color.BLACK);
        lore1 = new SpriteFont("You vanquished the spirit that possessed the Nave d'Oro and now owns the golden vessel.", 0, winMessage.getY() + 40, "Lucida Calligraphy", 20, Color.white, Color.BLACK);
        lore2 = new SpriteFont("However, something still bothers you in the back of your mind, as if something isn't right about this situation.", 0, lore1.getY() + 30, "Lucida Calligraphy", 20, Color.white, Color.BLACK);
        lore3 = new SpriteFont("You push these thoughts aside and sail away with your new found riches", 0, lore2.getY() + 30, "Lucida Calligraphy", 20, Color.white, Color.BLACK);
        instructions = new SpriteFont("Press Space to play again or Escape to go back to the main menu", 120, lore3.getY() + 30,"Lucida Calligraphy", 20, Color.white, Color.BLACK);
        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ESC);
    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }

        // if space is pressed, reset level. if escape is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            playLevelScreen.resetLevel();
        } else if (Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC)) {
            playLevelScreen.goBackToMenu();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawImage(ImageLoader.load("neutralEnd.png"),0, 0,1440, 1080);
        winMessage.draw(graphicsHandler);
        lore1.draw(graphicsHandler);
        lore2.draw(graphicsHandler);
        lore3.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }
}
