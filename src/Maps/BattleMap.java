package Maps;

// import EnhancedMapTiles.PushableRock;
import Level.*;
import Screens.PlayLevelScreen;
// import NPCs.Island;
import Scripts.SimpleTextScript;
import Scripts.StartIslandMap.*;
import Tilesets.RPGTileset;
import java.util.ArrayList;
import java.lang.Thread;

public class BattleMap extends Map{
    public static boolean battle = false;

    public BattleMap() {
    super("battle_map.txt", new RPGTileset());
    this.playerStartPosition = getMapTile(12, 6).getLocation();
    }
    
    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(576, 288, 50, 10, new BattleScript(), "battleWon"));
        triggers.add(new Trigger(576, 288, 10, 80, new BattleScript(), "battleWon"));
        triggers.add(new Trigger(676, 288, 10, 80, new BattleScript(), "battleWon"));
        System.out.println("DEBUG: Triggers loaded");
        return triggers;
    }
}
