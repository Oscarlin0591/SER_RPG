package Scripts.AtlantisMapScripts;

import java.util.ArrayList;

import Level.Script;
import MapEditor.ChangeMapSizeWindow;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.CustomRequirement;
import ScriptActions.FlagRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.NPCFacePlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class MermaidScript extends Script{
    
            @Override
            public ArrayList<ScriptAction> loadScriptActions() {
                ArrayList<ScriptAction> scriptActions = new ArrayList<>();
            scriptActions.add(new LockPlayerScriptAction());
            scriptActions.add(new NPCFacePlayerScriptAction());

            scriptActions.add(new ConditionalScriptAction() {{
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new FlagRequirement("mermanGuardComplete", true));
                    addScriptAction(new TextboxScriptAction() {{
                        addText("They seem to be happily conversing.");
                    }});
                }});
            }});

            scriptActions.add(new ConditionalScriptAction() {{
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new FlagRequirement("mermaid1", true));
                    addRequirement(new FlagRequirement("wingmanQuest", false));
                    addScriptAction(new TextboxScriptAction() {{
                        addText("Leave me alone...");
                    }});
                }});
            }});

            scriptActions.add(new ConditionalScriptAction() {{
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new FlagRequirement("mermaid1", true));
                    addRequirement(new FlagRequirement("wingmanQuest", true));
                    addRequirement(new FlagRequirement("wingmanIntro", false));
                    addScriptAction(new TextboxScriptAction() {{
                        addText("Leave me alone...", new String[] {"Ye have an admirer."});
                        addText("Wait really? Who?", new String[] {"Aqua, the guard with red hair"});
                        addText("Omg really? I've been eyeing him for a while too! But I don't know too much about him...", new String[]{"I could tell ye more"});
                        addText("That would be wonderful. Let's start then.");
                    }});
                    addScriptAction(new ChangeFlagScriptAction("wingmanIntro", true));
                }});
            }});

            scriptActions.add(new ConditionalScriptAction() {{
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new FlagRequirement("mermaid1", true));
                    addRequirement(new FlagRequirement("wingmanQuest", true));
                    addRequirement(new FlagRequirement("wingmanIntro", true));
                    addRequirement(new FlagRequirement("wingmanDone", true));
                    addScriptAction(new TextboxScriptAction() {{
                        addText("Go call Aqua over!");
                    }});
                    
                }});
            }});


            scriptActions.add(new ConditionalScriptAction() {{
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new FlagRequirement("mermaid1", true));
                    addRequirement(new FlagRequirement("wingmanQuest", true));
                    addRequirement(new FlagRequirement("wingmanIntro", true));
                    addRequirement(new FlagRequirement("wingmanDone", false));
                    addScriptAction(new TextboxScriptAction() {{
                        addText("Soooo... what does Aqua do in his free time?", new String[] {"Training with the guards","Host parties and stuff", "Develop games on a makeshift game engine"});
                    }});
                    scriptActions.add(new ConditionalScriptAction() {{
                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                    return answer == 1;
                                }
                            });

                            addScriptAction(new TextboxScriptAction() {{
                                addText("Oohhh he sounds like fun, I could see myself getting along with him");
                                addText("How about his loyalty? Can you tell if he's loyal?", new String[] {"Can't really say, he seems unserious", "He definitely talks to a bunch of girls at his parties", "He wouldn't hesitate to fight back when I want to see your ruler, he's very loyal"});
                            }});
                            scriptActions.add(new ConditionalScriptAction() {{
                                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                                    addRequirement(new CustomRequirement() {
                                        @Override
                                        public boolean isRequirementMet() {
                                            int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                            return answer == 2;
                                        }
                                    });

                                    addScriptAction(new TextboxScriptAction() {{
                                        addText("Awh he sounds very sweet. I definitely feel secure with someone like that.");
                                        addText("But I feel like there needs to be somethings special that really makes him\nshine as a person, you get me?");
                                        addText("Is there like anything else quirky about him?", new String[] {"He has a jar of dirt from the surface", "He has a polished gold trident", "I don't know anything else"});
                                    }});

                                    scriptActions.add(new ConditionalScriptAction() {{
                                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                                            addRequirement(new CustomRequirement() {
                                                @Override
                                                public boolean isRequirementMet() {
                                                    int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                                    return answer == 1;
                                                }
                                            });

                                            addScriptAction(new TextboxScriptAction() {{
                                                addText("So does every merman guard... what your point?");
                                                addText("Theres definitely something more, ask him then come back");
                                            }});
                                        }});
                                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                                            addRequirement(new CustomRequirement() {
                                                @Override
                                                public boolean isRequirementMet() {
                                                    int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                                    return answer == 2;
                                                }
                                            });
                                            addScriptAction(new TextboxScriptAction() {{
                                                addText("Theres definitely something more, ask him then come back");
                                            }});
                                        }});
                                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                                            addRequirement(new CustomRequirement() {
                                                @Override
                                                public boolean isRequirementMet() {
                                                    int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                                    return answer == 0;
                                                }
                                            });
                                            
                                            addScriptAction(new TextboxScriptAction() {{
                                                addText("OMG I actually think he's the one.");
                                                addText("Go tell Aqua that I definitely feel the same for him");
                                                addText("Thank you, fellow buccaneer. I wish you the best in your travels");
                                            }});
                                            addScriptAction(new ChangeFlagScriptAction("wingmanDone", true));
                                        }});
                                    }});
                                }});
                                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                                    addRequirement(new CustomRequirement() {
                                        @Override
                                        public boolean isRequirementMet() {
                                            int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                            return answer == 0 || answer == 1;
                                        }
                                    });

                                    addScriptAction(new TextboxScriptAction() {{
                                        addText("Ugh. I know I like to party and stuff but I want to be with someone who'll really\ncare about me as much as I will care about him.");
                                        addText("Is that really how he is? I feel like we're missing something.");
                                        addText("Maybe you should clear things up by asking him more.");
                                    }});

                                }});
                            }});
                        }});
                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                    return answer == 0 || answer == 2;
                                }
                            });
                            addScriptAction(new TextboxScriptAction() {{
                                addText("That sounds... lame.");
                                addText("He doesn't sound like that type of person either, try again.");
                            }});
                        }});
                    }});
                }});
            }});

            scriptActions.add(new ConditionalScriptAction() {{
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new FlagRequirement("mermaid1", false));
                    addScriptAction(new TextboxScriptAction() {{
                        addText("What a beautiful day in Atlantis!");
                        addText("What bring you here today, traveler?", new String[] {"I'm looking for your ruler"});
                        addText("Ugh... Let me guess, that golden ship", new String[] {"Ye know about it?"});
                        addText("All you sailor boys care about is \"The Nave d'Oro\" and I'm tired of hearin about it.");
                        addText("Why can't we get a fun pirate who just drinks rum and bring down something like...\nI don't know, a jar of dirt from the surface.", new String[] {"I can bring ye gold if you'd like."});
                        addText("Uggghhhh... go away, you're no fun.");
        
                    }});

                    addScriptAction(new ChangeFlagScriptAction("mermaid1", true));
                }});
            }});

            scriptActions.add(new UnlockPlayerScriptAction());

            return scriptActions;
            }
}
