package Scripts.ArcticMapScripts;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

public class KrampusScript extends Script{

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCLockScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
            addRequirement(new FlagRequirement("talkedToKrampus", false));

            addScriptAction(new TextboxScriptAction() {{
                addText("Well, well, well...\nWhat do we have here?", new String[] {"..."});
                addText("Another distant traveler I see...", new String[]{"Ye had visitors before?"});
                addText("Of course, many of them are like you, yearning for the ol' Nave d'Oro");
                addText("All of them too young, too fresh to grasp the weight of their pursuit...");
                addText("Ye come to me to have me taketh down the barrier, correct?", new String[] {"Can ye? It'd do me a favor"});
                addText("HO HO HO! Favors? An old sage like me has seen what my favors had done");
                addText("Y'know what, give me a second and I'll get right back to ye");
            }});
            
            addScriptAction(new ChangeFlagScriptAction("talkedToKrampus", true));
        }});
    }});
    
        scriptActions.add(new ConditionalScriptAction() {{
        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
            addRequirement(new FlagRequirement("talkedToKrampus", true));
        
        
            addScriptAction(new TextboxScriptAction() {{
            addText("Sorry lad, I ain't performing ye favors lest I get something in return", new String[] {"Talking to you is futile, draw yer weapons!", "What favors can I do?"});
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
            addRequirement(new CustomRequirement() {

                @Override
                public boolean isRequirementMet() {
                    int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                    return answer == 0;
                }
            });

            addScriptAction(new TextboxScriptAction() {{
                addText("Ah, I see how ye is...");
                addText("Always eager to defer to yer blades and guns");
                addText("Very well, I'll entertain ye in this duel");
            }});

            addScriptAction(new ChangeFlagScriptAction("krampusEnemy", true));
            addScriptAction(new ChangeFlagScriptAction("combatTriggered", true));
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
                    addText("Ye agree to help? What a surprise...");
                    addText("If thats the case, here what I need you to do...");
                    addText("[Insert Quest Here]");
                }});
                }});
        }});
    }});

        return scriptActions;
    }
    
}
