package Scripts.ShipwreckScripts;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class FlintlockGhostScript extends Script {
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction() {{
            addText("Hahahaha", new String[] {"Whatha got there laddy?"});
            addText("Oh this? Only te best flintlock this side of the seven seas.");
            addText("Me pride and joy it is. Show it off to everyone I know.");
            addText("Strange t'ing happened a while ago. \nShowed this to the guy with the sword over there.");
            addText("'e was very confused what it was.");
            addText("Strange yeah, who doesn't kno what a gun is?");
        }});

        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}