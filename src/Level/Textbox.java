package Level;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.ScreenManager;
import SpriteFont.SpriteFont;
import Engine.GameWindow;
import Screens.PlayLevelScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Represents the game's textbox
// Will display the text it is given to its textQueue
// Each String in the textQueue will be displayed in the textbox, and hitting the interact key will cycle between additional Strings in the queue
// Use the newline character in a String in the textQueue to break the text up into a second line if needed
// Also supports adding options for a player to select from
public class Textbox {
    // whether textbox is shown or not
    protected boolean isActive;

    // textbox constants
    protected final int x = 22;
    protected final static int bottomY = ScreenManager.getScreenHeight()-130;
    protected final int topY = 22;
    protected final int fontX = 35;
    protected final int fontBottomY = bottomY;
    protected final int fontTopY = 34;
    protected int width = ScreenManager.getScreenWidth()-22;
    protected final int height = 125;

    // options textbox constants
    protected final int optionX = 22;
    protected final static int optionBottomY = bottomY - 110;
    protected final int optionTopY = 130;
    protected int optionWidth = width;
    protected int optionHeight = 100;
    protected final int fontOptionX = optionX+50;
    protected final int fontOptionBottomYStart = optionBottomY + 10;
    protected final int fontOptionTopYStart = 145;
    protected final int fontOptionSpacing = 40;
    protected final int optionPointerX = 30;
    protected final int optionPointerYBottomStart = bottomY - 90;
    protected final int optionPointerYTopStart = 158;

    // core vars that make textbox work
    private Queue<TextboxItem> textQueue;
    private TextboxItem currentTextItem;
    protected int selectedOptionIndex = 0;
    private SpriteFont text = null;
    private ArrayList<SpriteFont> options = null;
    private KeyLocker keyLocker = new KeyLocker();
    private Key interactKey = Key.E;

    private Map map;

    public Textbox(Map map) {
        this.map = map;
        this.textQueue = new LinkedList<>();
    }

    public void addText(String text) {
        if (textQueue.isEmpty()) {
            keyLocker.lockKey(interactKey);
        }
        textQueue.add(new TextboxItem(text));
    }

    public void addText(String[] text) {
        if (textQueue.isEmpty()) {
            keyLocker.lockKey(interactKey);
        }
        for (String textItem : text) {
            textQueue.add(new TextboxItem(textItem));
        }
    }

    public void addText(TextboxItem text) {
        if (textQueue.isEmpty()) {
            keyLocker.lockKey(interactKey);
        }
        textQueue.add(text);
    }

    public void addText(TextboxItem[] text) {
        if (textQueue.isEmpty()) {
            keyLocker.lockKey(interactKey);
        }
        for (TextboxItem textItem : text) {
            textQueue.add(textItem);
        }
    }

    // returns whether the textQueue is out of items to display or not
    // useful for scripts to know when to complete
    public boolean isTextQueueEmpty() {
        return textQueue.isEmpty();
    }

    public void update() {
        // if textQueue has more text to display and the interact key button was pressed previously, display new text
        if (!textQueue.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
            currentTextItem = textQueue.peek();
            options = null;

            // if camera is at bottom of screen, text is drawn at top of screen instead of the bottom like usual
            // to prevent it from covering the player
            int fontY = !map.getCamera().isAtBottomOfMap() ? fontBottomY : fontTopY;
  
            // create text spritefont that will be drawn in textbox
            text = new SpriteFont(currentTextItem.getText(), fontX, fontY, "Lucida Calligraphy", 30, Color.black);

            // if there are options associated with this text item, prepare option spritefont text to be drawn in options textbox
            if (currentTextItem.getOptions() != null) {
                // if camera is at bottom of screen, text is drawn at top of screen instead of the bottom like usual
                // to prevent it from covering the player
                int fontOptionY = !map.getCamera().isAtBottomOfMap() ? fontOptionBottomYStart : fontOptionTopYStart;

                options = new ArrayList<>();
                // for each option, crate option text spritefont that will be drawn in options textbox
                for (int i = 0; i < currentTextItem.getOptions().size(); i++) {
                    options.add(new SpriteFont(currentTextItem.options.get(i), fontOptionX, fontOptionY + (i *  fontOptionSpacing), "Lucida Calligraphy", 30, Color.black));
                }
                selectedOptionIndex = 0;
            }

        }
        // if interact key is pressed, remove the current text from the queue to prepare for the next text item to be displayed
        if (Keyboard.isKeyDown(interactKey) && !keyLocker.isKeyLocked(interactKey)) {
            keyLocker.lockKey(interactKey);
            textQueue.poll();

            // if an option was selected, set output manager flag to the index of the selected option
            // a script can then look at output manager later to see which option was selected and do with that information what it wants
            if (options != null) {
                map.getActiveScript().getScriptActionOutputManager().addFlag("TEXTBOX_OPTION_SELECTION", selectedOptionIndex);
            }
        }
        else if (Keyboard.isKeyUp(interactKey)) {
            keyLocker.unlockKey(interactKey);
        }

        if (options != null) {
            if (Keyboard.isKeyDown(Key.S) && !keyLocker.isKeyLocked(Key.S)) {
                keyLocker.lockKey(Key.S);
                if (selectedOptionIndex < options.size() - 1) {
                    selectedOptionIndex++;
                }
            }
            if (Keyboard.isKeyDown(Key.W) && !keyLocker.isKeyLocked(Key.W)) {
                keyLocker.lockKey(Key.W);
                if (selectedOptionIndex > 0) {
                    selectedOptionIndex--;
                }
            }
            if (Keyboard.isKeyUp(Key.S)) {
                keyLocker.unlockKey(Key.S);
            }
            if (Keyboard.isKeyUp(Key.W)) {
                keyLocker.unlockKey(Key.W);
            }
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        //update widths based on gamePanel size
        width = GameWindow.gamePanel.getWidth() - 42;
        optionWidth = GameWindow.gamePanel.getWidth() - 42;

        //update height of combat menu to fit all four options (temporary fix, eventually make based on number of options)
        if (PlayLevelScreen.getMap().getMapFileName().equals("battle_map.txt"))
            optionHeight = 200;
        else
            optionHeight = 100;

        // draw textbox
        // if camera is at bottom of screen, textbox is drawn at top of screen instead of the bottom like usual
        // to prevent it from covering the player
        int y = !map.getCamera().isAtBottomOfMap() ? bottomY : topY;
        graphicsHandler.drawFilledRectangleWithBorder(x, y, width, height, new Color(112, 181, 255), new Color(255, 221, 0), 2);

        if (text != null) {
            // draw text in textbox
            text.drawWithParsedNewLines(graphicsHandler, 10);
            
            if (options != null) {
                // draw options textbox
                // if camera is at bottom of screen, textbox is drawn at top of screen instead of the bottom like usual
                // to prevent it from covering the player
                int optionY = !map.getCamera().isAtBottomOfMap() ? optionBottomY : optionTopY;
                graphicsHandler.drawFilledRectangleWithBorder(optionX, optionY, optionWidth, optionHeight, new Color(112, 181, 255), new Color(255, 221, 0), 2);

                // draw each option text
                for (SpriteFont option : options) {
                    option.draw(graphicsHandler);
                }

                // the start y location of the option pointer depends on whether the options textbox is on top or bottom of screen
                int optionPointerYStart = !map.getCamera().isAtBottomOfMap() ? optionPointerYBottomStart : optionPointerYTopStart;
                // draw option selection indicator (small black rectangle)
                graphicsHandler.drawFilledRectangle(optionPointerX, optionPointerYStart + (selectedOptionIndex * fontOptionSpacing), 10, 10, Color.black);
            }
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setInteractKey(Key interactKey) {
        this.interactKey = interactKey;
    }

    public static int getOptionBottomY() {
        return optionBottomY;
    }
}
