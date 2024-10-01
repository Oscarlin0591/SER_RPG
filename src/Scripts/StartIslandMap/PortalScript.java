package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;


// trigger script at beginning of game to set that heavy emotional plot
// checkout the documentation website for a detailed guide on how this script works
public class PortalScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Hello!");
            addText("I am a portal!");
            addText("What do I do? I'm still trying to figure that out.");
        }});

        scriptActions.add(new ChangeFlagScriptAction("interactPortal", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}

