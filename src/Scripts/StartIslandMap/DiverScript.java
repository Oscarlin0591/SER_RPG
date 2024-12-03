package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import Level.ScriptState;
import Screens.PlayLevelScreen;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.CustomRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.NPCChangeVisibilityScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class DiverScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.flagManager.isFlagSet("collectedBlueGem") && PlayLevelScreen.flagManager.isFlagSet("collectedGreenGem") && PlayLevelScreen.flagManager.isFlagSet("collectedRedGem"));
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("wow! you were able to find all the gems!");
                    addText("here's a reward for finding them!");
                    addText("You gained 2 health!");
                }});

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        player.setMaxHealth(player.getMaxHealth()+2);
                        System.out.println("Max: " + player.getMaxHealth());
                        System.out.println("Health: " + player.getHealth());
                            return ScriptState.COMPLETED;
                        }
                });
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return !(PlayLevelScreen.flagManager.isFlagSet("collectedBlueGem") && PlayLevelScreen.flagManager.isFlagSet("collectedGreenGem") && PlayLevelScreen.flagManager.isFlagSet("collectedRedGem"));
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("blurburbrbrb");
                    addText("nothing like a good dive to clear the mind!", new String[] {"who are you?", "how long have you been out here?"});
                    addText("oh none of that is important");
                    addText("i heard a rumor that some gems are hidden around this place");
                    addText("apparently some pirates left some treasure around here");
                    addText("if you can bring them all to me, i'll give you something for your time");
                    addText("i could've sworn i saw a blue one nearby...");
                }});

                addScriptAction(new ChangeFlagScriptAction("gemQuest", true));
            }});

        }});

    
        // PlayLevelScreen.flagManager.setFlag("gemQuest");
        
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}