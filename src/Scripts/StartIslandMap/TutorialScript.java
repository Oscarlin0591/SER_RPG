package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

public class TutorialScript extends Script{

    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Ahoy Matey!", new String[] { "Hi?", "Ahoy!" });
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return (answer == 0|| answer ==1);
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("Ye looking like yer hungry for booty!",new String[] { "I'm looking for the Nave d'Oro... You reckon tellin me 'bout it?", "Buzz off you coxswain!" });
                }});
            }});
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
                    addText("Of course I do! Not a single tar, lad, or seadog hasn’t heard of the fabled Nave d’Oro", new String[] { "Reckon you can tell me more?" });
                    addText("Well it was said that the Nave d’Oro is hidden in the heart of the seven seas!\nSealed in a magical barrier.");
                    addText("Only a courageous sea dog who’s sailed around the globe and slain the avatars of\nsafekeeping could that barrier fall.");
                    addText("I ne’er seen anyone return. No matter if they sailed in a raft or a man o’ war.");
                    addText("Perhaps you’re the exception, may Neptune’s blessings be upon ye.");
                }});
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }


    // // scriptActions.add(new TextboxScriptAction() {{
    // //     addText("Hello!");
    // //     addText("Do you like bugs?", new String[] { "Yes", "No" });
    // // }});


    // }
}