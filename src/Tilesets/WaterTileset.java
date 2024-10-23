package Tilesets;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;

public class WaterTileset extends Tileset{
    protected static ArrayList<MapTileBuilder> WaterTiles;
    static Frame caveFloor1;

    public WaterTileset() {
        super(ImageLoader.load("WaterTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        //original code is ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();
        WaterTiles = new ArrayList<>();

        caveFloor1 = new FrameBuilder(getSubImage(0, 0))
            .withScale(tileScale)
            .build();
        
        int index = 0;

        for (int row = 0; row < 14; row++) {
            for (int col = 0; col < 12; col++) {
                Frame caveFrame = new FrameBuilder(getSubImage(row, col))
                .withScale(tileScale)
                .build();

                // Define the tile type (passable/not passable)
                MapTileBuilder caveTile = new MapTileBuilder(caveFrame)
                .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                
                // Add tile to RPGTiles or mapTiles
                WaterTiles.add(caveTile);
            
                index++;
            }
        }

        return WaterTiles;
    }


    //returns the entire tileset
    public static ArrayList<MapTileBuilder> getWaterTiles() {
        return WaterTiles;
    }
}
