package NPCs;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

public class Boy extends NPC { 
    public Boy(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("CharacterPNGs/thirstyKid.png"),20, 20), "STAND_RIGHT");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0),8)
                    .withScale(2)
                    .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}