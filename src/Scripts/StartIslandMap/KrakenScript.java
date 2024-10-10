package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

public class KrakenScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("urrrghhhh...");
            addText("...");
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}