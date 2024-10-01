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
            addText("This is a combat test");
        }});

        //toggle combat flag
        scriptActions.add(new ChangeFlagScriptAction("combatTriggered", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}