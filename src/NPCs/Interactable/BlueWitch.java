package NPCs.Interactable;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

public class BlueWitch extends NPC { 
    public BlueWitch(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("CharacterPNGs/blueWitch.png"),32, 48), "STAND_RIGHT");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0),16)
                    .withBounds(2,4,28,40)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 0),16)
                    .withBounds(2,4,28,40)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 0),16)
                    .withBounds(2,4,28,40)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 0),16)
                    .withBounds(2,4,28,40)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(4, 0),16)
                    .withBounds(2,4,28,40)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(5, 0),16)
                    .withBounds(2,4,28,40)
                    .withScale(3)
                    .build()
            });
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0),16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(2,4,28,40)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 0),16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(2,4,28,40)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 0),16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(2,4,28,40)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 0),16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(2,4,28,40)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(4, 0),16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(2,4,28,40)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(5, 0),16)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(2,4,28,40)
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