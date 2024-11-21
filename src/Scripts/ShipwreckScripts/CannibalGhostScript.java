package Scripts.ShipwreckScripts;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class CannibalGhostScript extends Script{
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction() {{
            addText("...");
            addText("Well you didn' need t' go that far.");
            addText("Twas just a little hungry.");
        }});

        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
