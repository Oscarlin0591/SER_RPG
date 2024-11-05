package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.NPC;
import Utils.Point;

import java.util.HashMap;

// This class is for the Yeti NPC
public class Yeti extends Enemy {

    public Yeti(int id, Point location,float newHealth, float newStrength, float newCritChance, float newDodgeChance) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("CharacterPNGs/Yeti.png"), 64, 64), "STAND_RIGHT", newHealth, newStrength, newCritChance, newDodgeChance);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2)
                    .withBounds(0, 0, 64, 64)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2)
                    .withBounds(0, 0, 64, 64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
           });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
