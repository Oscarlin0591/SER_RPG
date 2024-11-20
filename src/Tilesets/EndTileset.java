package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

public class EndTileset extends Tileset{
    protected EndTileset2 EndTiles2;
    protected static ArrayList<MapTileBuilder> EndTiles;
    static Frame endFloor1;
    static Frame endWater;

    public EndTileset() {
        super(ImageLoader.load("TilesetPNGs/IslandTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        EndTiles = new ArrayList<>();
        EndTiles2 = new EndTileset2();
        int index = 0;

        endWater = new FrameBuilder(getSubImage(0, 1))
        .withScale(tileScale)
        .build();

        endFloor1 = new FrameBuilder(getSubImage(0, 0))
        .withScale(tileScale)
        .build();

        for (int row = 0; row < 24; row++) {
            for (int col = 0; col < 31; col++) {
                Frame endFrame = new FrameBuilder(getSubImage(row, col))
                .withScale(tileScale)
                .build();

                // Define the tile type (passable/not passable)
                MapTileBuilder endTile = new MapTileBuilder(endWater)
                .withTopLayer(endFrame)
                .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                
                // Add tile to RPGTiles or mapTiles
                EndTiles.add(endTile);

                if (index == 0) {// Define the tile type (passable/not passable)
                MapTileBuilder endPassTile = new MapTileBuilder(endFloor1)
                .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                
                // Add tile to RPGTiles or mapTiles
                EndTiles.set(index,endPassTile);
                }

                if (index == 281 || (index >=704 && index <709)) {
                    Frame endPassFrame = new FrameBuilder(getSubImage(row, col))
                .withScale(tileScale)
                .build();

                // Define the tile type (passable/not passable)
                MapTileBuilder endPassTile = new MapTileBuilder(endPassFrame)
                .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                
                // Add tile to RPGTiles or mapTiles
                EndTiles.set(index,endPassTile);
                }

                if ((index >= 364 && index < 368) || (index >= (364+31) && index < (368+31)) || (index >= (364+62) && index < (368+62))) {
                Frame endPassFrame = new FrameBuilder(getSubImage(row, col))
                .withScale(tileScale)
                .build();

                // Define the tile type (passable/not passable)
                MapTileBuilder endPassTile = new MapTileBuilder(endPassFrame)
                .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                
                // Add tile to RPGTiles or mapTiles
                EndTiles.set(index,endPassTile);
                }
                index++;
            }
        }

        for (int i = 1; i < EndTileset2.getEndTiles2().size(); i++) {
            EndTiles.add(EndTileset2.getEndTiles2().get(i));
        }

        return EndTiles;
    }

    public static ArrayList<MapTileBuilder> getEndTiles() {
        return EndTiles;
    }
    
    private class EndTileset2 extends Tileset {
        protected static ArrayList<MapTileBuilder> EndTiles2;
        static Frame endWater;
    
        public EndTileset2() {
            super(ImageLoader.load("TilesetPNGs/IslandTileset2.png"), 16, 16, 3);
            
        }
    
        @Override
        public ArrayList<MapTileBuilder> defineTiles() {
            EndTiles2 = new ArrayList<>();

            endWater = new FrameBuilder(getSubImage(0, 0))
            .withScale(tileScale)
            .build();

            int index=0;
    
            for (int row = 0; row < 10; row++) {
                for (int col = 0; col < 16; col++) {
                    Frame endFrame2 = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder endTile2 = new MapTileBuilder(endWater)
                    .withTopLayer(endFrame2)
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                    
                    // Add tile to RPGTiles or mapTiles
                    EndTiles2.add(endTile2);

                    if (index >=126 && index < (126+31)) {
                        Frame endFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder endTile = new MapTileBuilder(endFrame)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    
                    // Add tile to RPGTiles or mapTiles
                    EndTiles2.set(index, endTile);
                    }

                    index++;
                }
            }
            return EndTiles2;
        }
    
        public static ArrayList<MapTileBuilder> getEndTiles2() {
            return EndTiles2;
        }
    }
}

