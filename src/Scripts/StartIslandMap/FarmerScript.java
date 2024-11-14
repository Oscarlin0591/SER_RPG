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
                        addRequirement(new FlagRequirement("treeBroken", true));
                        
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
                                            addText("Le fixed me tree ;')");
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