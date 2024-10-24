package NPCs.Interactable;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import Utils.Direction;
import Utils.Point;

public class Spider extends NPC {
    private int totalAmountMoved = 0;
    private Direction direction = Direction.RIGHT;
    private float speed = 1;
    
    //modified to call Enemy constructor, for now hardcoded to put in -1 for health and strength
    public Spider(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("spiderSpriteSheet.png"), 32, 32), "WALK_RIGHT");
    }

    // this code makes the bug npc walk back and forth (left to right)
    @Override
    public void performAction(Player player) {
        // if bug has not yet moved 90 pixels in one direction, move bug forward
        if (totalAmountMoved < 180) {
            float amountMoved = moveXHandleCollision(speed * direction.getVelocity());
            totalAmountMoved += Math.abs(amountMoved);
        }

        // else if bug has already moved 90 pixels in one direction, flip the bug's direction
        else {
            totalAmountMoved = 0;
            if (direction == Direction.LEFT) {
                direction = Direction.RIGHT;
            }
            else {
                direction = Direction.LEFT;
            }
        }

        // based off of the bugs current walking direction, set its animation to match
        if (direction == Direction.RIGHT) {
            currentAnimationName = "WALK_RIGHT";
        }
        else {
            currentAnimationName = "WALK_LEFT";
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
                    .withBounds(0,16, 12,8)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1))
                    .withScale(3)
                    .withBounds(0,16, 12,8)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2))
                    .withScale(3)
                    .withBounds(0,16, 12,8)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3))
                    .withScale(3)
                    .withBounds(0,16, 12,8)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 4))
                    .withScale(3)
                    .withBounds(0,16, 12,8)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(8, 0))
                    .withScale(3)
                    .withBounds(0,16, 12,8)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(8, 1))
                    .withScale(3)
                    .withBounds(0,16, 12,8)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(8, 2))
                    .withScale(3)
                    .withBounds(0,16, 12,8)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(8, 3))
                    .withScale(3)
                    .withBounds(0,16, 12,8)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(8, 4))
                    .withScale(3)
                    .withBounds(0,16, 12,8)
                    .build()
           });
           put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 0), 8)
                        .withScale(3)
                        .withBounds(0,16, 12,8)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 8)
                        .withScale(3)
                        .withBounds(0,16, 12,8)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2), 8)
                        .withScale(3)
                        .withBounds(0,16, 12,8)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 3), 8)
                        .withScale(3)
                        .withBounds(0,16, 12,8)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 4), 8)
                        .withScale(3)
                        .withBounds(0,16, 12,8)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 5), 8)
                        .withScale(3)
                        .withBounds(0,16, 12,8)
                        .build()
            });
            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(9, 0), 8)
                        .withScale(3)
                        .withBounds(0,16, 12,8)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(9, 1), 8)
                        .withScale(3)
                        .withBounds(0,16, 12,8)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(9, 2), 8)
                        .withScale(3)
                        .withBounds(0,16, 12,8)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(9, 3), 8)
                        .withScale(3)
                        .withBounds(0,16, 12,8)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(9, 4), 8)
                        .withScale(3)
                        .withBounds(0,16, 12,8)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(9, 5), 8)
                        .withScale(3)
                        .withBounds(0,16, 12,8)
                        .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
