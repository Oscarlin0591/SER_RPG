package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

public class CaveTileset extends Tileset {
    
    protected static ArrayList<MapTileBuilder> CaveTiles;
    static Frame caveFloor1;

    public CaveTileset() {
        super(ImageLoader.load("CaveTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        //original code is ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();
        CaveTiles = new ArrayList<>();

        caveFloor1 = new FrameBuilder(getSubImage(0, 0))
            .withScale(tileScale)
            .build();
        
        int index = 0;
        int[] nonPass = new int[]{39,40,41,42,47,48,49,50,54,55,56,57,58,81,83,85,87,89,91,131,132,142,143,155,156,166,167,178,179,190,191,214,215,222,223,230,231,234,235,238,239};
        int nonPassIndex = 0;
        for (int row = 0; row < 12; row++) {
            for (int col = 0; col < 20; col++) {
                Frame caveFrame = new FrameBuilder(getSubImage(row, col))
                .withScale(tileScale)
                .build();

                // Define the tile type (passable/not passable)
                MapTileBuilder caveTile = new MapTileBuilder(caveFrame)
                .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                
                // Add tile to RPGTiles or mapTiles
                CaveTiles.add(caveTile);
                

                
                if ((index >= 35 && index < 59) || (index >= 120 && index < 192) || (index >=208 && index <=239)||(index>=80 && index <92)) {
                    Frame mushroomTreeBigTop = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    MapTileBuilder mushroomTreeBigTopTile = new MapTileBuilder(caveFloor1)
                    .withTopLayer(mushroomTreeBigTop)
                    .withTileType(TileType.PASSABLE);
                    
                    CaveTiles.set(index, mushroomTreeBigTopTile);
                }
                if (index == nonPass[nonPassIndex]) {
                    Frame nonPassFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    MapTileBuilder nonPassTile = new MapTileBuilder(caveFloor1)
                    .withTopLayer(nonPassFrame)
                    .withTileType(TileType.NOT_PASSABLE);
                    
                    CaveTiles.set(index, nonPassTile);
                    nonPassIndex++;
                }

                if (index == 59) {
                    Frame nonPassFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    MapTileBuilder nonPassTile = new MapTileBuilder(nonPassFrame)
                    .withTileType(TileType.NOT_PASSABLE);
                    
                    CaveTiles.set(index, nonPassTile);
                    nonPassIndex++;
                }
                index++;
            }
        }

        //cave floor
        // Frame caveFloor1 = new FrameBuilder(getSubImage(0, 0))
        //     .withScale(tileScale)
        //     .build();

        // MapTileBuilder caveFloorTile1 = new MapTileBuilder(caveFloor1)
        //     .withTileType(TileType.PASSABLE);
        
        // CaveTiles.set(0, caveFloorTile1);
        
        Frame caveFloor2 = new FrameBuilder(getSubImage(0, 0))
            .withScale(tileScale)
            .build();

        MapTileBuilder caveFloorTile2 = new MapTileBuilder(caveFloor2)
            .withTileType(TileType.PASSABLE);
        
        CaveTiles.set(1, caveFloorTile2);
        

        //mushroom trees
        Frame mushroomTreeTop1 = new FrameBuilder(getSubImage(1, 15))
            .withScale(tileScale)
            .build();

        MapTileBuilder mushroomTreeTopTile1 = new MapTileBuilder(caveFloor1)
            .withTopLayer(mushroomTreeTop1)
            .withTileType(TileType.PASSABLE);

        CaveTiles.set(35, mushroomTreeTopTile1);

        Frame mushroomTreeTop2 = new FrameBuilder(getSubImage(1, 16))
            .withScale(tileScale)
            .build();

        MapTileBuilder mushroomTreeTopTile2 = new MapTileBuilder(caveFloor2)
            .withTopLayer(mushroomTreeTop2)
            .withTileType(TileType.PASSABLE);

        CaveTiles.set(36, mushroomTreeTopTile2);

        Frame mushroomTreeTop3 = new FrameBuilder(getSubImage(1, 17))
            .withScale(tileScale)
            .build();

        MapTileBuilder mushroomTreeTopTile3 = new MapTileBuilder(caveFloor1)
            .withTopLayer(mushroomTreeTop3)
            .withTileType(TileType.PASSABLE);

        CaveTiles.set(37, mushroomTreeTopTile3);

        Frame mushroomTreeTop4 = new FrameBuilder(getSubImage(1, 18))
            .withScale(tileScale)
            .build();

        MapTileBuilder mushroomTreeTopTile4 = new MapTileBuilder(caveFloor2)
            .withTopLayer(mushroomTreeTop4)
            .withTileType(TileType.PASSABLE);

        CaveTiles.set(38, mushroomTreeTopTile4);

        Frame mushroomTreeTop5 = new FrameBuilder(getSubImage(2, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder mushroomTreeTopTile5 = new MapTileBuilder(caveFloor1)
            .withTopLayer(mushroomTreeTop5)
            .withTileType(TileType.PASSABLE);

        CaveTiles.set(43, mushroomTreeTopTile5);

        Frame mushroomTreeTop6 = new FrameBuilder(getSubImage(2, 4))
            .withScale(tileScale)
            .build();

        MapTileBuilder mushroomTreeTopTile6 = new MapTileBuilder(caveFloor2)
            .withTopLayer(mushroomTreeTop6)
            .withTileType(TileType.PASSABLE);

        CaveTiles.set(44, mushroomTreeTopTile6);

        Frame mushroomTreeTop7 = new FrameBuilder(getSubImage(2, 5))
            .withScale(tileScale)
            .build();

        MapTileBuilder mushroomTreeTopTile7 = new MapTileBuilder(caveFloor1)
            .withTopLayer(mushroomTreeTop7)
            .withTileType(TileType.PASSABLE);

        CaveTiles.set(45, mushroomTreeTopTile7);

        Frame mushroomTreeTop8 = new FrameBuilder(getSubImage(2, 6))
            .withScale(tileScale)
            .build();

        MapTileBuilder mushroomTreeTopTile8 = new MapTileBuilder(caveFloor2)
            .withTopLayer(mushroomTreeTop8)
            .withTileType(TileType.PASSABLE);

        CaveTiles.set(46, mushroomTreeTopTile8);

    Frame mushroomTreeTop9 = new FrameBuilder(getSubImage(2, 11))
        .withScale(tileScale)
        .build();

    MapTileBuilder mushroomTreeTopTile9 = new MapTileBuilder(caveFloor1)
        .withTopLayer(mushroomTreeTop9)
        .withTileType(TileType.PASSABLE);

    CaveTiles.set(51, mushroomTreeTopTile9);

    Frame mushroomTreeTop10 = new FrameBuilder(getSubImage(2, 12))
        .withScale(tileScale)
        .build();

    MapTileBuilder mushroomTreeTopTile10 = new MapTileBuilder(caveFloor2)
        .withTopLayer(mushroomTreeTop10)
        .withTileType(TileType.PASSABLE);

    CaveTiles.set(52, mushroomTreeTopTile10);

    Frame mushroomTreeTop11 = new FrameBuilder(getSubImage(2, 13))
        .withScale(tileScale)
        .build();

    MapTileBuilder mushroomTreeTopTile11 = new MapTileBuilder(caveFloor1)
        .withTopLayer(mushroomTreeTop11)
        .withTileType(TileType.PASSABLE);

    CaveTiles.set(53, mushroomTreeTopTile11);

    Frame mushroomTreeTop12 = new FrameBuilder(getSubImage(2, 14))
        .withScale(tileScale)
        .build();

    MapTileBuilder mushroomTreeTopTile12 = new MapTileBuilder(caveFloor2)
        .withTopLayer(mushroomTreeTop12)
        .withTileType(TileType.PASSABLE);

    CaveTiles.set(54, mushroomTreeTopTile12);

    // for (int row = 6; row < 10; row++) {
    //     for (int col = 0; col < 20; col++) {
    //         // int index = 120;
    //         if (index < 192) {
    //             Frame mushroomTreeBigTop = new FrameBuilder(getSubImage(row, col))
    //                 .withScale(tileScale)
    //                 .build();

    //             MapTileBuilder mushroomTreeBigTopTile = new MapTileBuilder(caveFloor2)
    //                 .withTopLayer(mushroomTreeBigTop)
    //                 .withTileType(TileType.PASSABLE);

    //             CaveTiles.set(index, mushroomTreeBigTopTile);
    //             index++;
    //         }
    //     }
    // }

    //mushroom tree stem


    // flowing river
    Frame[] flowingRiver1 = new Frame[] {
        new FrameBuilder(getSubImage(3, 14), 65)
            .withScale(tileScale)
            .build(),
        new FrameBuilder(getSubImage(3, 15), 65)
                .withScale(tileScale)
                .build(),
        new FrameBuilder(getSubImage(3, 16), 65)
                .withScale(tileScale)
                .build()
    };
    MapTileBuilder flowingRiverTile1 = new MapTileBuilder(flowingRiver1)
        .withTileType(TileType.NOT_PASSABLE);

        CaveTiles.add(flowingRiverTile1);

    Frame[] flowingRiver2 = new Frame[] {
        new FrameBuilder(getSubImage(3, 17), 65)
            .withScale(tileScale)
            .build(),
        new FrameBuilder(getSubImage(3, 18), 65)
                .withScale(tileScale)
                .build(),
        new FrameBuilder(getSubImage(3, 19), 65)
                .withScale(tileScale)
                .build()
    };
    MapTileBuilder flowingRiverTile2 = new MapTileBuilder(flowingRiver2)
        .withTileType(TileType.NOT_PASSABLE);

        CaveTiles.add(flowingRiverTile2);

    // glistening gems
    Frame[] gemstone = new Frame[] {
        new FrameBuilder(getSubImage(5, 15), 30)
            .withScale(tileScale)
            .build(),
        new FrameBuilder(getSubImage(5, 16), 30)
                .withScale(tileScale)
                .build(),
        new FrameBuilder(getSubImage(5, 17), 30)
                .withScale(tileScale)
                .build(),
        new FrameBuilder(getSubImage(5, 18), 30)
                .withScale(tileScale)
                .build(),
        new FrameBuilder(getSubImage(5, 19), 30)
                .withScale(tileScale)
                .build(),
        new FrameBuilder(getSubImage(5, 18), 30)
                .withScale(tileScale)
                .build(),
        new FrameBuilder(getSubImage(5, 17), 30)
                .withScale(tileScale)
                .build(),
        new FrameBuilder(getSubImage(5, 16), 30)
                .withScale(tileScale)
                .build(),
        new FrameBuilder(getSubImage(5, 15), 65)
                .withScale(tileScale)
                .build()
    };
    MapTileBuilder gemstoneTile = new MapTileBuilder(gemstone);

        CaveTiles.add(gemstoneTile);

        return CaveTiles;
    }

    //returns the entire tileset
    public static ArrayList<MapTileBuilder> getCaveTiles() {
        return CaveTiles;
    }
}
