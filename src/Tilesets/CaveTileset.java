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

    public CaveTileset() {
        super(ImageLoader.load("CaveTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        //original code is ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();
        CaveTiles = new ArrayList<>();

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 20; col++) {
                Frame caveFrame = new FrameBuilder(getSubImage(row, col))
                .withScale(tileScale)
                .build();

                // Define the tile type (passable/not passable)
                MapTileBuilder fantasyTile = new MapTileBuilder(caveFrame)
                        .withTileType(TileType.PASSABLE);  // Adjust tile type if needed

                // Add tile to RPGTiles or mapTiles
                CaveTiles.add(fantasyTile);
            }
        }

        // flowing river
        Frame[] flowingRiver = new Frame[] {
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
        MapTileBuilder flowingRiverTile = new MapTileBuilder(flowingRiver)
            .withTileType(TileType.NOT_PASSABLE);

            CaveTiles.add(flowingRiverTile);

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
