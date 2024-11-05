package NPCs.Bosses;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Utils.Point;


import java.util.HashMap;

// This class is for the kraken boss
public class Krampus extends Enemy {

    public Krampus(int id, Point location, int health, int strength, float newCritChance, float newDodgeChance) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("CharacterPNGs/Krampus.png"), 64, 128), "STAND_LEFT", health, strength, newCritChance, newDodgeChance);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 50)
                    .withScale(2)
                    .withBounds(0, 0, 60,120)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 50)
                    .withScale(2)
                    .withBounds(0, 0, 60,120)
                    .build(),
            });
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 50)
                    .withScale(2)
                    .withBounds(0, 0, 60,120)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 50)
                    .withScale(2)
                    .withBounds(0, 0, 60,120)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
