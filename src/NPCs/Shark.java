package NPCs;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Utils.Point;

public class Shark extends Enemy {
    public Shark(int id, Point location, int health, int strength, int critChance, int dodgeChance) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Shark.png"), 80, 60), "SHARK", health, strength, critChance, dodgeChance);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("SHARK", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(3)
                            .build(),
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
