package Scripts.ShipwreckScripts;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class SwordGhostScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Sword");
            addText("Sword");
            addText("SwordSwordSwordSwordSwordSwordSword");
            addText("Oh hello, didn't notice you there.");
            addText("...");
            addText("I'm going to go back to admiring my sword now");
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
