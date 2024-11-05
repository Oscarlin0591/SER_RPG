package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Utils.Point;


import java.util.HashMap;

// This class is for the kraken boss
public class Kraken extends Enemy {

    public Kraken(int id, Point location, int health, int strength, float newCritChance, float newDodgeChance) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("kraken.png"), 64, 64), "STAND_RIGHT", health, strength, newCritChance, newDodgeChance);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(2/1)
                            .build(),
           });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
