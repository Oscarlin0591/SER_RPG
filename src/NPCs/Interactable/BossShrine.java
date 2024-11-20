package NPCs.Interactable;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Direction;
import Utils.Point;

public class BossShrine extends NPC {
    public BossShrine(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("AnimatedSprites/Shrine1.png"),128, 128), "STAND_RIGHT");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withBounds(30,0,64,110)
                    .withScale(3)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 1),16)
                    .withBounds(30,0,64,110)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2),16)
                    .withBounds(30,0,64,110)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3),16)
                    .withBounds(30,0,64,110)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 4),16)
                    .withBounds(30,0,64,110)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 5),16)
                    .withBounds(30,0,64,110)
                    .withScale(3)
                    .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}