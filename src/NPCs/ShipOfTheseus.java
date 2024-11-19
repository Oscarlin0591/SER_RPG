package NPCs;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import Screens.PlayLevelScreen;
import Utils.Point;

public class ShipOfTheseus extends NPC {
    
    public ShipOfTheseus(int id, Point location, String startAnimation) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("ShipOfTheseus.png"), 80, 60), "ShipOfTheseus_" + startAnimation);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("ShipOfTheseus_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(2)
                            .build(),
            });
            put("ShipOfTheseus_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
           });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    // @Override
    // public void performAction(Player player) {
    //     if (PlayLevelScreen.flagManager.isFlagSet("goodShipInformed") && this == PlayLevelScreen.getMap().getNPCById(999)) {
    //         moveXHandleCollision(-1);
    //     }
    // }
}
