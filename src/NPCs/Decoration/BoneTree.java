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

public class BoneTree extends NPC{
    public BoneTree(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("DecorationTiles/Broken_tree_shadow2_1-1.png"), 63,89), "STAND_RIGHT");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
                    .withBounds(24,64, 64,64)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
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
