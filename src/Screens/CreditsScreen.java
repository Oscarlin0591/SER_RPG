package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the credits screen
public class CreditsScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected KeyLocker keyLocker = new KeyLocker();
    protected SpriteFont creditsLabel;
    protected SpriteFont createdByLabel;
    protected SpriteFont teamLabel1;
    protected SpriteFont teamLabel2;
    protected SpriteFont artLabel;
    protected SpriteFont soundLabel;
    protected SpriteFont returnInstructionsLabel;

    public CreditsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        // setup graphics on screen (background map, spritefont text)
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        creditsLabel = new SpriteFont("Credits", 15, 7, "Lucida Calligraphy", 30, Color.white);
        teamLabel1 = new SpriteFont("Game Created By: Ayden Hafidi, Aislin Hayes, Jacob Levin,", 130, creditsLabel.getY()+30, "Lucida Calligraphy", 20, Color.white);
        teamLabel2 = new SpriteFont("Oscar Lin, and Andrew McCleary", 130, teamLabel1.getY()+30, "Lucida Calligraphy", 20, Color.white);
        artLabel = new SpriteFont("Principal Artists: Ayden Hafidi, Aislin Hayes", 130, teamLabel2.getY()+30, "Lucida Calligraphy", 20, Color.white);
        soundLabel = new SpriteFont("Soundtrack By: Jacob Levin, Oscar Lin", 130, artLabel.getY()+30, "Lucida Calligraphy", 20, Color.white);
        createdByLabel = new SpriteFont("Engine Created by Alex Thimineur", 130, soundLabel.getY()+30, "Lucida Calligraphy", 20, Color.white);
        returnInstructionsLabel = new SpriteFont("Press Space to return to the menu", 20, 532, "Lucida Calligraphy", 30, Color.white);
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        background.update(null);

        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }

        // if space is pressed, go back to main menu
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        creditsLabel.draw(graphicsHandler);
        teamLabel1.draw(graphicsHandler);
        teamLabel2.draw(graphicsHandler);
        artLabel.draw(graphicsHandler);
        soundLabel.draw(graphicsHandler);
        createdByLabel.draw(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
