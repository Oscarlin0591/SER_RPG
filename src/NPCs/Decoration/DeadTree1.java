package NPCs.Decoration;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.AnimatedSprite;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;
import java.util.HashMap;

public class DeadTree1 extends NPC{
    public DeadTree1(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("AnimatedSprites/DeadTree.png"), 128,128), "STAND_RIGHT");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 16)
                    .withScale(2)
                    .withBounds(24,64, 64,64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 16)
                    .withScale(2)
                    .withBounds(24,64, 64,64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 16)
                    .withScale(2)
                    .withBounds(24,64, 64,64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 16)
                    .withScale(2)
                    .withBounds(24,64, 64,64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 4), 16)
                    .withScale(2)
                    .withBounds(24,64, 64,64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 5), 16)
                    .withScale(2)
                    .withBounds(24,64, 64,64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 16)
                    .withScale(2)
                    .withBounds(24,64, 64,64)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 16)
                    .withScale(2)
                    .withBounds(24,64, 64,64)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 16)
                    .withScale(2)
                    .withBounds(24,64, 64,64)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 16)
                    .withScale(2)
                    .withBounds(24,64, 64,64)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 4), 16)
                    .withScale(2)
                    .withBounds(24,64, 64,64)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 5), 16)
                    .withScale(2)
                    .withBounds(24,64, 64,64)
                    .build()
           });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
