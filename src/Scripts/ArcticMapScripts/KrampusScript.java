package Scripts.ArcticMapScripts;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.FlagRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.NPCFacePlayerScriptAction;
import ScriptActions.NPCLockScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;

public class KrampusScript extends Script{

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCLockScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
            addRequirement(new FlagRequirement("talkedToKrampus", false));

            addScriptAction(new TextboxScriptAction() {{
                addText("Well, well, well...\nWhat do we have here?", new String[] {"..."});
                addText("Another distant traveler I see...");
            }});
            }});
        }});
        return scriptActions;
    }
    
}
