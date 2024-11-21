package Screens;

import Engine.*;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the win level screen
public class BadWinScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont lore1;
    protected SpriteFont lore2;
    protected SpriteFont lore3;
    protected SpriteFont lore4;
    protected SpriteFont lore5;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;

    public BadWinScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("You win!", 350, 239, "Lucida Calligraphy", 30, Color.white, Color.black);
        lore1 = new SpriteFont("Bruised and battered, you stumble into the Nave d'Oro to take it home. As you sail, however, your ", 0, winMessage.getY() + 40, "Lucida Calligraphy", 20, Color.white, Color.black);
        lore2 = new SpriteFont("injuries proven too much and you pass out on board, unaware that you're about to collide into the rocks by an island.", 0, lore1.getY() + 30, "Lucida Calligraphy", 20, Color.white, Color.black);
        lore3 = new SpriteFont("You crash and something flew out of the ship, it is the corpse of King Midas. The moment his corpse touched the ocean,", 0, lore2.getY() + 30, "Lucida Calligraphy", 20, Color.white, Color.black);
        lore4 = new SpriteFont("you see a glimmer, the water begins to transform. At first you watched in awe but it quickly turns to horror as the spread ", 0, lore3.getY() + 30, "Lucida Calligraphy", 20, Color.white, Color.black);
        lore5 = new SpriteFont("of the gold does not stop. As the oceans turn to gold, you can only slowly accept this cruel fate you brought upon the world...", 0, lore4.getY() + 30, "Lucida Calligraphy", 20, Color.white, Color.black);
        instructions = new SpriteFont("Press Space to play again or Escape to go back to the main menu", 120, lore5.getY() + 40,"Lucida Calligraphy", 20, Color.white, Color.black);
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
        graphicsHandler.drawImage(ImageLoader.load("badEnd.png"), 0, 0, 1440, 1080);
        winMessage.draw(graphicsHandler);
        lore1.draw(graphicsHandler);
        lore2.draw(graphicsHandler);
        lore3.draw(graphicsHandler);
        lore4.draw(graphicsHandler);
        lore5.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }
}
