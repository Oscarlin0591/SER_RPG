package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
//mport GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class CommonTileset extends Tileset {

        public CommonTileset() {
            super(ImageLoader.load("CommonTileset.png"), 16, 16, 5); // 5 columns for a 5x5 grid
        }
    
        @Override
        public ArrayList<MapTileBuilder> defineTiles() {
            ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();
    
            // Grass
            Frame grassFrame = new FrameBuilder(getSubImage(0, 0))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder grassTile = new MapTileBuilder(grassFrame);
            mapTiles.add(grassTile);
    
            // Wooden Path
            Frame woodenPathFrame = new FrameBuilder(getSubImage(1, 0))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder woodenPathTile = new MapTileBuilder(woodenPathFrame);
            mapTiles.add(woodenPathTile);
    
            // Stone Path
            Frame stonePathFrame = new FrameBuilder(getSubImage(2, 0))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder stonePathTile = new MapTileBuilder(stonePathFrame);
            mapTiles.add(stonePathTile);
    
            // Wooden Planks
            Frame woodenPlanksFrame = new FrameBuilder(getSubImage(3, 0))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder woodenPlanksTile = new MapTileBuilder(woodenPlanksFrame);
            mapTiles.add(woodenPlanksTile);
    
            // Sand Path
            Frame sandPathFrame = new FrameBuilder(getSubImage(4, 0))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder sandPathTile = new MapTileBuilder(sandPathFrame);
            mapTiles.add(sandPathTile);
    
            // Water
            Frame waterFrame = new FrameBuilder(getSubImage(0, 1))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder waterTile = new MapTileBuilder(waterFrame);
            mapTiles.add(waterTile);
    
            // Plowed Field
            Frame plowedFieldFrame = new FrameBuilder(getSubImage(1, 1))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder plowedFieldTile = new MapTileBuilder(plowedFieldFrame);
            mapTiles.add(plowedFieldTile);
    
            // Stone Path Variation
            Frame stonePathVarFrame = new FrameBuilder(getSubImage(2, 1))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder stonePathVarTile = new MapTileBuilder(stonePathVarFrame);
            mapTiles.add(stonePathVarTile);
    
            // Tree Hole
            Frame treeHoleFrame = new FrameBuilder(getSubImage(2, 2))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder treeHoleTile = new MapTileBuilder(treeHoleFrame);
            mapTiles.add(treeHoleTile);
    
            // Pink Tiles (Corner Decoration)
            Frame pinkTileFrame = new FrameBuilder(getSubImage(4, 2))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder pinkTile = new MapTileBuilder(pinkTileFrame);
            mapTiles.add(pinkTile);
    
            // Tree Leaves
            Frame treeLeavesFrame = new FrameBuilder(getSubImage(0, 3))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder treeLeavesTile = new MapTileBuilder(treeLeavesFrame);
            mapTiles.add(treeLeavesTile);
    
            // Wooden Path with Grass
            Frame woodenPathWithGrassFrame = new FrameBuilder(getSubImage(1, 3))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder woodenPathWithGrassTile = new MapTileBuilder(woodenPathWithGrassFrame);
            mapTiles.add(woodenPathWithGrassTile);
    
            // Stone Steps
            Frame stoneStepsFrame = new FrameBuilder(getSubImage(2, 3))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder stoneStepsTile = new MapTileBuilder(stoneStepsFrame);
            mapTiles.add(stoneStepsTile);
    
            // Grass Edge
            Frame grassEdgeFrame = new FrameBuilder(getSubImage(3, 3))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder grassEdgeTile = new MapTileBuilder(grassEdgeFrame);
            mapTiles.add(grassEdgeTile);
    
            // Pink Edge (Corner Decoration)
            Frame pinkEdgeFrame = new FrameBuilder(getSubImage(4, 3))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder pinkEdgeTile = new MapTileBuilder(pinkEdgeFrame);
            mapTiles.add(pinkEdgeTile);
    
            // Water Edge
            Frame waterEdgeFrame = new FrameBuilder(getSubImage(0, 4))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder waterEdgeTile = new MapTileBuilder(waterEdgeFrame);
            mapTiles.add(waterEdgeTile);
    
            // Stone Path with Grass
            Frame stonePathWithGrassFrame = new FrameBuilder(getSubImage(1, 4))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder stonePathWithGrassTile = new MapTileBuilder(stonePathWithGrassFrame);
            mapTiles.add(stonePathWithGrassTile);
    
            // Wooden Door (Closed)
            Frame woodenDoorClosedFrame = new FrameBuilder(getSubImage(2, 4))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder woodenDoorClosedTile = new MapTileBuilder(woodenDoorClosedFrame)
                    .withTileType(TileType.NOT_PASSABLE);
            mapTiles.add(woodenDoorClosedTile);
    
            // Wooden Door (Open)
            Frame woodenDoorOpenFrame = new FrameBuilder(getSubImage(3, 4))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder woodenDoorOpenTile = new MapTileBuilder(woodenDoorOpenFrame);
            mapTiles.add(woodenDoorOpenTile);
    
            // Wooden Fence
            Frame woodenFenceFrame = new FrameBuilder(getSubImage(4, 4))
                    .withScale(tileScale)
                    .build();
            MapTileBuilder woodenFenceTile = new MapTileBuilder(woodenFenceFrame)
                    .withTileType(TileType.NOT_PASSABLE);
            mapTiles.add(woodenFenceTile);
    
            return mapTiles;
        }
    }
    //this is the original code (keeping it for when I need to look back on it just in case)
// public class CommonTileset extends Tileset {

//     public CommonTileset() {
//         super(ImageLoader.load("CommonTileset.png"), 16, 16, 3);
//     }

//     @Override
//     public ArrayList<MapTileBuilder> defineTiles() {
//         ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

//         // grass
//         Frame grassFrame = new FrameBuilder(getSubImage(0, 0))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder grassTile = new MapTileBuilder(grassFrame);

//         mapTiles.add(grassTile);

//         // sign
//         Frame signFrame = new FrameBuilder(getSubImage(3, 0))
//                 .withScale(tileScale)
//                 .withBounds(1, 2, 14, 14)
//                 .build();

//         MapTileBuilder signTile = new MapTileBuilder(signFrame)
//                 .withTileType(TileType.NOT_PASSABLE);

//         mapTiles.add(signTile);

//         // sand
//         Frame sandFrame = new FrameBuilder(getSubImage(0, 1))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder sandTile = new MapTileBuilder(sandFrame);

//         mapTiles.add(sandTile);

//         // rock
//         Frame rockFrame = new FrameBuilder(getSubImage(3, 1))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder rockTile = new MapTileBuilder(rockFrame)
//                 .withTileType(TileType.NOT_PASSABLE);

//         mapTiles.add(rockTile);

//         // tree trunk with full hole
//         Frame treeTrunkWithFullHoleFrame = new FrameBuilder(getSubImage(2, 2))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder treeTrunkWithFullHoleTile = new MapTileBuilder(grassFrame)
//                 .withTopLayer(treeTrunkWithFullHoleFrame)
//                 .withTileType(TileType.PASSABLE);

//         mapTiles.add(treeTrunkWithFullHoleTile);

//         // left end branch
//         Frame leftEndBranchFrame = new FrameBuilder(getSubImage(2, 4))
//                 .withScale(tileScale)
//                 .withBounds(0, 6, 16, 4)
//                 .build();

//         MapTileBuilder leftEndBranchTile = new MapTileBuilder(grassFrame)
//                 .withTopLayer(leftEndBranchFrame)
//                 .withTileType(TileType.PASSABLE);

//         mapTiles.add(leftEndBranchTile);

//         // right end branch
//         Frame rightEndBranchFrame = new FrameBuilder(getSubImage(2, 4))
//                 .withScale(tileScale)
//                 .withBounds(0, 6, 16, 4)
//                 .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
//                 .build();

//         MapTileBuilder rightEndBranchTile = new MapTileBuilder(grassFrame)
//                 .withTopLayer(rightEndBranchFrame)
//                 .withTileType(TileType.PASSABLE);

//         mapTiles.add(rightEndBranchTile);
        
//         // tree trunk
//         Frame treeTrunkFrame = new FrameBuilder(getSubImage(1, 0))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder treeTrunkTile = new MapTileBuilder(grassFrame)
//                 .withTopLayer(treeTrunkFrame)
//                 .withTileType(TileType.PASSABLE);

//         mapTiles.add(treeTrunkTile);

//         // tree top leaves
//         Frame treeTopLeavesFrame = new FrameBuilder(getSubImage(1, 1))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder treeTopLeavesTile = new MapTileBuilder(grassFrame)
//                 .withTopLayer(treeTopLeavesFrame)
//                 .withTileType(TileType.PASSABLE);

//         mapTiles.add(treeTopLeavesTile);
        
//         // yellow flower
//         Frame[] yellowFlowerFrames = new Frame[] {
//                 new FrameBuilder(getSubImage(1, 2), 65)
//                     .withScale(tileScale)
//                     .build(),
//                 new FrameBuilder(getSubImage(1, 3), 65)
//                         .withScale(tileScale)
//                         .build(),
//                 new FrameBuilder(getSubImage(1, 2), 65)
//                         .withScale(tileScale)
//                         .build(),
//                 new FrameBuilder(getSubImage(1, 4), 65)
//                         .withScale(tileScale)
//                         .build()
//         };

//         MapTileBuilder yellowFlowerTile = new MapTileBuilder(yellowFlowerFrames);

//         mapTiles.add(yellowFlowerTile);

//         // purple flower
//         Frame[] purpleFlowerFrames = new Frame[] {
//                 new FrameBuilder(getSubImage(0, 2), 65)
//                         .withScale(tileScale)
//                         .build(),
//                 new FrameBuilder(getSubImage(0, 3), 65)
//                         .withScale(tileScale)
//                         .build(),
//                 new FrameBuilder(getSubImage(0, 2), 65)
//                         .withScale(tileScale)
//                         .build(),
//                 new FrameBuilder(getSubImage(0, 4), 65)
//                         .withScale(tileScale)
//                         .build()
//         };

//         MapTileBuilder purpleFlowerTile = new MapTileBuilder(purpleFlowerFrames);

//         mapTiles.add(purpleFlowerTile);

//         // middle branch
//         Frame middleBranchFrame = new FrameBuilder(getSubImage(2, 3))
//                 .withScale(tileScale)
//                 .withBounds(0, 6, 16, 4)
//                 .build();

//         MapTileBuilder middleBranchTile = new MapTileBuilder(grassFrame)
//                 .withTopLayer(middleBranchFrame)
//                 .withTileType(TileType.PASSABLE);

//         mapTiles.add(middleBranchTile);

//         // tree trunk bottom
//         Frame treeTrunkBottomFrame = new FrameBuilder(getSubImage(2, 0))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder treeTrunkBottomTile = new MapTileBuilder(treeTrunkBottomFrame)
//                 .withTileType(TileType.NOT_PASSABLE);

//         mapTiles.add(treeTrunkBottomTile);

//         // mushrooms
//         Frame mushroomFrame = new FrameBuilder(getSubImage(2, 1))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder mushroomTile = new MapTileBuilder(mushroomFrame)
//                 .withTileType(TileType.PASSABLE);

//         mapTiles.add(mushroomTile);


//         // grey rock
//         Frame greyRockFrame = new FrameBuilder(getSubImage(3, 2))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder greyRockTile = new MapTileBuilder(greyRockFrame)
//                 .withTileType(TileType.PASSABLE);

//         mapTiles.add(greyRockTile);

//         // bush
//         Frame bushFrame = new FrameBuilder(getSubImage(3, 3))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder bushTile = new MapTileBuilder(bushFrame)
//                 .withTileType(TileType.NOT_PASSABLE);

//         mapTiles.add(bushTile);

//         // house body
//         Frame houseBodyFrame = new FrameBuilder(getSubImage(3, 4))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder houseBodyTile = new MapTileBuilder(houseBodyFrame)
//                 .withTileType(TileType.NOT_PASSABLE);

//         mapTiles.add(houseBodyTile);

//         // house roof body
//         Frame houseRoofBodyFrame = new FrameBuilder(getSubImage(4, 0))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder houseRoofBodyTile = new MapTileBuilder(grassFrame)
//                 .withTopLayer(houseRoofBodyFrame)
//                 .withTileType(TileType.PASSABLE);

//         mapTiles.add(houseRoofBodyTile);

//         // left house roof
//         Frame leftHouseRoofFrame = new FrameBuilder(getSubImage(4, 1))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder leftHouseRoofTile = new MapTileBuilder(grassFrame)
//                 .withTopLayer(leftHouseRoofFrame)
//                 .withTileType(TileType.PASSABLE);

//         mapTiles.add(leftHouseRoofTile);

//         // right house roof
//         Frame rightHouseRoofFrame = new FrameBuilder(getSubImage(4, 1))
//                 .withScale(tileScale)
//                 .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
//                 .build();

//         MapTileBuilder rightHouseRoofTile = new MapTileBuilder(grassFrame)
//                 .withTopLayer(rightHouseRoofFrame)
//                 .withTileType(TileType.PASSABLE);

//         mapTiles.add(rightHouseRoofTile);

//         // left window
//         Frame leftWindowFrame = new FrameBuilder(getSubImage(4, 2))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder leftWindowTile = new MapTileBuilder(leftWindowFrame)
//                 .withTileType(TileType.NOT_PASSABLE);

//         mapTiles.add(leftWindowTile);

//         // right window
//         Frame rightWindowFrame = new FrameBuilder(getSubImage(4, 2))
//                 .withScale(tileScale)
//                 .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
//                 .build();

//         MapTileBuilder rightWindowTile = new MapTileBuilder(rightWindowFrame)
//                 .withTileType(TileType.NOT_PASSABLE);

//         mapTiles.add(rightWindowTile);

//         // door
//         Frame doorFrame = new FrameBuilder(getSubImage(4, 3))
//                 .withScale(tileScale)
//                 .build();

//         MapTileBuilder doorTile = new MapTileBuilder(doorFrame)
//                 .withTileType(TileType.NOT_PASSABLE);

//         mapTiles.add(doorTile);

//         // top water
//         Frame[] topWaterFrames = new Frame[] {
//             new FrameBuilder(getSubImage(5, 0), 65)
//                     .withScale(tileScale)
//                     .build(),
//             new FrameBuilder(getSubImage(5, 1), 65)
//                     .withScale(tileScale)
//                     .build(),
//             new FrameBuilder(getSubImage(5, 2), 65)
//                     .withScale(tileScale)
//                     .build(),
//             new FrameBuilder(getSubImage(5, 1), 65)
//                     .withScale(tileScale)
//                     .build(),
//             new FrameBuilder(getSubImage(5, 0), 65)
//                     .withScale(tileScale)
//                     .build(),
//             new FrameBuilder(getSubImage(5, 3), 65)
//                     .withScale(tileScale)
//                     .build(),
//             new FrameBuilder(getSubImage(5, 4), 65)
//                     .withScale(tileScale)
//                     .build(),
//             new FrameBuilder(getSubImage(5, 3), 65)
//                     .withScale(tileScale)
//                     .build()
//         };

//         MapTileBuilder topWaterTile = new MapTileBuilder(topWaterFrames)
//                 .withTileType(TileType.NOT_PASSABLE);

//         mapTiles.add(topWaterTile);


//         return mapTiles;
//     }

    
// }
