package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import Level.ScriptState;
import Screens.PlayLevelScreen;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.CustomRequirement;
import ScriptActions.FlagRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class BlueGemScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
    
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("gemQuest", false));

                addScriptAction(new TextboxScriptAction() {{
                    addText("This is a shiny looking gem...");
                }});
            }});
        }});
        scriptActions.add(new ConditionalScriptAction(){{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("gemQuest", true));

                addScriptAction(new TextboxScriptAction() {{
                    addText("You collected one of three gems!");
                }});

                addScriptAction(new ChangeFlagScriptAction("collectedBlueGem", true));         
             }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}