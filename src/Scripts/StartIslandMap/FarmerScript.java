package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import Level.ScriptState;
import Screens.PlayLevelScreen;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.CustomRequirement;
import ScriptActions.FlagRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.NPCChangeVisibilityScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import Utils.Visibility;

public class FarmerScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.flagManager.isFlagSet("sonReveal") && !PlayLevelScreen.flagManager.isFlagSet("appleHaunted"));
                    }
                });

                addScriptAction(new TextboxScriptAction(){{
                    addText("What wrong kiddo, looks like you've seen a ghost...", new String[] {"You'd be right about about that, pops."});
                    addText("Only me boy ever called me pops... Don't tell me...");
                    addText("...");
                    addText("Me boys become a blasted sea ghost...", new String[] {"You and him are both pretty quick on the uptake, huh."});
                    addText("It's selfish to ask yeh this after all you've already done me, but...");
                    addText("Please, do me a favor, kiddo.");
                    addText("Gimme a sec.");
                }});

                addScriptAction(new ChangeFlagScriptAction("treeBroken", true));

                addScriptAction(new TextboxScriptAction(){{
                    addText("Me... le pirate, me broke me tree...");
                    addText("Please take this apple to him...");
                    addText("Tis a pitiful grave offerin, but its all I've got.");
                    addText("I only pray it offers his lost soul some meager comfort yet...");
                }});

                addScriptAction(new ChangeFlagScriptAction("appleGiven", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.flagManager.isFlagSet("appleHaunted") && !PlayLevelScreen.flagManager.isFlagSet("treeHaunted"));
                    }
                });

                addScriptAction(new TextboxScriptAction(){{
                    addText("You brought the apple back?", new String[] {"Okay don't freak out but when I gave it to your son, he disappeared."});
                    addText("So it was enough for him to pass on... I'm glad.");
                    addText("I'll see him again someday, in the next world...");
                    addText("Give me that apple back, I'll replant the ol family tree.");
                    addText("It's what me boy would've wanted...");
                }});

                addScriptAction(new ChangeFlagScriptAction("treeBroken", false));
                addScriptAction(new ChangeFlagScriptAction("treeHaunted", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.flagManager.isFlagSet("reunitedAtLast"));
                    }
                });

                addScriptAction(new TextboxScriptAction(){{
                    addText("You le pirate...");
                    addText("le fixed me boy!");
                    addText("Yer a member of this family now.");
                    addText("Take this apple and plant it some place cool, me boy.");
                    addText("And ye better not die doin it!");
                }});
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (!PlayLevelScreen.flagManager.isFlagSet("treeReplanted"));
                    }
                });

                scriptActions.add(new ConditionalScriptAction() {{
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new FlagRequirement("treeBroken", false));

                        addScriptAction(new TextboxScriptAction() {{
                            addText("hey there kiddo!");
                            addText("what do you think of my apple tree?", new String[] {"it's pretty cool!", "meh..."});
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
                                    addText("thanks kiddo!");
                                    addText("i'll let you in on a little secret, if you give my tree a\nshake, an apple should fall");
                                    addText("that tree's pretty weak though, hard to grow anything here");
                                    addText("if yeh get the chance, yeh should seek out Atlantis...\nthat is, if yeh hunger for exploration");
                                    addText("...");
                                    addText("...like my boy used to");
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
                                    addText("hey kiddo, learn some manners would you...");
                                }});
                            }});
                        }});
                    }});
                
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                return PlayLevelScreen.flagManager.isFlagSet("treeBroken") && !PlayLevelScreen.flagManager.isFlagSet("sonReveal");
                            }
                        });
                        
                        addScriptAction(new TextboxScriptAction() {{
                            addText("hey there kiddo!");
                            addText("what do you think of my-");
                            addText("My- le tree");
                            addText("You... le pirate, le broke me tree...");
                            addText("And to think yeh reminded me of my boy...");
                        }});
                        
                        scriptActions.add(new ConditionalScriptAction() {{
                            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                                addRequirement(new FlagRequirement("treePocketed", true));
                                
                                addScriptAction(new TextboxScriptAction() {{
                                    addText("...hey wait a minute");
                                    addText("whats that there pokin out of yer pocket?");
                                    addText("kiddo... is that...?", new String[] {"I brought it back here for you", "Don't worry about it"});
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
                                            addText("Kiddo I... I don't even know what to say");
                                            addText("Thank you so much");
                                            addText("Let me put that back where it should be.");
                                        }});

                                        addScriptAction(new ChangeFlagScriptAction("treeBroken", false));
                                        addScriptAction(new ChangeFlagScriptAction("treeReplanted", true));
                                        
                                        addScriptAction(new TextboxScriptAction() {{
                                            addText("You, le pirate...");
                                            addText("le fixed me tree ;')");
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
                        return (PlayLevelScreen.flagManager.isFlagSet("treeReplanted"));
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("It's a beautiful tree, isn't it kiddo?");
                }});
            }});
        }});
        
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}