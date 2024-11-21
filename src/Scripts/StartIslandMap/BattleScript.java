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
import Utils.Direction;


public class BattleScript extends Script {
    
    boolean isBattleWon = false;
    boolean isBattleLost = false;

    boolean attackCrit = false;

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        //player death script
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

            //enemy death script
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
            }});

        //combat won script
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
                    // addText("You earned:\n3 doubloons and a mysterious scroll.");
                }});

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        if (BattleMap.enemy == PlayLevelScreen.getMap().getNPCById(801)){
                            PlayLevelScreen.getMap().getFlagManager().setFlag("krakenKilled");
                        }
                        if (BattleMap.enemy == PlayLevelScreen.getMap().getNPCById(101)){
                            PlayLevelScreen.getMap().getFlagManager().setFlag("jvBeaten"); 
                        }
                        if (BattleMap.enemy == PlayLevelScreen.getMap().getNPCById(802)){
                            if (PlayLevelScreen.flagManager.isFlagSet("beetleQuestComplete"))
                                PlayLevelScreen.getMap().getFlagManager().setFlag("beetleBeaten");
                            else
                                PlayLevelScreen.getMap().getFlagManager().setFlag("beetleKilled"); 
                        }
                        if(BattleMap.enemy == PlayLevelScreen.getMap().getNPCById(666)){
                            if(PlayLevelScreen.flagManager.isFlagSet("capricornQuestComplete"));
                                PlayLevelScreen.getMap().getFlagManager().setFlag("capricornBeaten");
                        } else {
                            PlayLevelScreen.getMap().getFlagManager().setFlag("capricornKilled");
                        }

                        if (BattleMap.enemy == PlayLevelScreen.getMap().getNPCById(806)) {
                            PlayLevelScreen.getMap().getFlagManager().setFlag("badShipKilled");
                            player.setMaxHealth(player.getMaxHealth()+5);
                        }

                        if (BattleMap.enemy == PlayLevelScreen.getMap().getNPCById(807)) {
                            PlayLevelScreen.getMap().getFlagManager().setFlag("pirateBeaten"); 
                        }

                        if (BattleMap.enemy == PlayLevelScreen.getMap().getNPCById(803)){
                        PlayLevelScreen.getMap().getFlagManager().setFlag("yetiBeaten"); 
                        }
                        if (BattleMap.enemy == PlayLevelScreen.getMap().getNPCById(805)){
                            PlayLevelScreen.win();
                            }
                        return ScriptState.COMPLETED;
                    }
                });

                scriptActions.add(new ConditionalScriptAction(){{
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup(){{
                        addRequirement(new FlagRequirement("badShipKilled", true));
                    
                        addScriptAction(new TextboxScriptAction(){{
                            addText("As promised, my life is yours.");
                        }});
                    }});
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
                    addText("You can only pray to the goddesses to save you now");
                    addText("Perhaps you will stand victorious in your next life...");
                }});
                
                addScriptAction(new ChangeFlagScriptAction("gameOver", true));
            }});
        }});

        //if battle not won or lost, run combat logic
        if (!PlayLevelScreen.flagManager.isFlagSet("battleLost") && !PlayLevelScreen.flagManager.isFlagSet("battleWon")) {
            //player combat menu
            scriptActions.add(new TextboxScriptAction() {{
                addText("TIME TO COMMENCE BATTLE!");
                addText("You can ATTACK, HEAL, DODGE BOOST, and CRIT BOOST", new String[] { "FIRE THE CANNONS", "REPAIR THE SHIP", "TAKE EVASIVE MANEUVERS", "MAKE A GAMBLE"});
            }});

            //attack script
            scriptActions.add(new ConditionalScriptAction() {{
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
                            int damage = (int) Math.round(2 * Math.random() * PlayLevelScreen.battleScreen.returnMultiplier() * player.getStrength());

                            if (damage == 0) damage = 1;

                            //if not crit, deal damage normally
                            if (BattleMap.getEnemy().getCritChance() * Math.random() < 0.9)
                                BattleMap.getEnemy().attack(damage);
                            else {//if crit deal double damage
                                BattleMap.getEnemy().attack(damage * 2);
                                attackCrit = true;
                            }

                            return ScriptState.COMPLETED;
                        }
                    });

                    //if last player attack crit, print message then reset attackCrit boolean
                    scriptActions.add(new ConditionalScriptAction() {{
                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    return attackCrit;
                                }
                            });
            
                            addScriptAction(new TextboxScriptAction() {{
                                addText("You land a critical hit on the enemy!");
                            }});
            
                            addScriptAction(new ScriptAction() {
                                @Override
                                public ScriptState execute() {
                                    attackCrit = false;
                                    return ScriptState.COMPLETED;
                                }
                            });
                        }});
                    }});

                    //if last player attack was dodged, print message then reset attackDodged flag
                    scriptActions.add(new ConditionalScriptAction() {{
                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    return !PlayLevelScreen.flagManager.isFlagSet("attackDodged");
                                }
                            });

                            addScriptAction(new TextboxScriptAction() {{
                                addText("The ammunition shreds through the enemy vessel. Keep it up!");
                            }});
                        }});

                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    return PlayLevelScreen.flagManager.isFlagSet("attackDodged");
                                }
                            });
            
                            addScriptAction(new TextboxScriptAction() {{
                                addText("The enemy dodges your attack!");
                            }});
            
                            addScriptAction(new ScriptAction() {
                                @Override
                                public ScriptState execute() {
                                    PlayLevelScreen.flagManager.unsetFlag("attackDodged");
                                    return ScriptState.COMPLETED;
                                }
                            });                    
                        }});
                    }});
                
                    //enemy death script
                    scriptActions.add(new ConditionalScriptAction() {{
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
                                    return (!(BattleMap.getEnemy().getHealth() <= 0));
                                }
                            });

                            addScriptAction(new TextboxScriptAction() {{
                                addText("The enemy lauches an attack!");
                            }});
            
                            addScriptAction(new ScriptAction() {
                                @Override
                                public ScriptState execute() {
                                    //calculate damage
                                    int damage = (int) Math.round(Math.random() * 2 * BattleMap.getEnemy().getStrength()); if (damage == 0) damage = 1;
            
                                    //if not crit, deal damage normally
                                    if (BattleMap.getEnemy().getCritChance() * Math.random() < 0.9)
                                        PlayLevelScreen.getMap().getPlayer().damage(damage);
                                    else {//if crit deal double damage
                                        PlayLevelScreen.getMap().getPlayer().damage(damage * 2);
                                        attackCrit = true;
                                    }
                                        return ScriptState.COMPLETED;
                                }
                            });
            
                            //if last enemy attack crit, print message then reset attackCrit boolean
                            scriptActions.add(new ConditionalScriptAction() {{
                                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                                    addRequirement(new CustomRequirement() {
                                        @Override
                                        public boolean isRequirementMet() {
                                            return attackCrit;
                                        }
                                    });
            
                                    addScriptAction(new TextboxScriptAction() {{
                                        addText("The enemy lands a critical hit!");
                                    }});
            
                                    addScriptAction(new ScriptAction() {
                                        @Override
                                        public ScriptState execute() {
                                            attackCrit = false;
                                            return ScriptState.COMPLETED;
                                        }
                                    });
                                }});
                            }});
            
                            //if player dodged last enemy attack, print message then reset attackDodged flag
                            scriptActions.add(new ConditionalScriptAction() {{
                                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                                    addRequirement(new CustomRequirement() {
                                        @Override
                                        public boolean isRequirementMet() {
                                            return !PlayLevelScreen.flagManager.isFlagSet("attackDodged");
                                        }
                                    });
            
                                    addScriptAction(new TextboxScriptAction() {{
                                        addText("Your ship is damaged!");
                                    }});
                                }});
            
                                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                                    addRequirement(new CustomRequirement() {
                                        @Override
                                        public boolean isRequirementMet() {
                                            return PlayLevelScreen.flagManager.isFlagSet("attackDodged");
                                        }
                                    });
                    
                                    addScriptAction(new TextboxScriptAction() {{
                                        addText("You dodged the enemy's attack!");
                                    }});
                    
                                    addScriptAction(new ScriptAction() {
                                        @Override
                                        public ScriptState execute() {
                                            PlayLevelScreen.flagManager.unsetFlag("attackDodged");
                                            return ScriptState.COMPLETED;
                                        }
                                    });                    
                                }});
                            }});
                        }});
                    }});
                }});

                // heal script
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
                    }});

                    addScriptAction(new ScriptAction() {
                        @Override
                        public ScriptState execute() {
                            //calculate damage
                            int damage = (int) Math.round(Math.random() * 2 * BattleMap.getEnemy().getStrength()); if (damage == 0) damage = 1;

                            //if not crit, deal damage normally
                            if (BattleMap.getEnemy().getCritChance() * Math.random() < 0.9)
                                PlayLevelScreen.getMap().getPlayer().damage(damage);
                            else {//if crit deal double damage
                                PlayLevelScreen.getMap().getPlayer().damage(damage * 2);
                                attackCrit = true;
                            }
                            return ScriptState.COMPLETED;
                        }
                    });

                    //if last enemy attack crit, print message then reset attackCrit boolean
                    scriptActions.add(new ConditionalScriptAction() {{
                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    return attackCrit;
                                }
                            });

                            addScriptAction(new TextboxScriptAction() {{
                                addText("The enemy lands a critical hit!");
                            }});

                            addScriptAction(new ScriptAction() {
                                @Override
                                public ScriptState execute() {
                                    attackCrit = false;
                                    return ScriptState.COMPLETED;
                                }
                            });
                        }});
                    }});

                    //if player dodged last enemy attack, print message then reset attackDodged flag
                    scriptActions.add(new ConditionalScriptAction() {{
                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    return !PlayLevelScreen.flagManager.isFlagSet("attackDodged");
                                }
                            });

                            addScriptAction(new TextboxScriptAction() {{
                                addText("Your ship is damaged!");
                            }});
                        }});

                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    return PlayLevelScreen.flagManager.isFlagSet("attackDodged");
                                }
                            });
            
                            addScriptAction(new TextboxScriptAction() {{
                                addText("You dodged the enemy's attack!");
                            }});
            
                            addScriptAction(new ScriptAction() {
                                @Override
                                public ScriptState execute() {
                                    PlayLevelScreen.flagManager.unsetFlag("attackDodged");
                                    return ScriptState.COMPLETED;
                                }
                            });                    
                        }});
                    }});

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

                //dodge boost script
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new CustomRequirement() {
                        @Override
                        public boolean isRequirementMet() {
                            int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                            return answer == 2;
                        }
                    });

                    addScriptAction(new TextboxScriptAction() {{
                        addText("The enemy lauches an attack!");
                    }});

                    addScriptAction(new ScriptAction() {
                        @Override
                        public ScriptState execute() {
                            //if not crit, deal damage normally
                            if (BattleMap.getEnemy().getCritChance() * Math.random() < 0.9)
                                PlayLevelScreen.getMap().getPlayer().damage((int) Math.round((Math.random()*2) * BattleMap.getEnemy().getStrength()));
                            else {//if crit deal double damage
                                PlayLevelScreen.getMap().getPlayer().damage((int) Math.round((Math.random()*2) * BattleMap.getEnemy().getStrength()) * 2);
                                attackCrit = true;
                            }
                            return ScriptState.COMPLETED;
                        }
                    });

                    //if last enemy attack crit, print message then reset attackCrit boolean
                    scriptActions.add(new ConditionalScriptAction() {{
                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    return attackCrit;
                                }
                            });

                            addScriptAction(new TextboxScriptAction() {{
                                addText("The enemy lands a critical hit!");
                            }});

                            addScriptAction(new ScriptAction() {
                                @Override
                                public ScriptState execute() {
                                    attackCrit = false;
                                    return ScriptState.COMPLETED;
                                }
                            });
                        }});
                    }});

                    //if player dodged last enemy attack, print message then reset attackDodged flag (after usual enemy combat logic)
                    scriptActions.add(new ConditionalScriptAction() {{
                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    return !PlayLevelScreen.flagManager.isFlagSet("attackDodged");
                                }
                            });

                            addScriptAction(new TextboxScriptAction() {{
                                addText("Your ship is damaged!");
                            }});
                        }});

                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    return PlayLevelScreen.flagManager.isFlagSet("attackDodged");
                                }
                            });
            
                            addScriptAction(new TextboxScriptAction() {{
                                addText("You dodged the enemy's attack!");
                            }});
            
                            addScriptAction(new ScriptAction() {
                                @Override
                                public ScriptState execute() {
                                    PlayLevelScreen.flagManager.unsetFlag("attackDodged");
                                    return ScriptState.COMPLETED;
                                }
                            });                    
                        }});
                    }});

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

                    addScriptAction(new ScriptAction() {
                        @Override
                        public ScriptState execute() {
                            player.setDodgeChance(player.getDodgeChance() + 1);
                            return ScriptState.COMPLETED;
                        }
                    });

                    addScriptAction(new TextboxScriptAction() {{
                        addText("You engage evasive maneuvers!");
                    }});
                }});           

                //crit boost script
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new CustomRequirement() {
                        @Override
                        public boolean isRequirementMet() {
                            int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                            return answer == 3;
                        }
                    });

                    addScriptAction(new TextboxScriptAction() {{
                        addText("The enemy lauches an attack!");
                    }});

                    addScriptAction(new ScriptAction() {
                        @Override
                        public ScriptState execute() {
                            //calculate damage
                            int damage = (int) Math.round(Math.random() * 2 * BattleMap.getEnemy().getStrength()); if (damage == 0) damage = 1;

                            //if not crit, deal damage normally
                            if (BattleMap.getEnemy().getCritChance() * Math.random() < 0.9)
                                PlayLevelScreen.getMap().getPlayer().damage(damage);
                            else {//if crit deal double damage
                                PlayLevelScreen.getMap().getPlayer().damage(damage * 2);
                                attackCrit = true;
                            }
                            return ScriptState.COMPLETED;
                        }
                    });

                    //if last enemy attack crit, print message then reset attackCrit boolean
                    scriptActions.add(new ConditionalScriptAction() {{
                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    return attackCrit;
                                }
                            });

                            addScriptAction(new TextboxScriptAction() {{
                                addText("The enemy lands a critical hit!");
                            }});

                            addScriptAction(new ScriptAction() {
                                @Override
                                public ScriptState execute() {
                                    attackCrit = false;
                                    return ScriptState.COMPLETED;
                                }
                            });
                        }});
                    }});

                    //if player didn't dodge, display damage message
                    scriptActions.add(new ConditionalScriptAction() {{
                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    return !PlayLevelScreen.flagManager.isFlagSet("attackDodged");
                                }
                            });

                            addScriptAction(new TextboxScriptAction() {{
                                addText("Your ship is damaged!");
                            }});
                        }});

                        //if player dodged last enemy attack, print message then reset attackDodged flag
                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    return PlayLevelScreen.flagManager.isFlagSet("attackDodged");
                                }
                            });
            
                            addScriptAction(new TextboxScriptAction() {{
                                addText("You dodged the enemy's attack!");
                            }});
            
                            addScriptAction(new ScriptAction() {
                                @Override
                                public ScriptState execute() {
                                    PlayLevelScreen.flagManager.unsetFlag("attackDodged");
                                    return ScriptState.COMPLETED;
                                }
                            });                    
                        }});
                    }});

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

                    addScriptAction(new ScriptAction() {
                        @Override
                        public ScriptState execute() {
                            player.setCritChance(player.getCritChance() + 1);
                            return ScriptState.COMPLETED;
                        }
                    });

                    addScriptAction(new TextboxScriptAction() {{
                        addText("You feel a bit luckier!");
                    }});
                }}); 
            }});
        }

        // scriptActions.add(new ChangeFlagScriptAction("isInCombat", true));
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
