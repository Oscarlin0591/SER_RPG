package Scripts.ShipwreckScripts;

import java.util.ArrayList;

import Level.Script;
import Screens.PlayLevelScreen;
import ScriptActions.*;
import Utils.Visibility;

public class KrakenScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText(".....");
            addText("Is that your measly ship over there?", new String[] { "Combat", "Puzzle" });
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 0;
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("Well I think I should just pluck it out of the water and\n add it to my collection.");
                    scriptActions.add(new ChangeFlagScriptAction("krakenEnemy", true));
                    scriptActions.add(new ChangeFlagScriptAction("combatTriggered", true));
                }});
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1;
                    }
                });
                
                addScriptAction(new TextboxScriptAction() {{
                    scriptActions.add(new ChangeFlagScriptAction("krakenPuzzleTriggered", true));
                }});
            }});
        }});
        
        scriptActions.add(new UnlockPlayerScriptAction());
        

        return scriptActions;
    }
}