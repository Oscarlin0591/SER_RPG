package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file

    //this is the original code (keeping it for when I need to look back on it just in case)
public class StartTileset extends Tileset {

    public StartTileset() {
        super(ImageLoader.load("TilesetPNGs/beachtiles.png"), 32, 32, 1);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 12; j++) {
                Frame startIslandFrame = new FrameBuilder(getSubImage(i, j))
                        .withScale(tileScale)
                        .build();
        
                MapTileBuilder startIslandTile = new MapTileBuilder(startIslandFrame);
        
                mapTiles.add(startIslandTile);
            }
        }

        Frame waterFrame = new FrameBuilder(getSubImage(7,5))
            .withScale(tileScale)
            .build();

        MapTileBuilder waterTile = new MapTileBuilder(waterFrame)
            .withTileType(TileType.NOT_PASSABLE);


        mapTiles.add(waterTile);

        return mapTiles;
    }

    
}
