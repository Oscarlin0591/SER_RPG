package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;


import java.util.HashMap;

// This class is for the portal NPC
public class Kraken extends NPC {

    public Kraken(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("kraken.png"), 64, 64), "STAND_RIGHT");
        System.out.println(Math.random());
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
