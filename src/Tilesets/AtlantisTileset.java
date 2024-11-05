package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

public class AtlantisTileset extends Tileset{
    protected static ArrayList<MapTileBuilder> AtlantisTiles;
    static Frame atlantisFloor1;
    static Frame atlantisFloor2;

    public AtlantisTileset() {
        super(ImageLoader.load("TilesetPNGs/atlantisTiles.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {

        AtlantisTiles = new ArrayList<>();

        int index = 0;
        int[] passableTiles1 = new int[]{258,259,262,263,348,349,352,353,354,358,359,360,364,365,366,367,368,369,370,371,372,373,374,375,376,377,378, -1};
        int[] passableTiles2 = new int[]{457,458,461,462,463,467,468,469,473,474,475,476,477,478,479,480,481,482,483,484,485,486,487, -1};
        int[] nonPassableTiles = new int[] {514,521,522,531,532,534,537,538,555,556,557,558,560,562,564,567,568,571,572,574,576,578,581,582,585,586,589,590,593,594,597,598,-1};
        int passIndex1 = 0;
        int passIndex2=0;
        int nonPassIndex=0;

        atlantisFloor1 = new FrameBuilder(getSubImage(0, 0))
            .withScale(tileScale)
            .build();

        atlantisFloor2 = new FrameBuilder(getSubImage(3, 22))
            .withScale(tileScale)
            .build();


        for (int row = 0; row < 8; row++) {
            for (int col = 0; col <34; col++) {

                if ((index >=0 && index <29) || (index >= 123 && index < 152)){
                    Frame atlantisFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder atlantisTile = new MapTileBuilder(atlantisFrame)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    

                    AtlantisTiles.add(atlantisTile);
                }

                if ((index >=35 && index < 123) || (index >=158 && index < 246)) {
                    Frame atlantisFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder atlantisTile = new MapTileBuilder(atlantisFrame)
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                    

                    AtlantisTiles.add(atlantisTile);
                }
                if ((index >=152 && index < 158) || (index >=246 && index <251)) {
                    Frame atlantisFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();

                    // Define the tile type (passable/not passable)
                    MapTileBuilder atlantisTile = new MapTileBuilder(atlantisFloor1)
                    .withTopLayer(atlantisFrame)
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                    

                    AtlantisTiles.add(atlantisTile);
                }

                if ((index >=29 && index < 35) || (index >=251 && index <256)) {
                    Frame atlantisFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();

                    // Define the tile type (passable/not passable)
                    MapTileBuilder atlantisTile = new MapTileBuilder(atlantisFloor1)
                    .withTopLayer(atlantisFrame)
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                    

                    AtlantisTiles.add(atlantisTile);
                }

                
                index++;
            }
        }

        index = 238;

        // fix later
        for (int row = 7; row < 19; row++) {
            for (int col = 0; col < 34; col++) {

                // set 1 tiles (green)
                if ((index >=256 && index <381)) {
                    Frame atlantisFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder atlantisTile = new MapTileBuilder(atlantisFloor1)
                    .withTopLayer(atlantisFrame)
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed

                    AtlantisTiles.add(atlantisTile);
                }

                if (index == passableTiles1[passIndex1] && passIndex1 < passableTiles1.length - 1) {
                    Frame atlantisFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();

                    // Define the tile type (passable/not passable)
                    MapTileBuilder atlantisTile = new MapTileBuilder(atlantisFloor1)
                    .withTopLayer(atlantisFrame)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    
                    AtlantisTiles.set(index, atlantisTile);
                    passIndex1++;
                } 

                // set 2 tiles (blue)
                if (index >= 381 && index <490) {
                    Frame atlantisFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder atlantisTile = new MapTileBuilder(atlantisFloor2)
                    .withTopLayer(atlantisFrame)
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                    

                    AtlantisTiles.add(atlantisTile);
                }

                if ((index == passableTiles2[passIndex2] && passIndex2 < passableTiles2.length-1)) {
                    Frame atlantisFrame = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();

                    // Define the tile type (passable/not passable)
                    MapTileBuilder atlantisTile = new MapTileBuilder(atlantisFloor2)
                    .withTopLayer(atlantisFrame)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    passIndex2++;

                    AtlantisTiles.set(index, atlantisTile);
                } 
                if ((index >= 490 && index < 603) && (index != nonPassableTiles[nonPassIndex])) {
                    Frame atlantisFrame1 = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder atlantisTile1 = new MapTileBuilder(atlantisFloor1)
                    .withTopLayer(atlantisFrame1)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    

                    AtlantisTiles.add(atlantisTile1);

                    Frame atlantisFrame2 = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder atlantisTile2 = new MapTileBuilder(atlantisFloor2)
                    .withTopLayer(atlantisFrame2)
                    .withTileType(TileType.PASSABLE);  // Adjust tile type if needed
                    

                    AtlantisTiles.add(atlantisTile2);
                }
                if ((index == nonPassableTiles[nonPassIndex] && nonPassIndex < nonPassableTiles.length-1)) {
                    Frame atlantisFrame1 = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder atlantisTile1 = new MapTileBuilder(atlantisFloor1)
                    .withTopLayer(atlantisFrame1)
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                    

                    AtlantisTiles.add(atlantisTile1);
                    // AtlantisTiles.set(index, atlantisTile1);

                    Frame atlantisFrame2 = new FrameBuilder(getSubImage(row, col))
                    .withScale(tileScale)
                    .build();
                    
                    // Define the tile type (passable/not passable)
                    MapTileBuilder atlantisTile2 = new MapTileBuilder(atlantisFloor2)
                    .withTopLayer(atlantisFrame2)
                    .withTileType(TileType.NOT_PASSABLE);  // Adjust tile type if needed
                    

                    AtlantisTiles.add(atlantisTile2);

                    // AtlantisTiles.set(index, atlantisTile2);
                    nonPassIndex++;
                } 
                index++;
            }

        }
        Frame[] bubble1 = new Frame[] {
            new FrameBuilder(getSubImage(17, 25),45)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(17, 26),45)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(17, 27),45)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(17, 28),45)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(17, 29),45)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(17, 30),45)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(17, 31),45)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(17, 32),45)
                    .withScale(tileScale)
                    .build()
        };
        MapTileBuilder bubbleTile1 = new MapTileBuilder(atlantisFloor1)
        .withTopLayer(bubble1);
        MapTileBuilder bubbleTile2 = new MapTileBuilder(atlantisFloor2)
        .withTopLayer(bubble1);

        AtlantisTiles.add(bubbleTile1);
        AtlantisTiles.add(bubbleTile2);

        Frame[] bubble2 = new Frame[] {
            new FrameBuilder(getSubImage(17, 33),45)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(18, 0),45)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(18, 1),45)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(18, 2),45)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(18, 3),45)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(18, 4),45)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(18, 5),45)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(18, 6),45)
                    .withScale(tileScale)
                    .build()
        };
        MapTileBuilder bubbleTile3 = new MapTileBuilder(atlantisFloor1)
        .withTopLayer(bubble2);
        MapTileBuilder bubbleTile4 = new MapTileBuilder(atlantisFloor2)
        .withTopLayer(bubble2);

        AtlantisTiles.add(bubbleTile3);
        AtlantisTiles.add(bubbleTile4);

        Frame[] kelptop = new Frame[] {
            new FrameBuilder(getSubImage(18, 7),60)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(18, 9),60)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(18, 11),60)
                    .withScale(tileScale)
                    .build()
        };
        MapTileBuilder kelptop1 = new MapTileBuilder(atlantisFloor1)
        .withTopLayer(kelptop);
        MapTileBuilder kelptop2 = new MapTileBuilder(atlantisFloor2)
        .withTopLayer(kelptop);

        AtlantisTiles.add(kelptop1);
        AtlantisTiles.add(kelptop2);

        Frame[] kelpbottom = new Frame[] {
            new FrameBuilder(getSubImage(18, 8),60)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(18, 10),60)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(18, 12),60)
                    .withScale(tileScale)
                    .build()
        };
        MapTileBuilder kelpbottom1 = new MapTileBuilder(atlantisFloor1)
        .withTopLayer(kelpbottom);
        MapTileBuilder kelpbottom2 = new MapTileBuilder(atlantisFloor2)
        .withTopLayer(kelpbottom);

        AtlantisTiles.add(kelpbottom1);
        AtlantisTiles.add(kelpbottom2);


        return AtlantisTiles;
    }
}
