package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Utils.Point;
import java.util.HashMap;

public class PirateShip extends Enemy{
    public PirateShip (int id, Point location, String imageFile, int health, int strength, int critChance, int dodgeChance) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load(imageFile), 64, 64), "STAND_LEFT", health, strength, critChance, dodgeChance);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withBounds(0, 0, 62,48)
                            .build()
                        });
                        put("STAND_RIGHT", new Frame[] {
                            new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withBounds(0, 0, 62,48)
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
