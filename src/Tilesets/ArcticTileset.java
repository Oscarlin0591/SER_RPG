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
    static Frame arcticLedge;
    
    public ArcticTileset() {
    super(ImageLoader.load("TilesetPNGs/ArcticTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {

        int index = 0;
        int[] nonPass = new int[] {2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,40,41,42,43,44,45,46,47,-1};
        int nonPassIndex = 0;


        arcticFloor1 = new FrameBuilder(getSubImage(0, 0))
            .withScale(tileScale)
            .build();

        arcticFloor2 = new FrameBuilder(getSubImage(0, 1))
            .withScale(tileScale)
            .build();

        arcticWater = new FrameBuilder(getSubImage(0, 2))
            .withScale(tileScale)
            .build(); 

        arcticLedge = new FrameBuilder(getSubImage(2, 9))
            .withScale(tileScale)
            .build(); 

        ArcticTiles = new ArrayList<>();

        for (int row = 0; row <19; row++) {
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

                if (index == nonPass[nonPassIndex] && nonPassIndex < nonPass.length-1) {
                    Frame arcticFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder arcticTile = new MapTileBuilder(arcticFrame)
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                    

                    ArcticTiles.set(index,arcticTile);
                    nonPassIndex++;
                }

                if ((index >=74 && index <96)||(index >=105 && index <134)){
                    Frame arcticFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder arcticTile = new MapTileBuilder(arcticWater)
                    .withTopLayer(arcticFrame)
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                    

                    ArcticTiles.add(arcticTile);
                }

                if ((index >=96 && index <102)||(index >=134 && index < 179)){
                    Frame arcticFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder arcticTile = new MapTileBuilder(arcticFloor1)
                    .withTopLayer(arcticFrame)
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                    

                    ArcticTiles.add(arcticTile);
                }

                if (index >=102 && index <105){
                    Frame arcticFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder arcticTile = new MapTileBuilder(arcticLedge)
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
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                    

                    ArcticTiles.add(arcticTile);
                }

                if ((index >=213 && index <248)){
                    Frame arcticFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder arcticTile = new MapTileBuilder(arcticFloor2)
                    .withTopLayer(arcticFrame)
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                    

                    ArcticTiles.add(arcticTile);
                }

                if ((index >=248 && index <304)){
                    Frame arcticFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder arcticTile = new MapTileBuilder(arcticFrame)
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                    

                    ArcticTiles.add(arcticTile);
                }

                index++;
            }
        }

        Frame arcticFrame = new FrameBuilder(getSubImage(16, 6))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder arcticTile = new MapTileBuilder(arcticFrame)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    

                    ArcticTiles.set(262,arcticTile);

        return ArcticTiles;
    }

    
}
