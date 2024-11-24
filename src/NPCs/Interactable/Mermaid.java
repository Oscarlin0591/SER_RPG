package NPCs.Interactable;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Utils.Point;
import java.util.HashMap;

public class Mermaid extends Enemy{
    HashMap<String, Frame[]> frames;
    public Mermaid (int id, Point location, String imageFile, int spriteWidth, int spriteHeight, int health, int strength, int critChance, int dodgeChance) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load(imageFile), spriteWidth, spriteHeight), "STAND_LEFT", health, strength, critChance, dodgeChance);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {

        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 30, 31, 52/2)
                    .build()
                });
            put("STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2)
                    .withBounds(0, 30, 31, 52/2)
                    .build()
           });
        }};
        

        
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
