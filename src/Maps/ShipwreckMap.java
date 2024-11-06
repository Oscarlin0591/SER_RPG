package Maps;

import Level.Map;
import NPCs.Interactable.Shipwreck;
import Tilesets.*;

public class ShipwreckMap extends Map{
    public ShipwreckMap() {
        super("shipwreck_map.txt", new ShipwreckTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();
    }
}
