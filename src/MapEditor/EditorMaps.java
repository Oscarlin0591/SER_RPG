package MapEditor;

import Level.Map;
import Maps.AtlantisMap;
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
            add("AtlantisMap");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "StartIslandMap":
                return new StartIslandMap();
            case "TitleScreen":
                return new TitleScreenMap();
            case "OceanMap":
                return new OceanMap();
            case "BattleMap":
                return new BattleMap();
            case "CaveMap":
                return new CaveMap();
            case "AtlantisMap":
                return new AtlantisMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
