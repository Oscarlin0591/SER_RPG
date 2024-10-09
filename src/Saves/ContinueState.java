package Saves;

public class ContinueState {
    protected boolean pressedContinue;

    public ContinueState(){
        pressedContinue = false;
    }

    public void setPressedContinue(boolean pressedContinue) {
        this.pressedContinue = pressedContinue;
    }

    public boolean getPressedContinue(){
        return pressedContinue;
    }
}
