package NPCs.Bosses;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.Player;
import Utils.Direction;
import Utils.Point;

public class HolyBeetle extends Enemy {
    // private int totalAmountMoved = 0;
    // private Direction direction = Direction.RIGHT;
    // private float speed = 1;
    
    //modified to call Enemy constructor, for now hardcoded to put in -1 for health and strength
    public HolyBeetle(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("CharacterPNGs/beetleBoss.png"), 32, 32), "WALK_LEFT", -1, -1, -1, -1);
    }

    public HolyBeetle(int id, Point location, float newHealth, float newStrength, float newCritChance, float newDodgeChance) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("CharacterPNGs/beetleBoss.png"), 32, 32), "WALK_LEFT", newHealth, newStrength, newCritChance, newDodgeChance);
    }


    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(4)
                    .withBounds(3, 5, 32, 32)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(4)
                    .withBounds(3, 5, 32, 32)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
           });
           put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 16)
                    .withScale(4)
                    .withBounds(0, 5, 32, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 16)
                    .withScale(4)
                    .withBounds(0, 5, 32, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 16)
                    .withScale(4)
                    .withBounds(0, 5, 32, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 16)
                    .withScale(4)
                    .withBounds(0, 5, 32, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 16)
                    .withScale(4)
                    .withBounds(0, 5, 32, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 16)
                    .withScale(4)
                    .withBounds(0, 5, 32, 32)
                    .build()
            });
            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 16)
                    .withScale(4)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 5, 32, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 16)
                    .withScale(4)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 5, 32, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 16)
                    .withScale(4)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 5, 32, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 16)
                    .withScale(4)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 5, 32, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 16)
                    .withScale(4)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 5, 32, 32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 16)
                    .withScale(4)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 5, 32, 32)
                    .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
