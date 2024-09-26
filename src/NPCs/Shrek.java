package NPCs;

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

public class Shrek extends NPC {
    
    public Shrek(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Shrek.png"), 40, 40), "STAND_RIGHT");
    }

    // this code makes the bug npc walk back and forth (left to right)
   // @Override
    //public void performAction(Player player) {
       
    //}

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2)
                    .withBounds(2, 2, 36, 36)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2)
                    .withBounds(2, 2, 36, 36)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
           });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}