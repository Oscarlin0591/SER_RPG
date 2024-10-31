package Tilesets;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;

public class ArcticTileset extends Tileset{

    protected static ArrayList<MapTileBuilder> ArcticTiles;
    static Frame arcticFloor1;
    static Frame arcticFloor2;
    static Frame arcticWater;
    
    public ArcticTileset() {
    super(ImageLoader.load("TilesetPNGs/ArcticTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {

        int index = 0;

        arcticFloor1 = new FrameBuilder(getSubImage(0, 0))
            .withScale(tileScale)
            .build();

        arcticFloor2 = new FrameBuilder(getSubImage(0, 1))
            .withScale(tileScale)
            .build();

        ArcticTiles = new ArrayList<>();

        for (int row = 0; row <16; row++) {
            for (int col = 0; col < 16; col++) {
                if ((index >=0 && index <247)){
                    Frame arcticFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder arcticTile = new MapTileBuilder(arcticFrame)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    

                    ArcticTiles.add(arcticTile);
                }
                index++;
            }
        }

        return ArcticTiles;
    }

    
}
