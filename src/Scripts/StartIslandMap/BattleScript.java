package Scripts.StartIslandMap;

import java.util.ArrayList;

import Screens.PlayLevelScreen;
import Level.Script;
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
            addText("This is a test, ATTACK to win or DO NOTHING to lose.", new String[] { "ATTACK", "DO NOTHING"});
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        if (answer == 0) {
                            BattleMap.getEnemy().attack((float) Math.random()*2 + PlayLevelScreen.getMap().getPlayer().getStrength());
                            PlayLevelScreen.getMap().getPlayer().damage((float) Math.random()*2 + BattleMap.getEnemy().getStrength());

                            if (PlayLevelScreen.getMap().getPlayer().getHealth() <=0) {
                                PlayLevelScreen.getMap().getFlagManager().setFlag("battleLost");
                                // PlayLevelScreen.getMap().getFlagManager().setFlag("gameOver");
                            } else if (BattleMap.getEnemy().getHealth() <= 0) {
                                PlayLevelScreen.getMap().getFlagManager().setFlag("battleWonText");


                                // toggle enemy killed flag if applicable
                                if (BattleMap.enemy == PlayLevelScreen.getMap().getNPCById(801))
                                    PlayLevelScreen.getMap().getFlagManager().setFlag("krakenKilled");
            
                                if (BattleMap.enemy == PlayLevelScreen.getMap().getNPCById(101))
                                    PlayLevelScreen.getMap().getFlagManager().setFlag("jvBeaten"); 
                        }
                        }

                        return answer == 0;
                    }
                });

                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new CustomRequirement() {
                        @Override
                        public boolean isRequirementMet() {
                            return PlayLevelScreen.getMap().getFlagManager().isFlagSet("battleLost");
                        }
                    });
                    addScriptAction(new TextboxScriptAction(){{
                        addText("The enemy lands a finishing blow!");
                        addText("You can only pray to the goddesses\nto save you now");
                        addText("Perhaps you will stand victorious\nin your next life...");
                    }});
                    addScriptAction(new ChangeFlagScriptAction("gameOver", true));
                }});

                
                // addScriptAction(new ChangeFlagScriptAction("battleWon",true));
                
                addScriptAction(new TextboxScriptAction() {{
                    addText("You fired your cannons at the enemy!");
                    addText("The ammunition shreds through the enemy vessel...\nKeep it up!");
                }});
                
                
            }});

            // DO NOTHING script
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");

                        if (answer == 1) {
                            PlayLevelScreen.getMap().getPlayer().damage((float) (Math.random()*2) * BattleMap.getEnemy().getStrength());
                        } 

                        if (PlayLevelScreen.getMap().getPlayer().getHealth() <= 0) {
                            PlayLevelScreen.getMap().getFlagManager().setFlag("battleLost");
                        }

                        
                    return answer == 1;
                }
            });
                addScriptAction(new TextboxScriptAction() {{
                    addText("The enemy lauches a devastating attack!");
                    addText("Your ship is damaged!");
                }});
            }});
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return PlayLevelScreen.getMap().getFlagManager().isFlagSet("battleWonText");
                    }
                });
                addScriptAction(new TextboxScriptAction(){{
                    addText("You defeated the enemy!");
                    addText("You earned:\n3 doubloons and a mysterious scroll.");
                }});
                addScriptAction(new ChangeFlagScriptAction("battleWon", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return PlayLevelScreen.getMap().getFlagManager().isFlagSet("battleLost");
                    }
                });
                addScriptAction(new TextboxScriptAction(){{
                    addText("The enemy lands a finishing blow!");
                    addText("You can only pray to the goddesses\nto save you now");
                    addText("Perhaps you will stand victorious\nin your next life...");
                }});
                addScriptAction(new ChangeFlagScriptAction("gameOver", true));
                }});
        }});

        // scriptActions.add(new ChangeFlagScriptAction("isInCombat", true));
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }

}
