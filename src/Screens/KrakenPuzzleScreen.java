package Screens;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Engine.GameWindow;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import SpriteFont.SpriteFont;

public class KrakenPuzzleScreen extends Screen{
    protected PlayLevelScreen playLevelScreen;
    protected int currHovered = 0; //the hover for which option the user is currently over
    protected int currInput = 0; //keeps track of the number of inputs the user did
    protected SpriteFont question;
    protected SpriteFont first;
    protected SpriteFont last;
    protected SpriteFont enter;
    protected int keyPressTimer;
    protected int hoverX, hoverY;
    protected int[] answer = {1,2,0,3};
    protected int[] input = {-1,-1,-1,-1};
    protected KeyLocker keyLocker = new KeyLocker();
    protected BufferedImage swordGhost = ImageLoader.load("CharacterPNGs/swordGhost.png");
    protected BufferedImage pirateGhost = ImageLoader.load("ghostPirate.png");
    protected BufferedImage flintlockGhost = ImageLoader.load("CharacterPNGs/flintlockGhost.png");
    protected BufferedImage cannibalGhost = ImageLoader.load("CharacterPNGs/ghostCannibal.png");
    protected BufferedImage backspace = ImageLoader.load("backspace.png");

    
    public KrakenPuzzleScreen(PlayLevelScreen playLevelScreen){
        this.playLevelScreen = playLevelScreen;
        keyLocker.lockKey(Key.E);
            
        question = new SpriteFont("What order did the pirates meet their end?", 400, 50, "Arial", 30, Color.white);
        first = new SpriteFont("Earliest", 300, 279,"Arial", 20, Color.white);
        last = new SpriteFont("Lastest", 1000, 279,"Arial", 20, Color.white);
        enter = new SpriteFont("Try Order", 905, 405+45,"Arial", 20, Color.white);
    
        hoverY = 400;
    }
    @Override
    public void initialize() {
    
    }
    
        @Override
        public void update() {
        if (Keyboard.isKeyDown(Key.A) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currHovered--;
        } else if (Keyboard.isKeyDown(Key.D) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currHovered++;
        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }
    
        // if down is pressed on last menu item or up is pressed on first menu item, "loop" the selection back around to the beginning/end
        if (currHovered > 5) {
            currHovered = 0;
        } else if (currHovered < 0) {
            currHovered = 5;
        }
    
        hoverX = 100*currHovered + 400;
    
        // if space is pressed on menu item, change to appropriate screen based on which menu item was chosen
        if (Keyboard.isKeyUp(Key.E)) {
            keyLocker.unlockKey(Key.E);
        }
    
        if (!keyLocker.isKeyLocked(Key.E) && Keyboard.isKeyDown(Key.E)) {
            if(currHovered == 4 && currInput != 0){
                input[currInput-1] = -1;
                currInput--;
            }else if(currHovered == 5){
                //check if correct
                if(check()){
                    //System.out.println("CORRECT");
                    PlayLevelScreen.flagManager.setFlag("krakenQuestCompleted");
                }
                PlayLevelScreen.running();
            }else if(currHovered < 4 && currInput != 4){
                input[currInput] = currHovered;
                currInput++;
            }

            /*for(int i = 0; i < input.length; i++){
                System.out.println(input[i]);
            }*/ 
            keyLocker.lockKey(Key.E);
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangleWithBorder(0, 0, GameWindow.gamePanel.getWidth(), GameWindow.gamePanel.getHeight(), Color.darkGray, Color.BLUE, 10);
        question.draw(graphicsHandler);
        first.draw(graphicsHandler);
        last.draw(graphicsHandler);
        graphicsHandler.drawFilledRectangle(hoverX, hoverY, 100, 100, Color.LIGHT_GRAY);
        graphicsHandler.drawImage(pirateGhost, 405, 405,90,90);
        graphicsHandler.drawImage(swordGhost, 505, 405,90,90);
        graphicsHandler.drawImage(flintlockGhost, 605, 405,90,90);
        graphicsHandler.drawImage(cannibalGhost, 705, 405,90,90);
        graphicsHandler.drawImage(backspace, 805, 405, 90, 90);
        enter.draw(graphicsHandler);
        for(int i = 0; i < 4; i++){
            switch (input[i]) {
                case 0:
                    graphicsHandler.drawImage(pirateGhost, 100*i + 500, 250, 90, 90);
                    break;
                case 1:
                    graphicsHandler.drawImage(swordGhost, 100*i + 500, 250, 90, 90);
                    break;
                case 2:
                    graphicsHandler.drawImage(flintlockGhost, 100*i + 500, 250, 90, 90);
                    break;
                case 3:
                    graphicsHandler.drawImage(cannibalGhost, 100*i + 500, 250, 90, 90);
                    break;
                default:
                    break;
            }
        }
    }

    private boolean check(){
        for(int i = 0; i < input.length; i++){
            if(input[i] != answer[i]){
                return false;
            }
        }
        return true;
    }
    
}
