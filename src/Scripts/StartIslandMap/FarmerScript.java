package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import Level.ScriptState;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.CustomRequirement;
import ScriptActions.FlagRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.NPCChangeVisibilityScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import Utils.Visibility;

public class FarmerScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("treeBroken", false));

                addScriptAction(new TextboxScriptAction() {{
                addText("hey there kiddo!");
                addText("what do you think of my apple tree?", new String[] {"it's pretty cool!", "meh..."});
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
                    addText("thanks kiddo!");
                    addText("i'll let you in on a little secret, if you give my tree a\nshake, an apple should fall");
                    addText("that tree's pretty weak though, hard to grow anything here");
                    addText("if you get the chance, head over towards the top of the map,\nthat is, if you have a hunger for exploration");
                }});
            }});
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1;
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("hey kiddo, learn some manners would you...");
                }});
            }});
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("treeBroken", true));
                
                addScriptAction(new TextboxScriptAction() {{
                addText("hey there kiddo!");
                addText("what do you think of my-");
                addText("My- le tree");
                addText("You... le pirate, le broke me tree...", new String[]{"Oops gotta go!"});
            }});

            }});
        }});
        
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}