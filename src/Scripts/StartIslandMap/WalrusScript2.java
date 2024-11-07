package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

// script for talking to walrus npc
// checkout the documentation website for a detailed guide on how this script works
public class WalrusScript2 extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToWalrus2", false));
                addScriptAction(new TextboxScriptAction() {{
                    addText("G'day!");
                    addText("I'm Linda, and the one over there is my husbando, \nLudwig Cornelius Streihauffer of Tuscany II");
                    addText("Isn't he so lovely? So handsome, with such prominent tusks?");
                    addText("Don't dare wooing him away, he's mine!");
                }});
                addScriptAction(new ChangeFlagScriptAction("hasTalkedToWalrus2", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToWalrus2", true));
                addScriptAction(new TextboxScriptAction("I love spending time with my husbando, \nLudwig Cornelius Streihauffer of Tuscany II!"));
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
