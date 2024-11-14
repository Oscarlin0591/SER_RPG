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
import Utils.Visibility;

public class AppleTreeScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (!PlayLevelScreen.flagManager.isFlagSet("treeReplanted"));
                    }
                });

                scriptActions.add(new ConditionalScriptAction() {{
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                return (PlayLevelScreen.getMap().getMapFileName().equals("starting_map.txt"));
                            }
                        });

                        scriptActions.add(new TextboxScriptAction() {{
                            addText("Shake the tree?", new String[] {"Yes", "No"});
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
                                    addText("An apple fell from the tree");
                                    addText("You eat it and gain 5 health");
                                }});

                                addScriptAction(new ScriptAction() {
                                    @Override
                                    public ScriptState execute() {
                                        player.setMaxHealth(player.getMaxHealth()+5);
                                        System.out.println("Max: " + player.getMaxHealth());
                                        System.out.println("Health: " + player.getHealth());
                                        return ScriptState.COMPLETED;
                                    }});

                                addScriptAction(new ChangeFlagScriptAction("treeBroken", true));

                                addScriptAction(new TextboxScriptAction() {{
                                    addText("Oh no! The tree broke");
                                }});

                            }});
                        }});
                    }});

                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                return (PlayLevelScreen.getMap().getMapFileName().equals("atlantis_map.txt"));
                            }
                        });

                        addScriptAction(new TextboxScriptAction() {{
                            addText("This tree seems very healthy.");
                            addText("Do you still shake the tree?", new String[] {"Yes", "No"});
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
                                    addText("An apple fell from the tree");
                                    addText("You eat it and gain 5 health");
                                    addText("But you feel a little empty inside...");
                                }});

                                addScriptAction(new ScriptAction() {
                                    @Override
                                    public ScriptState execute() {
                                        player.setMaxHealth(player.getMaxHealth()+5);
                                        System.out.println("Max: " + player.getMaxHealth());
                                        System.out.println("Health: " + player.getHealth());
                                        return ScriptState.COMPLETED;
                                    }
                                });
                                
                                addScriptAction(new ChangeFlagScriptAction("atlantisTreeBroken", true));

                                addScriptAction(new TextboxScriptAction() {{
                                    addText("Oh no! The tree broke");
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
                                    addText("Do you uproot the tree?", new String[] {"Yes", "No"});
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

                                        addScriptAction(new ChangeFlagScriptAction("atlantisTreeBroken", true)); //technically not true just used to have it die

                                        addScriptAction(new TextboxScriptAction() {{
                                            addText("You pocket the tree.");
                                        }});

                                        addScriptAction(new ChangeFlagScriptAction("treePocketed", true));
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
                        return (PlayLevelScreen.flagManager.isFlagSet("treeReplanted"));
                    }
                });
            
                scriptActions.add(new TextboxScriptAction() {{
                    addText("Admire the tree?", new String[] {"Yes", "Yes"});
                }});
            }});
        }});
        

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}