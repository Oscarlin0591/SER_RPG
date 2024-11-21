package NPCs.Bosses;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Utils.Point;

//this is the class for the final boss the Golden Ship 
public class GoldenShip extends Enemy{

    public GoldenShip(int id, Point location, int health, int strength, float newCritChance, float newDodgeChance) {   
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("CharacterPNGs/GoldenShip.png"), 180, 180), "STAND_LEFT", health, strength, newCritChance, newDodgeChance);
    }
    
    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2/1)
                    .build()
           });
           put("STAND_RIGHT", new Frame[] {
            new FrameBuilder(spriteSheet.getSprite(0, 0))
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withScale(2/1)
                    .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
