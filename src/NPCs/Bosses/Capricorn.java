package NPCs.Bosses;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.NPC;
import Utils.Point;

public class Capricorn extends Enemy{
    public Capricorn(int id, Point location, int health, int strength, float newCritChance, float newDodgeChance) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("CharacterPNGs/Capricorn.png"), 74, 108), "STAND_RIGHT", health, strength, newCritChance, newDodgeChance);
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
