package Screens;

import Engine.*;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the win level screen
public class GoodWinScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont lore;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;

    public GoodWinScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("You win!", 350, 239, "Lucida Calligraphy", 30, Color.white);
        lore = new SpriteFont("The Nave d'Oro is now yours, but you know you couldn't allow what's inside to exist\nYou board the Nave d'Oro, sailed it to a volcano and used your good ol' SpeedBoat to hurl\nthat ship into the volcano.\nPerhaps it is best to keep some things as legends, and you will be remembered as such...", 0, 0, "Lucida Calligraphy", 20, Color.white);
        instructions = new SpriteFont("Press Space to play again or Escape to go back to the main menu", 120, 279,"Lucida Calligraphy", 20, Color.white);
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
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        winMessage.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }
}
