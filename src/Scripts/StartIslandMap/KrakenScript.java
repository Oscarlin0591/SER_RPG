package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import Screens.PlayLevelScreen;
import ScriptActions.*;

public class KrakenScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("urrrghhhh...");
            addText("...");
            addText("urrgghh...");
            addText("...");
            addText("alright lets do this");
            addText("take these steroids first tho (PLAYER STRENGTH BOOST TO MAKE FIGHT WINNABLE)");
        }});

        //toggle player upgrade flag
        scriptActions.add(new ChangeFlagScriptAction("playerRoided", true));
        
        //toggle combat flag
        scriptActions.add(new ChangeFlagScriptAction("krakenEnemy", true));
        scriptActions.add(new ChangeFlagScriptAction("combatTriggered", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}