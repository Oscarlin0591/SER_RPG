package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

// script for talking to walrus npc
// checkout the documentation website for a detailed guide on how this script works
public class WalrusScript1 extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToWalrus1", false));
                addScriptAction(new TextboxScriptAction() {{
                    addText("Hey there partner!");
                    addText("The names Ludwig Cornelius Streihauffer of Tuscany II, \nand over there is me wife, Linda.");
                    addText("A beaut, ain't she? Such voluptuous blubber, \nain't ever seen anything like that.");
                    addText("Ya better keep your oogling eyes off her!");
                }});
                addScriptAction(new ChangeFlagScriptAction("hasTalkedToWalrus1", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToWalrus1", true));
                addScriptAction(new TextboxScriptAction("I love spending time with me wife Linda!"));
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
