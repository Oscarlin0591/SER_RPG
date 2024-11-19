package Scripts.StartIslandMap;

import java.util.ArrayList;

import Engine.*;
import Screens.PlayLevelScreen;
import Level.Script;
import ScriptActions.*;

public class TutorialScript extends Script{

    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ChangeFlagScriptAction("jdvdialogue", true));

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return !PlayLevelScreen.getMap().getFlagManager().isFlagSet("jvBeaten");
                    }
                });
                
                //dialogue tree pre-combat
                scriptActions.add(new TextboxScriptAction() {{
                    addText("Ahoy Matey!", new String[] { "Hi?", "Ahoy!" });
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
                            addText("That aint right!");
                            addText("Ye clearly don't know yer hook from yer peg leg, do ya?\nLet's try this again.");
                        }});
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
                            addText("My two intact eyes mistake me! Ye be a bonafide pirate after all!");
                            addText("Guess I don't need to tell ye much of anything at all then.");
                        }});

                        addScriptAction(new TextboxScriptAction() {{
                            addText("Do I?", new String[] { "Yargh!", "Nargh?" });
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
                                    addText("Lets get to it then!");
                                    addText("To move around, ye can use wasd. Feel free to try it out.");
                                    addText("Not right now though, I'm talking. Don't be rude.");
                                    addText("Ye can also use e to interact, though ye may have figured that one\nout already.");
                                    addText("Interacting with certain objects is how ye can sail the seas, or\nboard an island.");
                                    addText("It's even how ye can enter combat! Though it be the space bar\nwhich actually lets one do damage...");
                                }});

                                addScriptAction(new TextboxScriptAction() {{
                                    addText("Wanna give combat a try?", new String[] { "Yargh!", "Hell nargh." });
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
                                        
                                        addScriptAction(new UnlockPlayerScriptAction());

                                        //toggle combat
                                        scriptActions.add(new ChangeFlagScriptAction("jdvdialogue", false));
                                        addScriptAction(new ChangeFlagScriptAction("jvEnemy", true));
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
                                            addText("Begone ye then scallywag!");
                                        }});
                                    }});
                                }});
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
                                    addText("Get sailin then!");
                                }});
                            }});
                        }});
                    }});
                }});
            }});
        //jvbeaten
        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
            addRequirement(new CustomRequirement() {
                @Override
                public boolean isRequirementMet() {
                    return PlayLevelScreen.flagManager.isFlagSet("jvBeaten");
                }
            });

            scriptActions.add(new ConditionalScriptAction() {{
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new CustomRequirement() {
                        @Override
                        public boolean isRequirementMet() {
                            return !PlayLevelScreen.flagManager.isFlagSet("jvSpokenTo");
                        }
                    });

                    addScriptAction(new TextboxScriptAction() {{
                        addText("Looks like ye beat me, fair and square! Now I can tell you even more.");
                        addText("Just, give me a little space before we chat more, will ya?");
                    }});

                    addScriptAction(new ChangeFlagScriptAction("jvSpokenTo", true));
                }});

                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                boolean bossKilled = (PlayLevelScreen.flagManager.isFlagSet("krakenKilled") || PlayLevelScreen.flagManager.isFlagSet("beetleKilled") || PlayLevelScreen.flagManager.isFlagSet("krampusKilled"));
                                return PlayLevelScreen.flagManager.isFlagSet("jvSpokenTo") && !bossKilled;
                            }
                        });

                    addScriptAction(new TextboxScriptAction() {{
                        addText("Ye looking like yer hungry for booty!",new String[] { "I'm looking for the Nave d'Oro... You reckon tellin me 'bout it?", "Buzz off you coxswain!" });
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
                                addText("Of course I do! Not a single tar, lad, or seadog hasn’t heard of the\nfabled Nave d’Oro", new String[] { "Reckon you can tell me more?" });
                                addText("Well it was said that the Nave d’Oro is hidden in the heart of\nthe seven seas! Sealed in a magical barrier.");
                                addText("Only a courageous sea dog who’s sailed around the globe and slain\nthe avatars of safekeeping could that barrier fall.");
                                addText("I ne’er seen anyone return. No matter if they sailed in a raft \nor a man o’ war.");
                                addText("Perhaps you’re the exception, may Neptune’s blessings be upon ye.");
                            }});
                        }});
                    }});

                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                boolean bossKilled = (PlayLevelScreen.flagManager.isFlagSet("krakenKilled") || PlayLevelScreen.flagManager.isFlagSet("beetleKilled") || PlayLevelScreen.flagManager.isFlagSet("krampusKilled"));
                                return bossKilled && !PlayLevelScreen.flagManager.isFlagSet("jvDated");
                            }
                        });

                        addScriptAction(new TextboxScriptAction(){{
                            addText("Seems you're really makin waves out there!");
                            addText("Slayin' beasts and sailin the seven seas... I remember the days...", new String[] {"It's awesome!", "'The days'? You don't seem that old to me..."});
                        }});

                        scriptActions.add(new ConditionalScriptAction(){{
                            addConditionalScriptActionGroup(new ConditionalScriptActionGroup(){{
                                addRequirement(new CustomRequirement() {
                                    @Override
                                    public boolean isRequirementMet() {
                                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                        return answer == 0;
                                    }
                                });

                                addScriptAction(new TextboxScriptAction(){{
                                    addText("Aye. That it is.");
                                }});
                            }});

                            addConditionalScriptActionGroup(new ConditionalScriptActionGroup(){{
                                addRequirement(new CustomRequirement() {
                                    @Override
                                    public boolean isRequirementMet() {
                                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                        return answer == 1;
                                    }
                                });

                                addScriptAction(new TextboxScriptAction(){{
                                    addText("I be older than ye think...");
                                    addText("I be... brace yerself... 22!", new String[] {"Jesus Christ gramps who let you out of the retirement home??", "Dude you're like basically the same age as me."});
                                }});

                                scriptActions.add(new ConditionalScriptAction(){{
                                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup(){{
                                        addRequirement(new CustomRequirement() {
                                            @Override
                                            public boolean isRequirementMet() {
                                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                                return answer == 0;
                                            }
                                        });
        
                                        addScriptAction(new TextboxScriptAction(){{
                                            addText("Arrgh, I broke free.");
                                            addText("Heh heh.");
                                        }});
                                    }});
        
                                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup(){{
                                        addRequirement(new CustomRequirement() {
                                            @Override
                                            public boolean isRequirementMet() {
                                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                                return answer == 1;
                                            }
                                        });
        
                                        addScriptAction(new TextboxScriptAction(){{
                                            addText("Really?");
                                            addText("Thought you were more of the young and naive protagonist type...");
                                            addText("Guess that means ye never even really needed me to teach ye anything\nin the first place...", new String[] {"I can still think of a few things you could teach me... ;)", "I still appreciate your guidance."});
                                        }});
        
                                        scriptActions.add(new ConditionalScriptAction(){{
                                            addConditionalScriptActionGroup(new ConditionalScriptActionGroup(){{
                                                addRequirement(new CustomRequirement() {
                                                    @Override
                                                    public boolean isRequirementMet() {
                                                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                                        return answer == 0;
                                                    }
                                                });
                
                                                addScriptAction(new TextboxScriptAction(){{
                                                    addText("Oh my Poughkeepsie Johnson.");
                                                    addText("I... I could lose my job as a tutorial NPC for this.");
                                                    addText("But god ye seem worth it to me.");
                                                    addText("I'll teach ye anythin ye need to know!");
                                                }});

                                                addScriptAction(new ChangeFlagScriptAction("jvDate", true));
                                                addScriptAction(new ChangeFlagScriptAction("dateTriggered", true));
                                            }});
                
                                            addConditionalScriptActionGroup(new ConditionalScriptActionGroup(){{
                                                addRequirement(new CustomRequirement() {
                                                    @Override
                                                    public boolean isRequirementMet() {
                                                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                                        return answer == 1;
                                                    }
                                                });
                
                                                addScriptAction(new TextboxScriptAction(){{
                                                    addText("Thanks man.");
                                                    addText("Ack, how'd we even end up talking about me insecurities?");
                                                    addText("Get out there and get sailin!");
                                                }});
                                            }});
                                        }});
                                    }});
                                }});
                            }});
                        }});
                    }});

                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                return PlayLevelScreen.flagManager.isFlagSet("jvDated");
                            }
                        });

                        addScriptAction(new TextboxScriptAction(){{
                            addText("That date was incredible!");
                            addText("Definitely the best date I've had in a long time.");
                            addText("I love you so much lets have babies and get married the end fade to black you win the game.");
                        }});
                    }});
                }});
            }});
        }});
    }});

    scriptActions.add(new ChangeFlagScriptAction("jdvdialogue", false));
    
    scriptActions.add(new UnlockPlayerScriptAction());

    return scriptActions;
    }
}