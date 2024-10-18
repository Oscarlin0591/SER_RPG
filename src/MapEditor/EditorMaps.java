package MapEditor;

import Level.Map;
import Maps.BattleMap;
import Maps.CaveMap;
import Maps.OceanMap;
import Maps.StartIslandMap;
import Maps.TitleScreenMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("StartIslandMap");
            add("TitleScreen");
            add("OceanMap");
            add("BattleMap");
            add("CaveMap");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "StartIslandMap":
                return new StartIslandMap();
            case "TitleScreen":
                return new TitleScreenMap();
            case "BattleMap":
                return new BattleMap();
            case "CaveMap":
                return new CaveMap();
            case "OceanMap":
                return new OceanMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
