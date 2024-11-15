package MapEditor;

import Level.Map;
import Maps.ArcticMap;
import Maps.AtlantisMap;
import Maps.BattleMap;
import Maps.CaveMap;
import Maps.DateMap;
import Maps.EndMap;
import Maps.OceanMap;
import Maps.ShipwreckMap;
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
            add("ArcticMap");
            add("ShipwreckMap");
            add("EndMap");
            add("DateMap");
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
            case "ArcticMap":
                return new ArcticMap();
            case "ShipwreckMap":
                return new ShipwreckMap();
            case "EndMap":
                return new EndMap();
            case "DateMap":
                return new DateMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
