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

    public ShipwreckTileset() {
        super(ImageLoader.load("TilesetPNGs/Shipwreck.png"), 32,32, 1);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ShipwreckTiles = new ArrayList<>();
        int index = 0;

        for (int row = 0; row < 43; row++) {
            for (int col = 0; col < 20; col++) {
                Frame ShipwreckFrame = new FrameBuilder(getSubImage(row, col))
                .withScale(tileScale)
                .build();

                // Define the tile type (passable/not passable)
                MapTileBuilder ShipwreckTile = new MapTileBuilder(ShipwreckFrame)
                .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                
                // Add tile to RPGTiles or mapTiles
                ShipwreckTiles.add(ShipwreckTile);
            }
        }

        return ShipwreckTiles;
    }
    
}
