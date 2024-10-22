package Scripts.CaveMapScripts;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

// script for talking to bug npc
// checkout the documentation website for a detailed guide on how this script works
public class BugScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCLockScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Hello!");
            addText("Do you like bugs?", new String[] { "Yes", "No" });
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
                    addText("Hell yeah man! Me too!");
                    addText("It would've been weird if you said you didn't like bugs... considering I am one.");
                    addText("As a bug enjoyer, I'm going to let you in on a little secret...\nThere is a giant bug here somewhere in this cave.");
                    addText("He's like, totally ancient and stuff!");
                    addText("The few time I saw him, he keeps murmuring about something like \"Protect the barrier...\"\nor something...He's super cool though!");
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
                    addText("WHAT?!?!");
                    addText("What about bugs don't you like?");
                    addText("That's it! We're throwing hands.. or legs.. or limbs. En garde!");

                    //toggle combat flag
                }});
                addScriptAction(new ChangeFlagScriptAction("bugEnemy", true));
                addScriptAction(new ChangeFlagScriptAction("combatTriggered", true));
            }});
        }});

        scriptActions.add(new NPCUnlockScriptAction());
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
