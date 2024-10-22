package Players;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Player;
import Screens.PlayLevelScreen;

import java.util.HashMap;

// This is the class for the Cat player character
// basically just sets some values for physics and then defines animations

public class SpeedBoatSteve extends Player {

        public SpeedBoatSteve(float x, float y) {
                super(new SpriteSheet(ImageLoader.load("speedboatSteve.png"), 14, 17), x, y, "STAND_RIGHT", 20, 5);
                walkSpeed = defaultWalkSpeed;
        }

        public void update() {
                super.update();
        }

        public void draw(GraphicsHandler graphicsHandler) {
                super.draw(graphicsHandler);
        }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withBounds(4, 5, 5, 10)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
                           .withScale(3)
                           .withBounds(4, 5, 5, 10)
                           .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                           .build()
           });

            put("WALK_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                            .withScale(3)
                            .withBounds(4, 5, 5, 10)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                            .withScale(3)
                            .withBounds(4, 5, 5, 10)
                            .build()
            });

            put("WALK_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 5, 5, 10)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 5, 5, 10)
                            .build()
            });
        }};
    }

        // @Override
        // public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        //         return new HashMap<String, Frame[]>() {{
        //         put("STAND_LEFT", new Frame[] {
        //                 new FrameBuilder(spriteSheet.getSprite(0, 0))
        //                         .withScale(3)
        //                         .withBounds(6, 12, 12, 7)
        //                         .build()
        //         });

        //         put("STAND_RIGHT", new Frame[] {
        //                 new FrameBuilder(spriteSheet.getSprite(0, 0))
        //                         .withScale(3)
        //                         .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
        //                         .withBounds(6, 12, 12, 7)
        //                         .build()
        //         });

        //         put("WALK_LEFT", new Frame[] {
        //                 new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
        //                         .withScale(3)
        //                         .withBounds(6, 12, 12, 7)
        //                         .build(),
        //                 new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
        //                         .withScale(3)
        //                         .withBounds(6, 12, 12, 7)
        //                         .build(),
        //                 new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
        //                         .withScale(3)
        //                         .withBounds(6, 12, 12, 7)
        //                         .build(),
        //                 new FrameBuilder(spriteSheet.getSprite(1, 3), 14)
        //                         .withScale(3)
        //                         .withBounds(6, 12, 12, 7)
        //                         .build()
        //         });

        //         put("WALK_RIGHT", new Frame[] {
        //                 new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
        //                         .withScale(3)
        //                         .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
        //                         .withBounds(6, 12, 12, 7)
        //                         .build(),
        //                 new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
        //                         .withScale(3)
        //                         .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
        //                         .withBounds(6, 12, 12, 7)
        //                         .build(),
        //                 new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
        //                         .withScale(3)
        //                         .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
        //                         .withBounds(6, 12, 12, 7)
        //                         .build(),
        //                 new FrameBuilder(spriteSheet.getSprite(1, 3), 14)
        //                         .withScale(3)
        //                         .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
        //                         .withBounds(6, 12, 12, 7)
        //                         .build()
        //         });
        //         }};
        // }
}