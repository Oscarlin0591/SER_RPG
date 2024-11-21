package Screens;

import Engine.*;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the win level screen
public class GoodWinScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont lore1;
    protected SpriteFont lore2;
    protected SpriteFont lore3;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;

    public GoodWinScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("You win!", 350, 239, "Lucida Calligraphy", 30, Color.white, Color.black);
        lore1 = new SpriteFont("The Nave d'Oro is now yours, but you know you couldn't allow what's inside to exist.", 0, winMessage.getY() + 40, "Lucida Calligraphy", 20, Color.white, Color.black);
        lore2 = new SpriteFont("You board the Nave d'Oro, sailed it to a volcano and used your good ol' SpeedBoat to hurl that ship into the volcano.", 0, lore1.getY() + 30, "Lucida Calligraphy", 20, Color.white, Color.black);
        lore3 = new SpriteFont("Perhaps it is best to keep some things as legends, and you will be remembered as such...", 0, lore2.getY() + 30, "Lucida Calligraphy", 20, Color.white, Color.black);
        instructions = new SpriteFont("Press Space to play again or Escape to go back to the main menu", 120, lore3.getY()+40,"Lucida Calligraphy", 20, Color.white, Color.black);
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
        graphicsHandler.drawImage(ImageLoader.load("trueEnd.png"),0, 0, 1440, 1080);
        lore1.draw(graphicsHandler);
        lore2.draw(graphicsHandler);
        lore3.draw(graphicsHandler);
        winMessage.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }
}
