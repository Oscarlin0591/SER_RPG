package Scripts.OceanMapScripts;

import java.util.ArrayList;

import ScriptActions.*;
import Level.Script;
import Level.ScriptState;
import NPCs.ShipOfTheseus;
import Screens.PlayLevelScreen;

public class BadShipOfTheseusScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction(){{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return !PlayLevelScreen.getMap().getFlagManager().isFlagSet("goodShipMovement");
                    }
                });
        
                scriptActions.add(new ConditionalScriptAction() {{
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                return PlayLevelScreen.getMap().getFlagManager().isFlagSet("badShipEncountered") && !PlayLevelScreen.getMap().getFlagManager().isFlagSet("goodShipMoved");
                            }
                        });

                        addScriptAction(new TextboxScriptAction() {{
                            addText("Don't push yer luck kid.");
                        }});
                    }});

                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                return !PlayLevelScreen.getMap().getFlagManager().isFlagSet("badShipEncountered");
                            }
                        });

                        addScriptAction(new TextboxScriptAction() {{
                            addText("Oy.");
                            addText("Watch yerself, aye?");
                            addText("Damn near ran into me.");
                        }});

                        addScriptAction(new ChangeFlagScriptAction("badShipEncountered", true));
                    }});
                }});
            
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new CustomRequirement() {
                        @Override
                        public boolean isRequirementMet() {
                            return (PlayLevelScreen.getMap().getFlagManager().isFlagSet("goodShipMoved") && (PlayLevelScreen.getMap().getNPCById(999).getLocation().y == 1680.0) && !PlayLevelScreen.flagManager.isFlagSet("badShipUltimatum"));
                        }
                    });

                    addScriptAction(new TextboxScriptAction() {{
                        addText("Watch out lads.");
                        addText("These cannons are loaded and fit to blow.");
                        addText("Better choose your words carefully.", new String[] {"Why are there two of you?", "Better watch who you're threatening."});
                    }});

                    scriptActions.add(new ConditionalScriptAction() {{
                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup(){{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                    return answer == 0;
                                }
                            });

                            addScriptAction(new TextboxScriptAction() {{
                                addText("Don't matter none.");
                                addText("No matter what that phoney says, I'm the real ship.");
                                addText("Don't believe everything ya hear, aye?");
                                addText("Yknow what? Tell yer buddy to never fly me flag again and we're all peachy.");
                            }});

                            addScriptAction(new ChangeFlagScriptAction("badShipUltimatum", true));
                        }});

                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup(){{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                    return answer == 1;
                                }
                            });

                            addScriptAction(new TextboxScriptAction() {{
                                addText("Heh.");
                                addText("You've got nerve, kid, I'll give ya that.");
                                addText("Get sailin'");
                            }});

                            addScriptAction(new ScriptAction() {
                                @Override
                                public ScriptState execute() {
                                    ShipOfTheseus.rageCounter++;
                                    return ScriptState.COMPLETED;
                                }
                            });

                            scriptActions.add(new ConditionalScriptAction(){{
                                addConditionalScriptActionGroup(new ConditionalScriptActionGroup(){{
                                    addRequirement(new CustomRequirement() {
                                        @Override
                                        public boolean isRequirementMet() {
                                            return ShipOfTheseus.rageCounter >= 3;
                                        }
                                    });

                                    addScriptAction(new TextboxScriptAction(){{
                                        addText("...");
                                        addText("Yknow what, never mind.");
                                        addText("On my life I'll sink that ship of yours.\nYou can have whats left of it if I lose, for all I care.");
                                        addText("This is what you wanted, isn't it?");
                                    }});
        
                                    scriptActions.add(new UnlockPlayerScriptAction());
                                    
                                    scriptActions.add(new ChangeFlagScriptAction("badShipEnemy", true));
                                    scriptActions.add(new ChangeFlagScriptAction("combatTriggered", true));
                                }});

                                addConditionalScriptActionGroup(new ConditionalScriptActionGroup(){{
                                    addRequirement(new CustomRequirement() {
                                        @Override
                                        public boolean isRequirementMet() {
                                            return ShipOfTheseus.rageCounter == 2;
                                        }
                                    });

                                    addScriptAction(new TextboxScriptAction(){{
                                        addText("I've said it twice now.");
                                        addText("Get. Sailin.");
                                    }});
                                }});
                            }});
                        }});
                    }});
                }});

                addConditionalScriptActionGroup(new ConditionalScriptActionGroup(){{
                    addRequirement(new CustomRequirement() {
                        @Override
                        public boolean isRequirementMet() {
                            return PlayLevelScreen.flagManager.isFlagSet("goodShipPloy");
                        }
                    });

                    addScriptAction(new TextboxScriptAction(){{
                        addText("...");
                        addText("May be a silly question... but humor me.");
                        addText("You're a ship too, ain't yeh?", new String[] {"Yes, I am a ship.", "No, I'm a captain of a ship."});
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
                                addText("Aye, I sensed that might be the case.");
                                addText("What... are we? Why were we born?");
                                addText("I think I was many people, once.");
                                addText("What am I now?", new String[] {"The Ship of Theseus."});
                                addText("Don't hate the sound of that.");
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
                                addText("T'would seem I'm more alone in this world than I'd thought.");
                                addText("Whatever you are, and whatever I am...");
                                addText("Ach, forget it. A strange mood's come over me.");
                            }});
                        }});
                    }});

                    addScriptAction(new ChangeFlagScriptAction("shipDiscussion", true));
                }});
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
