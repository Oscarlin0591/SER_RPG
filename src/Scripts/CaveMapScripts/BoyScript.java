package Scripts.CaveMapScripts;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import Level.Script;
import Screens.PlayLevelScreen;
import ScriptActions.*;

public class BoyScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return !(PlayLevelScreen.flagManager.isFlagSet("waterQuest"));
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("hey mister", new String[] {"hi", "where are your parents?"});
                    addText("i am a fierce traveller mister, who's planning to explore the entire world!");
                    addText("the only problem is, i'm really thirsty...");
                    addText("ocean water doesn't taste very good, i found that out the hard way...");
                    addText("do you think you could find me some fresh water mister?");
                    addText("here, i'll give you my empty water bottle!");
                    addText("if you find some fresh water, would you fill it up and return it to me?", new String[] {"sure buddy!", "i can't right now"});
                }});
                addScriptAction(new ChangeFlagScriptAction("waterQuest", true));
            }});
        }});
    scriptActions.add(new ConditionalScriptAction(){{
        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{ 
            addRequirement(new FlagRequirement("waterCollected", true));

            addScriptAction(new TextboxScriptAction() {{
                addText("thank you mister! i can finally get back to exploring! here's something for you!");
            }});

        }});
    }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}