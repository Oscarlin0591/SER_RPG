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
                            addText("Ye clearly don't know yer hook from yer peg leg, do ya? Let's try this again.");
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
                                    addText("Not right now though I'm talking. Don't be rude.");
                                    addText("Ye can also use e to interact, though ye may have figured that one\nout already.");
                                    addText("Interacting with certain objects is how ye can sail the seas, or\nboard an island.");
                                    addText("It's even how ye can enter combat!");
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
                        addText("Gimme a little space before we chat more, will ya?");
                    }});

                    addScriptAction(new ChangeFlagScriptAction("jvSpokenTo", true));
                }});

                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                return PlayLevelScreen.flagManager.isFlagSet("jvSpokenTo");
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
                }});
            }});
        }});
    }});

    scriptActions.add(new ChangeFlagScriptAction("jdvdialogue", false));
    
    scriptActions.add(new UnlockPlayerScriptAction());

    return scriptActions;
    }
}