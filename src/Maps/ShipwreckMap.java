package Maps;

import Level.Map;
import Tilesets.CaveTileset;

public class ShipwreckMap extends Map{
    public ShipwreckMap() {
        super("shipwreck_map.txt", new CaveTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();
    }
}
