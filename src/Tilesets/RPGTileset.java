package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

// This class contains the tileset for our Fantasy RPG tiles
public class RPGTileset extends Tileset {

        protected static ArrayList<MapTileBuilder> RPGTiles;

        public RPGTileset() { 
                super(ImageLoader.load("FantasyTileset.png"), 16, 16, 3); // loads png
        }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        //original code is ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();
        RPGTiles = new ArrayList<>();

        Frame fantasyFrame00 = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile00 = new MapTileBuilder(fantasyFrame00)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile00);

        Frame fantasyFrame01 = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile01 = new MapTileBuilder(fantasyFrame01)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile01);

        Frame fantasyFrame02 = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile02 = new MapTileBuilder(fantasyFrame02)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile02);

        Frame fantasyFrame03 = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile03 = new MapTileBuilder(fantasyFrame03)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile03);

        // Continue for all 12 rows and 16 columns

        Frame fantasyFrame04 = new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile04 = new MapTileBuilder(fantasyFrame04)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile04);

        Frame fantasyFrame05 = new FrameBuilder(getSubImage(0, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile05 = new MapTileBuilder(fantasyFrame05)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile05);

        Frame fantasyFrame06 = new FrameBuilder(getSubImage(0, 6))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile06 = new MapTileBuilder(fantasyFrame06)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile06);

        Frame fantasyFrame07 = new FrameBuilder(getSubImage(0, 7))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile07 = new MapTileBuilder(fantasyFrame07)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile07);

        Frame fantasyFrame08 = new FrameBuilder(getSubImage(0, 8))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile08 = new MapTileBuilder(fantasyFrame08)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile08);

        Frame fantasyFrame09 = new FrameBuilder(getSubImage(0, 9))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile09 = new MapTileBuilder(fantasyFrame09)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile09);

        Frame fantasyFrame010 = new FrameBuilder(getSubImage(0, 10))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile010 = new MapTileBuilder(fantasyFrame010)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile010);

        Frame fantasyFrame011 = new FrameBuilder(getSubImage(0, 11))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile011 = new MapTileBuilder(fantasyFrame011)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile011);

        Frame fantasyFrame012 = new FrameBuilder(getSubImage(0, 12))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile012 = new MapTileBuilder(fantasyFrame012)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile012);

        Frame fantasyFrame013 = new FrameBuilder(getSubImage(0, 13))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile013 = new MapTileBuilder(fantasyFrame013)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile013);

        Frame fantasyFrame014 = new FrameBuilder(getSubImage(0, 14))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile014 = new MapTileBuilder(fantasyFrame014)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile014);

        Frame fantasyFrame015 = new FrameBuilder(getSubImage(0, 15))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile015 = new MapTileBuilder(fantasyFrame015)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile015);

        // Row 1
        Frame fantasyFrame10 = new FrameBuilder(getSubImage(1, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile10 = new MapTileBuilder(fantasyFrame10)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile10);

        Frame fantasyFrame11 = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile11 = new MapTileBuilder(fantasyFrame11)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile11);

        Frame fantasyFrame12 = new FrameBuilder(getSubImage(1, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile12 = new MapTileBuilder(fantasyFrame12)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile12);

        Frame fantasyFrame13 = new FrameBuilder(getSubImage(1, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile13 = new MapTileBuilder(fantasyFrame13)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile13);

        Frame fantasyFrame14 = new FrameBuilder(getSubImage(1, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile14 = new MapTileBuilder(fantasyFrame14)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile14);

        Frame fantasyFrame15 = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile15 = new MapTileBuilder(fantasyFrame15)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile15);

        Frame fantasyFrame16 = new FrameBuilder(getSubImage(1, 6))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile16 = new MapTileBuilder(fantasyFrame16)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile16);

        Frame fantasyFrame17 = new FrameBuilder(getSubImage(1, 7))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile17 = new MapTileBuilder(fantasyFrame17)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile17);

        Frame fantasyFrame18 = new FrameBuilder(getSubImage(1, 8))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile18 = new MapTileBuilder(fantasyFrame18)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile18);

        Frame fantasyFrame19 = new FrameBuilder(getSubImage(1, 9))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile19 = new MapTileBuilder(fantasyFrame19)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile19);

        Frame fantasyFrame110 = new FrameBuilder(getSubImage(1, 10))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile110 = new MapTileBuilder(fantasyFrame110)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile110);

        Frame fantasyFrame111 = new FrameBuilder(getSubImage(1, 11))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile111 = new MapTileBuilder(fantasyFrame111)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile111);

        Frame fantasyFrame112 = new FrameBuilder(getSubImage(1, 12))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile112 = new MapTileBuilder(fantasyFrame112)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile112);

        Frame fantasyFrame113 = new FrameBuilder(getSubImage(1, 13))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile113 = new MapTileBuilder(fantasyFrame113)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile113);

        Frame fantasyFrame114 = new FrameBuilder(getSubImage(1, 14))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile114 = new MapTileBuilder(fantasyFrame114)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile114);

        Frame fantasyFrame115 = new FrameBuilder(getSubImage(1, 15))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile115 = new MapTileBuilder(fantasyFrame115)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile115);


        // Row 2
        Frame fantasyFrame20 = new FrameBuilder(getSubImage(2, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile20 = new MapTileBuilder(fantasyFrame20)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile20);

        Frame fantasyFrame21 = new FrameBuilder(getSubImage(2, 1))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile21 = new MapTileBuilder(fantasyFrame21)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile21);

        Frame fantasyFrame22 = new FrameBuilder(getSubImage(2, 2))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile22 = new MapTileBuilder(fantasyFrame22)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile22);

        Frame fantasyFrame23 = new FrameBuilder(getSubImage(2, 3))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile23 = new MapTileBuilder(fantasyFrame23)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile23);

        Frame fantasyFrame24 = new FrameBuilder(getSubImage(2, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile24 = new MapTileBuilder(fantasyFrame24)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile24);

        Frame fantasyFrame25 = new FrameBuilder(getSubImage(2, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile25 = new MapTileBuilder(fantasyFrame25)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile25);

        Frame fantasyFrame26 = new FrameBuilder(getSubImage(2, 6))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile26 = new MapTileBuilder(fantasyFrame26)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile26);

        Frame fantasyFrame27 = new FrameBuilder(getSubImage(2, 7))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile27 = new MapTileBuilder(fantasyFrame27)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile27);

        Frame fantasyFrame28 = new FrameBuilder(getSubImage(2, 8))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile28 = new MapTileBuilder(fantasyFrame28)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile28);

        Frame fantasyFrame29 = new FrameBuilder(getSubImage(2, 9))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile29 = new MapTileBuilder(fantasyFrame29)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile29);

        Frame fantasyFrame210 = new FrameBuilder(getSubImage(2, 10))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile210 = new MapTileBuilder(fantasyFrame210)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile210);

        Frame fantasyFrame211 = new FrameBuilder(getSubImage(2, 11))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile211 = new MapTileBuilder(fantasyFrame211)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile211);

        Frame fantasyFrame212 = new FrameBuilder(getSubImage(2, 12))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile212 = new MapTileBuilder(fantasyFrame212)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile212);

        Frame fantasyFrame213 = new FrameBuilder(getSubImage(2, 13))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile213 = new MapTileBuilder(fantasyFrame213)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile213);

        Frame fantasyFrame214 = new FrameBuilder(getSubImage(2, 14))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile214 = new MapTileBuilder(fantasyFrame214)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile214);

        Frame fantasyFrame215 = new FrameBuilder(getSubImage(2, 15))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile215 = new MapTileBuilder(fantasyFrame215)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile215);


        // Row 3
        Frame fantasyFrame30 = new FrameBuilder(getSubImage(3, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile30 = new MapTileBuilder(fantasyFrame30)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile30);

        Frame fantasyFrame31 = new FrameBuilder(getSubImage(3, 1))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile31 = new MapTileBuilder(fantasyFrame31)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile31);

        Frame fantasyFrame32 = new FrameBuilder(getSubImage(3, 2))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile32 = new MapTileBuilder(fantasyFrame32)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile32);

        Frame fantasyFrame33 = new FrameBuilder(getSubImage(3, 3))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile33 = new MapTileBuilder(fantasyFrame33)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile33);

        Frame fantasyFrame34 = new FrameBuilder(getSubImage(3, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile34 = new MapTileBuilder(fantasyFrame34)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile34);

        Frame fantasyFrame35 = new FrameBuilder(getSubImage(3, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile35 = new MapTileBuilder(fantasyFrame35)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile35);

        Frame fantasyFrame36 = new FrameBuilder(getSubImage(3, 6))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile36 = new MapTileBuilder(fantasyFrame36)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile36);

        Frame fantasyFrame37 = new FrameBuilder(getSubImage(3, 7))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile37 = new MapTileBuilder(fantasyFrame37)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile37);

        Frame fantasyFrame38 = new FrameBuilder(getSubImage(3, 8))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile38 = new MapTileBuilder(fantasyFrame38)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile38);

        Frame fantasyFrame39 = new FrameBuilder(getSubImage(3, 9))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile39 = new MapTileBuilder(fantasyFrame39)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile39);

        Frame fantasyFrame310 = new FrameBuilder(getSubImage(3, 10))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile310 = new MapTileBuilder(fantasyFrame310)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile310);

        Frame fantasyFrame311 = new FrameBuilder(getSubImage(3, 11))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile311 = new MapTileBuilder(fantasyFrame311)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile311);

        Frame fantasyFrame312 = new FrameBuilder(getSubImage(3, 12))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile312 = new MapTileBuilder(fantasyFrame312)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile312);

        Frame fantasyFrame313 = new FrameBuilder(getSubImage(3, 13))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile313 = new MapTileBuilder(fantasyFrame313)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile313);

        Frame fantasyFrame314 = new FrameBuilder(getSubImage(3, 14))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile314 = new MapTileBuilder(fantasyFrame314)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile314);

        Frame fantasyFrame315 = new FrameBuilder(getSubImage(3, 15))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile315 = new MapTileBuilder(fantasyFrame315)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile315);


        // Row 4
        Frame fantasyFrame40 = new FrameBuilder(getSubImage(4, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile40 = new MapTileBuilder(fantasyFrame40)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile40);

        Frame fantasyFrame41 = new FrameBuilder(getSubImage(4, 1))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile41 = new MapTileBuilder(fantasyFrame41)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile41);

        Frame fantasyFrame42 = new FrameBuilder(getSubImage(4, 2))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile42 = new MapTileBuilder(fantasyFrame42)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile42);

        Frame fantasyFrame43 = new FrameBuilder(getSubImage(4, 3))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile43 = new MapTileBuilder(fantasyFrame43)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile43);

        Frame fantasyFrame44 = new FrameBuilder(getSubImage(4, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile44 = new MapTileBuilder(fantasyFrame44)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile44);

        Frame fantasyFrame45 = new FrameBuilder(getSubImage(4, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile45 = new MapTileBuilder(fantasyFrame45)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile45);

        Frame fantasyFrame46 = new FrameBuilder(getSubImage(4, 6))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile46 = new MapTileBuilder(fantasyFrame46)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile46);

        Frame fantasyFrame47 = new FrameBuilder(getSubImage(4, 7))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile47 = new MapTileBuilder(fantasyFrame47)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile47);

        Frame fantasyFrame48 = new FrameBuilder(getSubImage(4, 8))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile48 = new MapTileBuilder(fantasyFrame48)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile48);

        Frame fantasyFrame49 = new FrameBuilder(getSubImage(4, 9))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile49 = new MapTileBuilder(fantasyFrame49)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile49);

        Frame fantasyFrame410 = new FrameBuilder(getSubImage(4, 10))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile410 = new MapTileBuilder(fantasyFrame410)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile410);

        Frame fantasyFrame411 = new FrameBuilder(getSubImage(4, 11))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile411 = new MapTileBuilder(fantasyFrame411)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile411);

        Frame fantasyFrame412 = new FrameBuilder(getSubImage(4, 12))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile412 = new MapTileBuilder(fantasyFrame412)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile412);

        Frame fantasyFrame413 = new FrameBuilder(getSubImage(4, 13))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile413 = new MapTileBuilder(fantasyFrame413)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile413);

        Frame fantasyFrame414 = new FrameBuilder(getSubImage(4, 14))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile414 = new MapTileBuilder(fantasyFrame414)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile414);

        Frame fantasyFrame415 = new FrameBuilder(getSubImage(4, 15))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile415 = new MapTileBuilder(fantasyFrame415)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile415);

        // Row 5
        Frame fantasyFrame50 = new FrameBuilder(getSubImage(5, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile50 = new MapTileBuilder(fantasyFrame50)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile50);

        Frame fantasyFrame51 = new FrameBuilder(getSubImage(5, 1))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile51 = new MapTileBuilder(fantasyFrame51)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile51);

        Frame fantasyFrame52 = new FrameBuilder(getSubImage(5, 2))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile52 = new MapTileBuilder(fantasyFrame52)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile52);

        Frame fantasyFrame53 = new FrameBuilder(getSubImage(5, 3))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile53 = new MapTileBuilder(fantasyFrame53)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile53);

        Frame fantasyFrame54 = new FrameBuilder(getSubImage(5, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile54 = new MapTileBuilder(fantasyFrame54)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile54);

        Frame fantasyFrame55 = new FrameBuilder(getSubImage(5, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile55 = new MapTileBuilder(fantasyFrame55)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile55);

        Frame fantasyFrame56 = new FrameBuilder(getSubImage(5, 6))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile56 = new MapTileBuilder(fantasyFrame56)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile56);

        Frame fantasyFrame57 = new FrameBuilder(getSubImage(5, 7))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile57 = new MapTileBuilder(fantasyFrame57)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile57);

        Frame fantasyFrame58 = new FrameBuilder(getSubImage(5, 8))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile58 = new MapTileBuilder(fantasyFrame58)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile58);

        Frame fantasyFrame59 = new FrameBuilder(getSubImage(5, 9))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile59 = new MapTileBuilder(fantasyFrame59)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile59);

        Frame fantasyFrame510 = new FrameBuilder(getSubImage(5, 10))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile510 = new MapTileBuilder(fantasyFrame510)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile510);

        Frame fantasyFrame511 = new FrameBuilder(getSubImage(5, 11))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile511 = new MapTileBuilder(fantasyFrame511)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile511);

        Frame fantasyFrame512 = new FrameBuilder(getSubImage(5, 12))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile512 = new MapTileBuilder(fantasyFrame512)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile512);

        Frame fantasyFrame513 = new FrameBuilder(getSubImage(5, 13))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile513 = new MapTileBuilder(fantasyFrame513)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile513);

        Frame fantasyFrame514 = new FrameBuilder(getSubImage(5, 14))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile514 = new MapTileBuilder(fantasyFrame514)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile514);

        Frame fantasyFrame515 = new FrameBuilder(getSubImage(5, 15))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile515 = new MapTileBuilder(fantasyFrame515)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile515);

        // Row 6
        Frame fantasyFrame60 = new FrameBuilder(getSubImage(6, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile60 = new MapTileBuilder(fantasyFrame60)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile60);

        Frame fantasyFrame61 = new FrameBuilder(getSubImage(6, 1))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile61 = new MapTileBuilder(fantasyFrame61)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile61);

        Frame fantasyFrame62 = new FrameBuilder(getSubImage(6, 2))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile62 = new MapTileBuilder(fantasyFrame62)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile62);

        Frame fantasyFrame63 = new FrameBuilder(getSubImage(6, 3))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile63 = new MapTileBuilder(fantasyFrame63)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile63);

        Frame fantasyFrame64 = new FrameBuilder(getSubImage(6, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile64 = new MapTileBuilder(fantasyFrame64)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile64);

        Frame fantasyFrame65 = new FrameBuilder(getSubImage(6, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile65 = new MapTileBuilder(fantasyFrame65)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile65);

        Frame fantasyFrame66 = new FrameBuilder(getSubImage(6, 6))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile66 = new MapTileBuilder(fantasyFrame66)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile66);

        Frame fantasyFrame67 = new FrameBuilder(getSubImage(6, 7))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile67 = new MapTileBuilder(fantasyFrame67)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile67);

        Frame fantasyFrame68 = new FrameBuilder(getSubImage(6, 8))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile68 = new MapTileBuilder(fantasyFrame68)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile68);

        Frame fantasyFrame69 = new FrameBuilder(getSubImage(6, 9))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile69 = new MapTileBuilder(fantasyFrame69)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile69);

        Frame fantasyFrame610 = new FrameBuilder(getSubImage(6, 10))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile610 = new MapTileBuilder(fantasyFrame610)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile610);

        Frame fantasyFrame611 = new FrameBuilder(getSubImage(6, 11))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile611 = new MapTileBuilder(fantasyFrame611)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile611);

        Frame fantasyFrame612 = new FrameBuilder(getSubImage(6, 12))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile612 = new MapTileBuilder(fantasyFrame612)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile612);

        Frame fantasyFrame613 = new FrameBuilder(getSubImage(6, 13))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile613 = new MapTileBuilder(fantasyFrame613)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile613);

        Frame fantasyFrame614 = new FrameBuilder(getSubImage(6, 14))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile614 = new MapTileBuilder(fantasyFrame614)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile614);

        Frame fantasyFrame615 = new FrameBuilder(getSubImage(6, 15))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile615 = new MapTileBuilder(fantasyFrame615)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile615);

        // Row 7
        Frame fantasyFrame70 = new FrameBuilder(getSubImage(7, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile70 = new MapTileBuilder(fantasyFrame70)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile70);

        Frame fantasyFrame71 = new FrameBuilder(getSubImage(7, 1))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile71 = new MapTileBuilder(fantasyFrame71)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile71);

        Frame fantasyFrame72 = new FrameBuilder(getSubImage(7, 2))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile72 = new MapTileBuilder(fantasyFrame72)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile72);

        Frame fantasyFrame73 = new FrameBuilder(getSubImage(7, 3))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile73 = new MapTileBuilder(fantasyFrame73)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile73);

        Frame fantasyFrame74 = new FrameBuilder(getSubImage(7, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile74 = new MapTileBuilder(fantasyFrame74)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile74);

        Frame fantasyFrame75 = new FrameBuilder(getSubImage(7, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile75 = new MapTileBuilder(fantasyFrame75)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile75);

        Frame fantasyFrame76 = new FrameBuilder(getSubImage(7, 6))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile76 = new MapTileBuilder(fantasyFrame76)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile76);

        Frame fantasyFrame77 = new FrameBuilder(getSubImage(7, 7))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile77 = new MapTileBuilder(fantasyFrame77)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile77);

        Frame fantasyFrame78 = new FrameBuilder(getSubImage(7, 8))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile78 = new MapTileBuilder(fantasyFrame78)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile78);

        Frame fantasyFrame79 = new FrameBuilder(getSubImage(7, 9))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile79 = new MapTileBuilder(fantasyFrame79)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile79);

        Frame fantasyFrame710 = new FrameBuilder(getSubImage(7, 10))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile710 = new MapTileBuilder(fantasyFrame710)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile710);

        Frame fantasyFrame711 = new FrameBuilder(getSubImage(7, 11))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile711 = new MapTileBuilder(fantasyFrame711)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile711);

        Frame fantasyFrame712 = new FrameBuilder(getSubImage(7, 12))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile712 = new MapTileBuilder(fantasyFrame712)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile712);

        Frame fantasyFrame713 = new FrameBuilder(getSubImage(7, 13))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile713 = new MapTileBuilder(fantasyFrame713)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile713);


        Frame fantasyFrame714 = new FrameBuilder(getSubImage(7, 14))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile714 = new MapTileBuilder(fantasyFrame714)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile714);

        Frame fantasyFrame715 = new FrameBuilder(getSubImage(7, 15))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile715 = new MapTileBuilder(fantasyFrame715)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile715);

        // Row 8
        Frame fantasyFrame80 = new FrameBuilder(getSubImage(8, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile80 = new MapTileBuilder(fantasyFrame80)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile80);

        Frame fantasyFrame81 = new FrameBuilder(getSubImage(8, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile81 = new MapTileBuilder(fantasyFrame81)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile81);

        Frame fantasyFrame82 = new FrameBuilder(getSubImage(8, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile82 = new MapTileBuilder(fantasyFrame82)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile82);

        Frame fantasyFrame83 = new FrameBuilder(getSubImage(8, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile83 = new MapTileBuilder(fantasyFrame83)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile83);

        Frame fantasyFrame84 = new FrameBuilder(getSubImage(8, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile84 = new MapTileBuilder(fantasyFrame84)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile84);

        Frame fantasyFrame85 = new FrameBuilder(getSubImage(8, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile85 = new MapTileBuilder(fantasyFrame85)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile85);

        Frame fantasyFrame86 = new FrameBuilder(getSubImage(8, 6))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile86 = new MapTileBuilder(fantasyFrame86)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile86);

        Frame fantasyFrame87 = new FrameBuilder(getSubImage(8, 7))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile87 = new MapTileBuilder(fantasyFrame87)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile87);

        Frame fantasyFrame88 = new FrameBuilder(getSubImage(8, 8))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile88 = new MapTileBuilder(fantasyFrame88)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile88);

        Frame fantasyFrame89 = new FrameBuilder(getSubImage(8, 9))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile89 = new MapTileBuilder(fantasyFrame89)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile89);

        Frame fantasyFrame810 = new FrameBuilder(getSubImage(8, 10))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile810 = new MapTileBuilder(fantasyFrame810)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile810);

        Frame fantasyFrame811 = new FrameBuilder(getSubImage(8, 11))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile811 = new MapTileBuilder(fantasyFrame811)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile811);

        Frame fantasyFrame812 = new FrameBuilder(getSubImage(8, 12))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile812 = new MapTileBuilder(fantasyFrame812)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile812);

        Frame fantasyFrame813 = new FrameBuilder(getSubImage(8, 13))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile813 = new MapTileBuilder(fantasyFrame813)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile813);

        Frame fantasyFrame814 = new FrameBuilder(getSubImage(8, 14))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile814 = new MapTileBuilder(fantasyFrame814)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile814);


        Frame fantasyFrame815 = new FrameBuilder(getSubImage(8, 14))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile815 = new MapTileBuilder(fantasyFrame815)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile815);


        // Row 9
        Frame fantasyFrame90 = new FrameBuilder(getSubImage(9, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile90 = new MapTileBuilder(fantasyFrame90)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile90);

        Frame fantasyFrame91 = new FrameBuilder(getSubImage(9, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile91 = new MapTileBuilder(fantasyFrame91)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile91);

        Frame fantasyFrame92 = new FrameBuilder(getSubImage(9, 2))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile92 = new MapTileBuilder(fantasyFrame92)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile92);

        Frame fantasyFrame93 = new FrameBuilder(getSubImage(9, 3))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile93 = new MapTileBuilder(fantasyFrame93)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile93);

        Frame fantasyFrame94 = new FrameBuilder(getSubImage(9, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile94 = new MapTileBuilder(fantasyFrame94)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile94);

        Frame fantasyFrame95 = new FrameBuilder(getSubImage(9, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile95 = new MapTileBuilder(fantasyFrame95)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile95);

        Frame fantasyFrame96 = new FrameBuilder(getSubImage(9, 6))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile96 = new MapTileBuilder(fantasyFrame96)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile96);

        Frame fantasyFrame97 = new FrameBuilder(getSubImage(9, 7))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile97 = new MapTileBuilder(fantasyFrame97)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile97);

        Frame fantasyFrame98 = new FrameBuilder(getSubImage(9, 8))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile98 = new MapTileBuilder(fantasyFrame98)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile98);

        Frame fantasyFrame99 = new FrameBuilder(getSubImage(9, 9))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile99 = new MapTileBuilder(fantasyFrame99)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile99);

        Frame fantasyFrame910 = new FrameBuilder(getSubImage(9, 10))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile910 = new MapTileBuilder(fantasyFrame910)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile910);

        Frame fantasyFrame911 = new FrameBuilder(getSubImage(9, 11))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile911 = new MapTileBuilder(fantasyFrame911)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile911);

        Frame fantasyFrame912 = new FrameBuilder(getSubImage(9, 12))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile912 = new MapTileBuilder(fantasyFrame912)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile912);

        Frame fantasyFrame913 = new FrameBuilder(getSubImage(9, 13))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile913 = new MapTileBuilder(fantasyFrame913)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile913);

        Frame fantasyFrame914 = new FrameBuilder(getSubImage(9, 14))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile914 = new MapTileBuilder(fantasyFrame914)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile914);

        Frame fantasyFrame915 = new FrameBuilder(getSubImage(9, 15))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile915 = new MapTileBuilder(fantasyFrame915)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile915);

        // Row 10
        Frame fantasyFrame100 = new FrameBuilder(getSubImage(10, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile100 = new MapTileBuilder(fantasyFrame100)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile100);

        Frame fantasyFrame101 = new FrameBuilder(getSubImage(10, 1))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile101 = new MapTileBuilder(fantasyFrame101)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile101);

        Frame fantasyFrame102 = new FrameBuilder(getSubImage(10, 2))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile102 = new MapTileBuilder(fantasyFrame102)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile102);

        Frame fantasyFrame103 = new FrameBuilder(getSubImage(10, 3))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile103 = new MapTileBuilder(fantasyFrame103)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile103);

        Frame fantasyFrame104 = new FrameBuilder(getSubImage(10, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile104 = new MapTileBuilder(fantasyFrame104)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile104);

        Frame fantasyFrame105 = new FrameBuilder(getSubImage(10, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile105 = new MapTileBuilder(fantasyFrame105)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile105);

        Frame fantasyFrame106 = new FrameBuilder(getSubImage(10, 6))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile106 = new MapTileBuilder(fantasyFrame106)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile106);

        Frame fantasyFrame107 = new FrameBuilder(getSubImage(10, 7))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile107 = new MapTileBuilder(fantasyFrame107)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile107);

        Frame fantasyFrame108 = new FrameBuilder(getSubImage(10, 8))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile108 = new MapTileBuilder(fantasyFrame108)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile108);

        Frame fantasyFrame109 = new FrameBuilder(getSubImage(10, 9))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile109 = new MapTileBuilder(fantasyFrame109)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile109);

        Frame fantasyFrame1010 = new FrameBuilder(getSubImage(10, 10))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile1010 = new MapTileBuilder(fantasyFrame1010)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile1010);

        Frame fantasyFrame1011 = new FrameBuilder(getSubImage(10, 11))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile1011 = new MapTileBuilder(fantasyFrame1011)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile1011);

        Frame fantasyFrame1012 = new FrameBuilder(getSubImage(10, 12))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile1012 = new MapTileBuilder(fantasyFrame1012)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile1012);

        Frame fantasyFrame1013 = new FrameBuilder(getSubImage(10, 13))
        .withScale(tileScale)
        .build();

        MapTileBuilder fantasyTile1013 = new MapTileBuilder(fantasyFrame1013)
        .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile1013);

        Frame fantasyFrame1014 = new FrameBuilder(getSubImage(10, 14))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile1014 = new MapTileBuilder(fantasyFrame1014)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile1014);

        Frame fantasyFrame1015 = new FrameBuilder(getSubImage(10, 15))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile1015 = new MapTileBuilder(fantasyFrame1015)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile1015);

        // Row 11
        Frame fantasyFrame11_0 = new FrameBuilder(getSubImage(11, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile11_0 = new MapTileBuilder(fantasyFrame11_0)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile11_0);

        Frame fantasyFrame11_1 = new FrameBuilder(getSubImage(11, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile11_1 = new MapTileBuilder(fantasyFrame11_1)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile11_1);

        Frame fantasyFrame11_2 = new FrameBuilder(getSubImage(11, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile11_2 = new MapTileBuilder(fantasyFrame11_2)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile11_2);

        Frame fantasyFrame11_3 = new FrameBuilder(getSubImage(11, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile11_3 = new MapTileBuilder(fantasyFrame11_3)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile11_3);

        Frame fantasyFrame11_4 = new FrameBuilder(getSubImage(11, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile11_4 = new MapTileBuilder(fantasyFrame11_4)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile11_4);

        Frame fantasyFrame11_5 = new FrameBuilder(getSubImage(11, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile11_5 = new MapTileBuilder(fantasyFrame11_5)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile11_5);

        Frame fantasyFrame11_6 = new FrameBuilder(getSubImage(11, 6))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile11_6 = new MapTileBuilder(fantasyFrame11_6)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile11_6);

        Frame fantasyFrame11_7 = new FrameBuilder(getSubImage(11, 7))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile11_7 = new MapTileBuilder(fantasyFrame11_7)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile11_7);

        Frame fantasyFrame11_8 = new FrameBuilder(getSubImage(11, 8))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile11_8 = new MapTileBuilder(fantasyFrame11_8)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile11_8);

        Frame fantasyFrame11_9 = new FrameBuilder(getSubImage(11, 9))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile11_9 = new MapTileBuilder(fantasyFrame11_9)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile11_9);

        Frame fantasyFrame1110 = new FrameBuilder(getSubImage(11, 10))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile1110 = new MapTileBuilder(fantasyFrame1110)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile1110);

        Frame fantasyFrame1111 = new FrameBuilder(getSubImage(11, 11))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile1111 = new MapTileBuilder(fantasyFrame1111)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile1111);

        Frame fantasyFrame1112 = new FrameBuilder(getSubImage(11, 12))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile1112 = new MapTileBuilder(fantasyFrame1112)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile1112);

        Frame fantasyFrame1113 = new FrameBuilder(getSubImage(11, 13))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile1113 = new MapTileBuilder(fantasyFrame1113)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile1113);

        Frame fantasyFrame1114 = new FrameBuilder(getSubImage(11, 14))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile1114 = new MapTileBuilder(fantasyFrame1114)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile1114);

        Frame fantasyFrame1115 = new FrameBuilder(getSubImage(11, 15))
                .withScale(tileScale)
                .build();

        MapTileBuilder fantasyTile1115 = new MapTileBuilder(fantasyFrame1115)
                .withTileType(TileType.PASSABLE);

        RPGTiles.add(fantasyTile1115);


        return RPGTiles;
    }
    public static ArrayList<MapTileBuilder> getRPGTiles() {
        return RPGTiles;
    }
}
