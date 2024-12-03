package Scripts.ShipwreckScripts;

import java.util.ArrayList;

import Level.Script;
import Screens.PlayLevelScreen;
import ScriptActions.*;
import Utils.Visibility;

public class KrakenScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new ConditionalScriptAction(){{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("krakenQuestCompleted", true));

                addScriptAction(new TextboxScriptAction () {{
                    addText("Congratulations on answering correctly.");
                    addText("I will keep my word.");
                    addText("The reason for collecting ships...");
                    addText("Is because I like them.", new String[]{"That's not a very satifying reason."});
                    addText("Never said it would be.\nAnd what do you care, uyou have my help concerning the barrier.");
                    addText("Now, human, leave my sight.");
                }});
                
                
            }});
        }});
        
        scriptActions.add(new ConditionalScriptAction(){{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("krakenFirstTalk", true));
                addRequirement(new FlagRequirement("krakenQuestCompleted", false));
    
                
                addScriptAction(new TextboxScriptAction () {{
                    addText(".....");
                    addText("Back so soon?", new String[] { "Enough of yer puzzle, we're doing this the old fashioned way!", "Ask me the question again." });
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
                            scriptActions.add(new ChangeFlagScriptAction("krakenEnemy", true));
                            scriptActions.add(new ChangeFlagScriptAction("combatTriggered", true));
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
                            scriptActions.add(new ChangeFlagScriptAction("krakenPuzzleTriggered", true));
                        }});
                    }});
                }});
                
            }});
        }});

        scriptActions.add(new ConditionalScriptAction(){{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("krakenFirstTalk", false));
                addScriptAction(new TextboxScriptAction () {{
                    addText(".....");
                    addText("You step before me mortal, and for what?", new String[] {"Bring down the barrier to the Nave d'Oro"});
                    addText("Is that all?");
                    addText("Go, before I tire of you.");
                }});
        }});
        
    }});

        scriptActions.add(new ConditionalScriptAction(){{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("krakenFirstTalk", false));
                addScriptAction(new TextboxScriptAction () {{
                    addScriptAction(new WaitScriptAction(70));
                    addText("Are you not afraid? Afraid of the being that is before you?", new String[] {"Yer not that scary"});
                    addText("....");
                    addText("Is that your measly ship over there?", new String[] { "Yer not touching it! Have at ye!", "Why collect ships?" });
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
                            //addText("Well I think I should just pluck it out of the water and\n add it to my collection.");
                            //scriptActions.add(new WaitScriptAction(1));
                            scriptActions.add(new ChangeFlagScriptAction("krakenEnemy", true));
                            scriptActions.add(new ChangeFlagScriptAction("combatTriggered", true));
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
                            addText("Well didn't expect you to get philosophical.");
                            addText(".....");
                            addText("Tell you what, answer my riddle and not only will I lower the barrier,\nI will also tell you why I collect these ships.", new String[] {"Yer on!"});
                            scriptActions.add(new ChangeFlagScriptAction("krakenFirstTalk", true));
                            scriptActions.add(new ChangeFlagScriptAction("saidYesToPuzzle", true));
                            //scriptActions.add(new ConditionalScriptAction() {{
                                //start
                                /*scriptActions.add(new ConditionalScriptAction() {{
                                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                                        addRequirement(new CustomRequirement() {
                                            @Override
                                            public boolean isRequirementMet() {
                                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                                return answer == 0;
                                            }
                                        });
                        
                                        addScriptAction(new TextboxScriptAction() {{
                                            //addText("Well I think I should just pluck it out of the water and\n add it to my collection.");
                                            //scriptActions.add(new WaitScriptAction(1));
                                            
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
                                                scriptActions.add(new ChangeFlagScriptAction("krakenFirstTalk", true));
                                            scriptActions.add(new ChangeFlagScriptAction("krakenPuzzleTriggered", true));
                                            
                                        }});
                                    }});
                                }});*/
                                //end
                                //scriptActions.add(new ChangeFlagScriptAction("krakenFirstTalk", true));
                                //scriptActions.add(new ChangeFlagScriptAction("krakenPuzzleTriggered", true));
                            //}});
                            
                        }});
                    }});
                }});
                
            }});
        
        }});
        
        scriptActions.add(new ConditionalScriptAction(){{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("saidYesToPuzzle", true));

                /*addScriptAction(new TextboxScriptAction () {{
                    addText("Congratulations on answering correctly.");
                    addText("I will keep my word.");
                    addText("The reason for collecting ships...");
                    addText("Is because I like them.", new String[]{"That's not a very satifying reason."});
                    addText("Never said it would be.\nAnd what do you care, uyou have my help concerning the barrier.");
                    addText("Now, human, leave my sight.");
                }});*/
                scriptActions.add(new ChangeFlagScriptAction("krakenPuzzleTriggered", true));
                scriptActions.add(new ChangeFlagScriptAction("saidYesToPuzzle", false));
                
            }});
        }});
        scriptActions.add(new UnlockPlayerScriptAction());
        

        return scriptActions;
    }
}