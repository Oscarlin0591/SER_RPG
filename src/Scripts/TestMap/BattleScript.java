package Scripts.TestMap;

import java.util.ArrayList;

import Level.Script;
import Screens.PlayLevelScreen;
import ScriptActions.*;
import Maps.BattleMap;


public class BattleScript extends Script {

    boolean isBattleWon = false;
    boolean isBattleLost = false;

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("TIME TO COMMENCE BATTLE!");
            addText("This is a test, ATTACK to win or DO NOTHING to lose.", new String[] { "ATTACK", "DO NOTHING" });
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        isBattleWon = true;
                        System.out.println("DEBUG: Battle Won");
                        return answer == 0;
                    }
                });
                
                addScriptAction(new TextboxScriptAction() {{
                    addText("You defeated the enemy!");
                    addText("You earned:\n3 doubloons and a mysterious scroll.");
                }});
                System.out.println("DEBUG: Win flag tripped");
                addScriptAction(new ChangeFlagScriptAction("battleWon",true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        isBattleLost = true;
                        System.out.println("game over");
                        return answer == 1;
                    }
                });
                
                addScriptAction(new TextboxScriptAction() {{
                    addText("The enemy lauches a devastating attack!");
                    addText("Your ship sinks immediately and you\nmeet your untimely demise...");
                }});
                System.out.println("DEBUG: game over flag tripped");
                addScriptAction(new ChangeFlagScriptAction("gameOver",true));
            }});
        }});

        // scriptActions.add(new ChangeFlagScriptAction("isInCombat", true));
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }

}
