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
            addText("*Swings sword at you*", new String[] {"AHHH(rg)"});
            addText("Ye be afraid?");
            addText("Ha! Haven't got a spook like that in years.");
            addText("That lad with the eyepatch over there, 'told 'im that we have to do that!");
            addText("Hahahaha, it's fun messin with the greenbeards.");
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
