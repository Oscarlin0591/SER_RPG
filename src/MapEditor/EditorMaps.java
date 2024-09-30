package MapEditor;

import Level.Map;
import Maps.BattleMap;
import Maps.GameMap;
import Maps.TestMap;
import Maps.TitleScreenMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("GameMap");
            add("BattleMap");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap();
            case "TitleScreen":
                return new TitleScreenMap();
            case "GameMap":
                return new GameMap();
            case "BattleMap":
                return new BattleMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
