package Scripts.CaveMapScripts;

import java.util.ArrayList;

import Level.Script;
import Level.ScriptState;
import ScriptActions.*;


// trigger script at beginning of game to set that heavy emotional plot
// checkout the documentation website for a detailed guide on how this script works
public class CaveExitScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Exit Cave?", new String[] { "Yes", "No" });
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

                addScriptAction(new ChangeFlagScriptAction("exitCave", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1;
                    }
                });

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        player.setLocation(player.getX(), player.getY()-10);
                        return ScriptState.COMPLETED;
                    }
                });
            }});
        }});
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}

