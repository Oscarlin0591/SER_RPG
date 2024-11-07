package Scripts.ShipwreckScripts;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class GhostPirateScript extends Script{

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("BOO!");
            addText("...");
            addText("Sorry laddy, I'm legally obligated to do that.");
            addText("That damned beast picked me ship out of the water and get this!");
            addText("It placed it gently done on this 'island' if ye can call it that.");
            addText("Oh I starved to death if ye wanted to know.");
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
    
}
