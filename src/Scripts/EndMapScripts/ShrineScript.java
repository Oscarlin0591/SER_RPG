package Scripts.EndMapScripts;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

// script for talking to walrus npc
// checkout the documentation website for a detailed guide on how this script works
public class ShrineScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("bossUnlocked", false));
                addScriptAction(new TextboxScriptAction() {{
                    addText("You cannot yet enter");
                    addText("Light all of the Torches of Recollection and only then will the door open");
                }});
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("bossUnlocked", true));
                addScriptAction(new TextboxScriptAction("You ready yourself as you prepare for your final battle..."));

                addScriptAction(new ChangeFlagScriptAction("finalBoss", true));
                addScriptAction(new ChangeFlagScriptAction("combatTriggered", true));
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
