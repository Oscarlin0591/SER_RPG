package Scripts.CaveMapScripts;

import Level.Script;
import ScriptActions.*;
import Utils.Direction;
import Utils.Visibility;

import java.util.ArrayList;

public class BossBeetleScript extends Script{

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCLockScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        // scriptActions.add(new NPCWalkScriptAction(Direction.RIGHT, 196, 2));
        // scriptActions.add(new NPCWalkScriptAction(Direction.UP, 196, 2));

        // scriptActions.add(new TextboxScriptAction() {{
        //     addText("WHO DARES TINKERING ABOUT MY THRONE?!?!");
        // }});
        // scriptActions.add(new NPCChangeVisibilityScriptAction(Visibility.VISIBLE));
        // scriptActions.add(new NPCWalkScriptAction(Direction.DOWN, 196, 2));
        // scriptActions.add(new NPCWalkScriptAction(Direction.LEFT, 196, 2));

        scriptActions.add(new TextboxScriptAction() {{
            addText("YOU!");
            addText("A foreign adversary I see...");
            addText("Whatever business you may have here in our cave... take it elsewhere.");
            addText("You are not welcome.", new String[] { "I need you to take down the barrier", "No one asked, let's fight." });
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
                    addText("The barrier?");
                    addText("Over my dead exoskeleton!");
                    addText("You're just like the others, so eager on the shine of gold that you are\nblinded by the terror that could be released if I let that barrier down...");
                    addText("There are no other options. En Garde!");
                }});

                addScriptAction(new UnlockPlayerScriptAction());

                addScriptAction(new ChangeFlagScriptAction("beetleEnemy", true));
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
                    addText("What a sour attitude you have!");
                    addText("Think you could take me on? Bring it!");
                }});
                
                addScriptAction(new UnlockPlayerScriptAction());

                addScriptAction(new ChangeFlagScriptAction("beetleEnemy", true));
                addScriptAction(new ChangeFlagScriptAction("combatTriggered", true));
            }});
        }});

        scriptActions.add(new NPCChangeVisibilityScriptAction(Visibility.HIDDEN));

        // scriptActions.add(new NPCUnlockScriptAction());
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
