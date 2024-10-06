package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

public class ShrekScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Oh? You're approaching me?");
            addText("Instead of running away, you're coming right to me?");
            addText("So be it, we shall settle this through our fists.");
        }});

        //toggle combat flag
        scriptActions.add(new ChangeFlagScriptAction("shrekEnemy", true));
        scriptActions.add(new ChangeFlagScriptAction("combatTriggered", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}