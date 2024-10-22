package Scripts.CaveMapScripts;

import java.util.ArrayList;

import Level.Script;
import Screens.PlayLevelScreen;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.CustomRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.NPCFacePlayerScriptAction;
import ScriptActions.NPCLockScriptAction;
import ScriptActions.NPCUnlockScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class CaveHoleScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        // scriptActions.add(new ConditionalScriptAction() {{
        //     addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
        //         addRequirement(new CustomRequirement() {
        //             @Override
        //             public boolean isRequirementMet() {
        //                 // ensures player is directly underneath tree trunk tile
        //                 // this prevents the script from working if the player tries to interact with it from the side

        //                 // if player is not below tree trunk tile, player location is not valid and this conditional script will not be reached
        //                 if (player.getBounds().getY1() <= entity.getBounds().getY2()) {
        //                     return false;
        //                 }

        //                 // if code gets here, it means player is below tree trunk tile and player location is valid, so this conditional script will continue
        //                 return true;
        //             }
        //     });
        // }});
    // }});

        scriptActions.add(new TextboxScriptAction() {{
            addText("There is a very deep hole in front of you.");
            addText("You know the abyss will swallow you whole if you jump...\nbut strangely you feel as if you must join the void...", new String[] { "Jump", "Resist" });
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
                    addText("You brace yourself, as you prepare to jump...");
                    addText("The void embraces you.");
                }});
                addScriptAction(new ChangeFlagScriptAction("gameOver", true));
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
                    addText("You pull away from the void's temptation.");
                    addText("Perhaps another time...");

                    //toggle combat flag
                }});
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
