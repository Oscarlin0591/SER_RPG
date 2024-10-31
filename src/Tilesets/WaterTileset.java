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
        super(ImageLoader.load("TilesetPNGs/WaterTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        //original code is ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();
        WaterTiles = new ArrayList<>();

        // caveFloor1 = new FrameBuilder(getSubImage(0, 0))
        //     .withScale(tileScale)
        //     .build();
        
        int index = 0;
        int tileIndex = 0;
        int[] individualTiles = new int[]{0,1,12,13,24,25};
        int oceanDelay = 120;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 12; col++) {
                if (index == 0 || index == 1 || index == 12|| index == 13|| index == 24|| index == 25) {
                Frame waterFrame = new FrameBuilder(getSubImage(row, col))
                .withScale(tileScale)
                .build();

                // Define the tile type (passable/not passable)
                MapTileBuilder waterTile = new MapTileBuilder(waterFrame)
                .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                
                // Add tile to RPGTiles or mapTiles
                WaterTiles.add(waterTile);
                
                // tileIndex++;
            }
            index++; 
            }
        }

        // animated waves
        Frame[] Wave1Frames = new Frame[] {
            new FrameBuilder(getSubImage(0, 2), oceanDelay+10)
            .withScale(tileScale)
            .build(),
            new FrameBuilder(getSubImage(0, 3), oceanDelay+10)
            .withScale(tileScale)
            .build(),
            new FrameBuilder(getSubImage(0, 3), oceanDelay+10)
            .withScale(tileScale)
            .build(),
            new FrameBuilder(getSubImage(0, 4), oceanDelay+10)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave1Tile = new MapTileBuilder(Wave1Frames);

        WaterTiles.add(Wave1Tile);

        Frame[] Wave2Frames = new Frame[] {
            new FrameBuilder(getSubImage(0, 5), 100)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(0, 6), 100)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave2Tile = new MapTileBuilder(Wave2Frames);

        WaterTiles.add(Wave2Tile);

        Frame[] Wave3Frames = new Frame[] {
            new FrameBuilder(getSubImage(0, 7), oceanDelay+12)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(0, 8), oceanDelay+12)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave3Tile = new MapTileBuilder(Wave3Frames);

        WaterTiles.add(Wave3Tile);

        Frame[] Wave4Frames = new Frame[] {
            new FrameBuilder(getSubImage(0, 9), oceanDelay+15)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(0, 10), oceanDelay+15)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(0, 11), oceanDelay+15)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(0, 10), oceanDelay+15)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave4Tile = new MapTileBuilder(Wave4Frames);

        WaterTiles.add(Wave4Tile);

        Frame[] Wave5Frames = new Frame[] {
            new FrameBuilder(getSubImage(1, 2), oceanDelay+18)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(1, 3), oceanDelay+18)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(1, 4), oceanDelay+18)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(1, 3), oceanDelay+18)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave5Tile = new MapTileBuilder(Wave5Frames);

        WaterTiles.add(Wave5Tile);

        Frame[] Wave6Frames = new Frame[] {
            new FrameBuilder(getSubImage(1, 5), 100)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(1, 6), 100)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave6Tile = new MapTileBuilder(Wave6Frames);

        WaterTiles.add(Wave6Tile);

        Frame[] Wave7Frames = new Frame[] {
            new FrameBuilder(getSubImage(1, 7), oceanDelay)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(1, 8), oceanDelay)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave7Tile = new MapTileBuilder(Wave7Frames);

        WaterTiles.add(Wave7Tile);

        Frame[] Wave8Frames = new Frame[] {
            new FrameBuilder(getSubImage(1, 9), oceanDelay+12)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(1, 10), oceanDelay+12)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(1, 11), oceanDelay+12)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(1, 10), oceanDelay+12)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave8Tile = new MapTileBuilder(Wave8Frames);

        WaterTiles.add(Wave8Tile);

        Frame[] Wave9Frames = new Frame[] {
            new FrameBuilder(getSubImage(2, 2), oceanDelay+15)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(2, 3), oceanDelay+15)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(2, 4), oceanDelay+15)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(2, 3), oceanDelay+15)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave9Tile = new MapTileBuilder(Wave9Frames);

        WaterTiles.add(Wave9Tile);

        Frame[] Wave10Frames = new Frame[] {
            new FrameBuilder(getSubImage(2, 5), 100)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(2, 6), 100)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave10Tile = new MapTileBuilder(Wave10Frames);

        WaterTiles.add(Wave10Tile);

        Frame[] Wave11Frames = new Frame[] {
            new FrameBuilder(getSubImage(2, 7), oceanDelay+15)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(2, 8), oceanDelay+15)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave11Tile = new MapTileBuilder(Wave11Frames);

        WaterTiles.add(Wave11Tile);

        Frame[] Wave12Frames = new Frame[] {
            new FrameBuilder(getSubImage(2, 9), oceanDelay+21)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(2, 10), oceanDelay+21)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(2, 11), oceanDelay+21)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(2, 10), oceanDelay+21)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave12Tile = new MapTileBuilder(Wave12Frames);

        WaterTiles.add(Wave12Tile);

        Frame[] Wave13Frames = new Frame[] {
            new FrameBuilder(getSubImage(3, 0), oceanDelay+17)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(3, 1), oceanDelay+17)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(3, 2), oceanDelay+17)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(3, 1), oceanDelay+17)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave13Tile = new MapTileBuilder(Wave13Frames);

        WaterTiles.add(Wave13Tile);

        Frame[] Wave14Frames = new Frame[] {
            new FrameBuilder(getSubImage(4, 0), oceanDelay+10)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(4, 1), oceanDelay+10)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(4, 2), oceanDelay+10)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(4, 1), oceanDelay+10)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave14Tile = new MapTileBuilder(Wave14Frames);

        WaterTiles.add(Wave14Tile);

        Frame[] Wave15Frames = new Frame[] {
            new FrameBuilder(getSubImage(5, 0), oceanDelay+10)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(5, 1), oceanDelay+10)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 2), oceanDelay+10)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 1), oceanDelay+10)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave15Tile = new MapTileBuilder(Wave15Frames);

        WaterTiles.add(Wave15Tile);

        Frame[] Wave16Frames = new Frame[] {
            new FrameBuilder(getSubImage(3, 3), oceanDelay+17)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(4, 3), oceanDelay+17)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 3), oceanDelay+17)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(4, 3), oceanDelay+17)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave16Tile = new MapTileBuilder(Wave16Frames);

        WaterTiles.add(Wave16Tile);

        Frame[] Wave17Frames = new Frame[] {
            new FrameBuilder(getSubImage(3, 4), oceanDelay+12)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(4, 4), oceanDelay+12)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 4), oceanDelay+12)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(4, 4), oceanDelay+12)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave17Tile = new MapTileBuilder(Wave17Frames);

        WaterTiles.add(Wave17Tile);

        Frame[] Wave18Frames = new Frame[] {
            new FrameBuilder(getSubImage(3, 5), oceanDelay+12)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(4, 5), oceanDelay+12)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 5), oceanDelay+12)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(4, 5), oceanDelay+12)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave18Tile = new MapTileBuilder(Wave18Frames);

        WaterTiles.add(Wave18Tile);

        Frame[] Wave19Frames = new Frame[] {
            new FrameBuilder(getSubImage(3, 6), oceanDelay+18)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(4, 6), oceanDelay+18)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 6), oceanDelay+18)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(4, 6), oceanDelay+18)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave19Tile = new MapTileBuilder(Wave19Frames);

        WaterTiles.add(Wave19Tile);

        Frame[] Wave20Frames = new Frame[] {
            new FrameBuilder(getSubImage(3, 7), oceanDelay+18)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(4, 7), oceanDelay+18)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 7), oceanDelay+18)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(4, 7), oceanDelay+18)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave20Tile = new MapTileBuilder(Wave20Frames);

        WaterTiles.add(Wave20Tile);

        Frame[] Wave21Frames = new Frame[] {
            new FrameBuilder(getSubImage(3, 8), oceanDelay)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(4, 8), oceanDelay)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 8), oceanDelay)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(4, 8), oceanDelay)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder Wave21Tile = new MapTileBuilder(Wave21Frames);

        WaterTiles.add(Wave21Tile);

        for (int col = 6; col < 12; col++) {
                Frame glacierFrame = new FrameBuilder(getSubImage(9, col))
                .withScale(tileScale)
                .build();

                // Define the tile type (passable/not passable)
                MapTileBuilder glacierTile = new MapTileBuilder(Wave4Frames)
                .withTopLayer(glacierFrame)
                .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                
                // Add tile to RPGTiles or mapTiles
                WaterTiles.add(glacierTile);

        }


        return WaterTiles;
    }


    //returns the entire tileset
    public static ArrayList<MapTileBuilder> getWaterTiles() {
        return WaterTiles;
    }
}
