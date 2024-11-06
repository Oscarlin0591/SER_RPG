package Scripts.ArcticMapScripts;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.ChangeFlagScriptAction;
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
                addText("Another distant traveler I see...", new String[]{"Ye had visitors before?"});
                addText("Of course, many of them are like you, yearning for the ol' Nave d'Oro");
                addText("All of them too young, too fresh to grasp the weight of their pursuit...");
                addText("Ye come to me to have me taketh down the barrier, correct?", new String[] {"Can ye? It'd do me a favor"});
                addText("HO HO HO! Favors? An old sage like me has seen what my favors had done");
                addText("Sorry lad, I ain't performing ye favors lest I get something in return", new String[] {"Talking to you is futile, draw yer weapons!", "What favors can I do?"});
            }});

            addScriptAction(new ChangeFlagScriptAction("talkedToKrampus", true));
            }});
        }});
        return scriptActions;
    }
    
}
