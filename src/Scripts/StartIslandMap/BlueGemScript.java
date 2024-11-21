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
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return !(PlayLevelScreen.flagManager.isFlagSet("gemQuest"));
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("This is a shiny looking gem...");
                }});
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.flagManager.isFlagSet("gemQuest"));
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("You collected one of three gems!");
                }});

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        PlayLevelScreen.flagManager.setFlag("collectedBlueGem");
        
                        if (map.getNPCById(24) != null) {
                            PlayLevelScreen.flagManager.setFlag("blueGem");
                        }
                        return ScriptState.COMPLETED;
                }});         
             }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}