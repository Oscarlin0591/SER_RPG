package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

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

public class ZodiacMatchingScreen extends Screen{
    protected PlayLevelScreen playLevelScreen;
    protected int currentSelection = 0; //tracking the hovered name 
    protected int currentInput = 0; //tracking current inputs
    protected int[] solution = {0,1,2,3,4}; //correct order
    protected int[] input = {-1,-1,-1,-1,-1}; //player input
    protected KeyLocker keyLocker = new KeyLocker();
    protected boolean incorrect = false;

    //Constellations
    protected BufferedImage ariesImage = ImageLoader.load("AriesConstellation.png");
    protected BufferedImage taurusImage = ImageLoader.load("TaurusConstellation.png");
    protected BufferedImage leoImage = ImageLoader.load("LeoConstellation.png");
    protected BufferedImage cancerImage = ImageLoader.load("CancerConstellation.png");
    protected BufferedImage capricornImage = ImageLoader.load("CapricornConstellation.png");

    protected SpriteFont instructions;
    protected SpriteFont matchPrompt;
    protected int hoverX, hoverY;
    protected int keyPressTimer = 0;

    public ZodiacMatchingScreen(PlayLevelScreen playLevelScreen){ 
        this.playLevelScreen = playLevelScreen;
        instructions = new SpriteFont("Match the constellation to its name", 300, 50, "Arial", 30, Color.white);
        matchPrompt = new SpriteFont("Hover over and press E", 300, 100, "Arial", 20, Color.BLUE);
        hoverY = 400;
        keyLocker.lockKey(Key.E);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
        if(Keyboard.isKeyDown(Key.A) && keyPressTimer == 0){
            keyPressTimer = 14;
            currentSelection --;
        } else if (Keyboard.isKeyDown(Key.D) && keyPressTimer == 0){
            keyPressTimer = 14;
            currentSelection ++;
        } else {
            if (keyPressTimer > 0){
                keyPressTimer--;
            }
        }

        if( currentSelection > 4){
            currentSelection = 4;
        } else if (currentSelection < 0){
            currentSelection = 4;
        }

        hoverX = 100 * currentSelection + 100;

        if(!keyLocker.isKeyLocked(Key.E) && Keyboard.isKeyDown(Key.E)){
            if(currentInput <5){
                input[currentInput] = currentSelection;
                currentInput++;
            }
            keyLocker.lockKey(Key.E);

            //checking the win condition to see if they are filled correctly
            if(currentInput ==5){
                if(checkSolution()){
                    System.out.println("Correct!");
                    PlayLevelScreen.running();
                    PlayLevelScreen.getMap().getFlagManager().unsetFlag("capricornGameTriggered");
                } else {
                    System.out.println("Incorrect!");
                    resetInputs();
                }
            }

        } else if (Keyboard.isKeyUp(Key.E)) {
            keyLocker.unlockKey(Key.E);
        }

    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        instructions.draw(graphicsHandler);
        matchPrompt.draw(graphicsHandler);
        
        graphicsHandler.drawFilledRectangle(hoverX, hoverY, 64, 64, Color.darkGray);
    
        //constellations
        graphicsHandler.drawImage(ariesImage, 100,400,64,64);
        graphicsHandler.drawImage(taurusImage, 200,400,64,64);
        graphicsHandler.drawImage(leoImage, 300,400,64,64);
        graphicsHandler.drawImage(cancerImage, 400,400,64,64);
        graphicsHandler.drawImage(capricornImage, 500,400,64,64);

        if (incorrect) {
            graphicsHandler.drawString("Incorrect!", GameWindow.gamePanel.getWidth()-200, 200, new Font("Lucida Calligraphy", 0, 20), Color.white);
            int timer = 100;
            timer--;
            if (timer == 0)
            incorrect = false;
        }
        
    
     //displayinng the match constellations in their postions
    for (int i = 0; i<5 ; i++){
        if(input[i] != -1){
            switch(input[i]){
                case 0:
                    graphicsHandler.drawImage(ariesImage, i*100+100, 250, 64, 64);
                    break;
                case 1:
                    graphicsHandler.drawImage(taurusImage, i*100+100,250,64,64);
                    break;
                case 2:
                    graphicsHandler.drawImage(leoImage, i*100+100,250,64,64);
                    break;
                case 3:
                    graphicsHandler.drawImage(cancerImage, i*100+100,250,64,64);
                    break;
                case 4:
                    graphicsHandler.drawImage(capricornImage, i*100+100,250,64,64);
                    break;

            }
        }
    }
}
    //checking if it is right
    private boolean checkSolution() {
        for (int i = 0; i <solution.length; i++){
            if(solution[i] != input[i]){
                return false;
            }
        }
        return true;
    }

    //resetting inputs after getting it wrong
    private void resetInputs(){
        input = new int[]{-1,-1,-1,-1,-1};
        currentInput =0;
    }



}
