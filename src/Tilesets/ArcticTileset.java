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
    static Frame invisible;
    
    public ArcticTileset() {
    super(ImageLoader.load("TilesetPNGs/ArcticTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {

        int index = 0;
        // int[] waterTileIndex = new int[] {74,75,76,77,78,78,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95};


        arcticFloor1 = new FrameBuilder(getSubImage(0, 0))
            .withScale(tileScale)
            .build();

        arcticFloor2 = new FrameBuilder(getSubImage(0, 1))
            .withScale(tileScale)
            .build();

        arcticWater = new FrameBuilder(getSubImage(0, 2))
            .withScale(tileScale)
            .build(); 

        invisible = new FrameBuilder(getSubImage(16, 15))
            .withScale(tileScale)
            .build(); 

        ArcticTiles = new ArrayList<>();

        for (int row = 0; row <16; row++) {
            for (int col = 0; col < 16; col++) {
                if ((index >=0 && index <74)){
                    Frame arcticFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder arcticTile = new MapTileBuilder(arcticFrame)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    

                    ArcticTiles.add(arcticTile);
                }

                if ((index >=74 && index <96)){
                    Frame arcticFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder arcticTile = new MapTileBuilder(arcticWater)
                    .withTopLayer(arcticFrame)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    

                    ArcticTiles.add(arcticTile);
                }

                if ((index >=96 && index <179)){
                    Frame arcticFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder arcticTile = new MapTileBuilder(arcticFloor1)
                    .withTopLayer(arcticFrame)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    

                    ArcticTiles.add(arcticTile);
                }

                if ((index >=179 && index <213)){
                    Frame arcticFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder arcticTile = new MapTileBuilder(arcticFloor1)
                    .withTopLayer(arcticFrame)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    

                    ArcticTiles.add(arcticTile);
                }

                if ((index >=213 && index <248)){
                    Frame arcticFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder arcticTile = new MapTileBuilder(arcticFloor2)
                    .withTopLayer(arcticFrame)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    

                    ArcticTiles.add(arcticTile);
                }

                index++;
            }
        }

        return ArcticTiles;
    }

    
}
