package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import Screens.PlayLevelScreen;
import ScriptActions.*;

public class WellScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("waterQuest", true));
                // addRequirement(new CustomRequirement() {
                //     @Override
                //     public boolean isRequirementMet() {
                //         return (PlayLevelScreen.flagManager.isFlagSet("waterQuest"));
                //     }
                // });

                addScriptAction(new TextboxScriptAction() {{
                    addText("You filled the water bottle with well water");
                }});

                PlayLevelScreen.flagManager.setFlag("waterCollected");
            }});
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("waterQuest", false));
                // addRequirement(new CustomRequirement() {
                //     @Override
                //     public boolean isRequirementMet() {
                //         return !(PlayLevelScreen.flagManager.isFlagSet("waterQuest"));
                //     }
                // });

                addScriptAction(new TextboxScriptAction() {{
                    addText("This water looks refreshing...");
                }});
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}