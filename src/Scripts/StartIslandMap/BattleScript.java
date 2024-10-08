package Scripts.StartIslandMap;

import java.util.ArrayList;
import Screens.PlayLevelScreen;
import Engine.GamePanel;
import Level.Script;
import ScriptActions.*;


public class BattleScript extends Script {

    boolean isBattleWon = false;
    boolean isBattleLost = false;

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("TIME TO COMMENCE BATTLE!");
            addText("This is a test, ATTACK to win or DO NOTHING to lose.", new String[] { "ATTACK", "DO NOTHING", "RECEIVE DAMAGE" });
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
                addScriptAction(new ChangeFlagScriptAction("gameOver",true));
            }});
            
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        // isBattleWon = true;
                        PlayLevelScreen.getMap().getPlayer().damage(1);
                        
                        // player health check
                        if (PlayLevelScreen.getMap().getPlayer().getHealth() <= 0) {
                            isBattleWon = false;
                            PlayLevelScreen.getMap().getFlagManager().setFlag("gameOver");
                        }
                        System.out.println("DEBUG: Damage flag tripped");
                        return answer == 2;
                    }
                    
                });
                // battle lost text
                // if (PlayLevelScreen.getMap().getPlayer().getHealth() > 0) {
                    addScriptAction(new TextboxScriptAction() {{
                        addText("You mysteriously take 1 damage! How odd..");
                    }});
                    
                }});
                
                if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("gameOver")) {
                    scriptActions.add(new TextboxScriptAction() {{
                        addText("The enemy lands a finishing blow!");
                        addText("You can only pray to the goddesses\nto save you now");
                        addText("Perhaps you will stand victorious\nin your next life...");
                        }});
                    }
        }});

        // scriptActions.add(new ChangeFlagScriptAction("isInCombat", true));
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }

}
