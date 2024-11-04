package Scripts.StartIslandMap;

import java.util.ArrayList;

import Engine.GraphicsHandler;
import Screens.PlayLevelScreen;
import Screens.BattleScreen;
import Level.Script;
import Level.ScriptState;
import ScriptActions.*;
import Maps.BattleMap;
import Game.ScreenCoordinator;


public class BattleScript extends Script {
    
    boolean isBattleWon = false;
    boolean isBattleLost = false;

    
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("TIME TO COMMENCE BATTLE!");
            addText("You can ATTACK, HEAL, DODGE, and TALK", new String[] { "ATTACK", "HEAL"});
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                    return (PlayLevelScreen.getMap().getPlayer().getHealth() <= 0);
                    }
                });

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        PlayLevelScreen.getMap().getFlagManager().setFlag("battleLost");
                        return ScriptState.COMPLETED;
                    }
                });
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                    return (BattleMap.getEnemy().getHealth() <= 0);
                    }
                });

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        PlayLevelScreen.getMap().getFlagManager().setFlag("battleWonText");
                        return ScriptState.COMPLETED;
                    }
                });
            }});
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 0;
                    }
                });

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        PlayLevelScreen.getMap().getFlagManager().setFlag("battlePanel");

                        return ScriptState.COMPLETED;
                    }
                });
                addScriptAction(new TextboxScriptAction() {{
                    addText("You fired your cannons at the enemy!");
                }});

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        System.out.println(PlayLevelScreen.battleScreen.returnMultiplier());
                        BattleMap.getEnemy().attack(Math.round(Math.random()*PlayLevelScreen.battleScreen.returnMultiplier()) * PlayLevelScreen.getMap().getPlayer().getStrength());

                        return ScriptState.COMPLETED;
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("The ammunition shreds through the enemy vessel. Keep it up!");
                }});
                
                addScriptAction(new TextboxScriptAction() {{
                    addText("The enemy lauches an attack!");
                    addText("Your ship is damaged!");
                }});
                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        PlayLevelScreen.getMap().getPlayer().damage(Math.round(Math.random()*2 + BattleMap.getEnemy().getStrength()));
                        return ScriptState.COMPLETED;
                    }
                });

            }});

            // DO NOTHING script
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");

                    return answer == 1;
                }
            });

                addScriptAction(new TextboxScriptAction() {{
                    addText("You grabbed spare boards on the ship and make emergency repairs");
                    addText("Your ship recovers some structural integrity");
                }});

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        PlayLevelScreen.getMap().getPlayer().heal();
                        return ScriptState.COMPLETED;
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("The enemy lauches an attack!");
                    addText("Your ship is damaged!");
                }});

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        PlayLevelScreen.getMap().getPlayer().damage(Math.round((Math.random()*2) * BattleMap.getEnemy().getStrength()));
                        return ScriptState.COMPLETED;
                    }
                });

                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new CustomRequirement() {
                        @Override
                        public boolean isRequirementMet() {
                        return (PlayLevelScreen.getMap().getPlayer().getHealth() <= 0);
                        }
                    });

                    addScriptAction(new ScriptAction() {
                        @Override
                        public ScriptState execute() {
                            PlayLevelScreen.getMap().getFlagManager().setFlag("battleLost");
                            return ScriptState.COMPLETED;
                        }
                    });
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

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        if (BattleMap.enemy == PlayLevelScreen.getMap().getNPCById(801)) {
                            PlayLevelScreen.getMap().getFlagManager().setFlag("krakenKilled");
                        }
                        if (BattleMap.enemy == PlayLevelScreen.getMap().getNPCById(101)) {
                            PlayLevelScreen.getMap().getFlagManager().setFlag("jvBeaten"); 
                        }

                        return ScriptState.COMPLETED;
                    }
                });

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
                    addText("You can only pray to the goddesses to save you now");
                    addText("Perhaps you will stand victorious in your next life...");
                }});
                addScriptAction(new ChangeFlagScriptAction("gameOver", true));
                }});
        }});

        // scriptActions.add(new ChangeFlagScriptAction("isInCombat", true));
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }

}
