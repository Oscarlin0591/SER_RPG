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
public class Portal extends NPC {

    public Portal(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Portal.png"), 32, 32), "PORTAL_1");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("PORTAL_1", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(3)
                            .withBounds(7, 13, 32, 32)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1),8)
                            .withScale(3)
                            .withBounds(7, 13, 32, 32)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2),8)
                            .withScale(3)
                            .withBounds(7, 13, 32, 32)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 3),8)
                            .withScale(3)
                            .withBounds(7, 13, 32, 32)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 4),8)
                            .withScale(3)
                            .withBounds(7, 13, 32, 32)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 5),8)
                            .withScale(3)
                            .withBounds(7, 13, 32, 32)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 6),8)
                            .withScale(3)
                            .withBounds(7, 13, 32, 32)
                            .build()
                    // new FrameBuilder(spriteSheet.getSprite(0, 7),8)
                    //         .withScale(3)
                    //         .withBounds(7, 13, 32, 32)
                    //         .build()
           });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
