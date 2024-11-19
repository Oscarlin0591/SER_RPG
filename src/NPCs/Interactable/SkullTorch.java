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

public class SkullTorch extends NPC { 
    protected static boolean torchLit = false;
    public SkullTorch(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("AnimatedSprites/Torch.png"),64, 64), "STAND_LEFT");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withBounds(8,0,32,64)
                    .withScale(2)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 1),16)
                    .withBounds(8,0,32,64)
                    .withScale(2)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2),16)
                    .withBounds(8,0,32,64)
                    .withScale(2)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3),16)
                    .withBounds(8,0,32,64)
                    .withScale(2)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 4),16)
                    .withBounds(8,0,32,64)
                    .withScale(2)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 5),16)
                    .withBounds(8,0,32,64)
                    .withScale(2)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 6),16)
                    .withBounds(8,0,32,64)
                    .withScale(2)
                    .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    public boolean isTorchLit() {
        return torchLit;
    }

    public void lightTorch() {
        torchLit = true;
    }
}