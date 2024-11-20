package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

public class ShipwreckTileset extends Tileset{
    protected static ArrayList<MapTileBuilder> ShipwreckTiles;
    protected static Frame shipPlankFrame;
    protected static Frame beachFloorFrame;
    protected static int[] nonPassTiles;

    public ShipwreckTileset() {
        super(ImageLoader.load("TilesetPNGs/ShipwreckDark.png"), 32,32, 1);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        shipPlankFrame = new FrameBuilder(getSubImage(3, 1))
        .withScale(tileScale)
        .build();
        beachFloorFrame = new FrameBuilder(getSubImage(0, 0))
        .withScale(tileScale)
        .build();

        nonPassTiles = new int[]{1,2,3,4,9,18,27,36,41,42,43,44,49,50,-1};

        ShipwreckTiles = new ArrayList<>();
        int index = 0;
        int nonPassIndex = 0;

        for (int row = 0; row < 43; row++) {
            for (int col = 0; col < 20; col++) {
                
                if(index >= 0 && index < 62) {
                    Frame ShipwreckFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();

                    // Define the tile type (passable/not passable)
                    MapTileBuilder ShipwreckTile = new MapTileBuilder(ShipwreckFrame)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    
                    // Add tile to RPGTiles or mapTiles
                    ShipwreckTiles.add(ShipwreckTile);
                }

                if (index >=62 && index < 341) {
                Frame ShipwreckFrame = new FrameBuilder(getSubImage(row, col))
                .withScale(tileScale)
                .build();

                // Define the tile type (passable/not passable)
                MapTileBuilder ShipwreckTile = new MapTileBuilder(beachFloorFrame)
                .withTopLayer(ShipwreckFrame)
                .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                
                // Add tile to RPGTiles or mapTiles
                ShipwreckTiles.add(ShipwreckTile);
                }

                if (index >=341 && index < 520) {
                    Frame ShipwreckFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder ShipwreckTile = new MapTileBuilder(beachFloorFrame)
                    .withTopLayer(ShipwreckFrame)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    
                    // Add tile to RPGTiles or mapTiles
                    ShipwreckTiles.add(ShipwreckTile);
                    }

                if (index >=520 && index < 866) {
                    Frame ShipwreckFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder ShipwreckTile = new MapTileBuilder(shipPlankFrame)
                    .withTopLayer(ShipwreckFrame)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    
                    // Add tile to RPGTiles or mapTiles
                    ShipwreckTiles.add(ShipwreckTile);
                    }

                if (index == nonPassTiles[nonPassIndex]) {
                    Frame ShipwreckFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();

                    // Define the tile type (passable/not passable)
                    MapTileBuilder ShipwreckTile = new MapTileBuilder(ShipwreckFrame)
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                    
                    // Add tile to RPGTiles or mapTiles
                    ShipwreckTiles.set(index,ShipwreckTile);
                    nonPassIndex++;
                }

                index++;
            }
        }

        return ShipwreckTiles;
    }
    
}
