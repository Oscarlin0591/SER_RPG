package Scripts.ArcticMapScripts;

import java.util.ArrayList;

import Level.Script;
import Screens.PlayLevelScreen;
import ScriptActions.*;

public class KrampusScript extends Script{

    protected boolean done = false;

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCLockScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
            addRequirement(new FlagRequirement("krampusQuestComplete", true));
            if (PlayLevelScreen.getMap().getFlagManager().isFlagSet("krampusQuestComplete")) {
                done = true;
            }
    
            addScriptAction(new TextboxScriptAction() {{
                addText("My wand!");
                addText("I suppose I should return the favor since you helped me", new String[] {"Is the Nave d'Oro that dangerous for you to guard it so fiercely?"});
                addText("The Nave d'Oro? Its not the ship but whats within...", new String[] {"What is inside?", "The King Midas corpse?"});
                addText("The King Midas corpse...");
                addText("That wicked king terrorized neighboring lands just so he can get more gold");
                addText("But honestly that is not even why I keep people out", new String[] {"Why do you keep people out then?"});
                addText("There was another young man once that sailed here in search of the Nave d'Oro");
                addText("I let him through hoping he'd be able to destroy the ship");
                addText("But alas! He never returned...");
                addText("Please, destroy the ship. I wish you best of luck...");
                }});
            }});
        }});
        
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
            addRequirement(new FlagRequirement("krampusQuestComplete", false));
            addRequirement(new FlagRequirement("talkedToKrampus", false));

            addScriptAction(new TextboxScriptAction() {{
                addText("Well, well, well...\nWhat do we have here?", new String[] {"..."});
                addText("Another distant traveler I see...", new String[]{"Ye had visitors before?"});
                addText("Of course, many of them are like you, yearning for the ol' Nave d'Oro");
                addText("All of them too young, too fresh to grasp the weight of their pursuit...");
                addText("Ye come to me to have me taketh down the barrier, correct?", new String[] {"Can ye? It'd do me a favor"});
                addText("HO HO HO! Favors? An old sage like me has seen what my favors had done");
            }});
            
            addScriptAction(new ChangeFlagScriptAction("talkedToKrampus", true));
            }});

        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("krampusQuestComplete", false));
                addRequirement(new FlagRequirement("krampusQuest", true));
                
                addScriptAction(new TextboxScriptAction() {{
                    addText("Well what are ye waiting for? Ye have maidens to charm!");
                }});
            }});
            
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("krampusQuest", false));
                
                addScriptAction(new TextboxScriptAction() {{
                    addText("Sorry lad, I ain't performing ye favors lest I get something in return", new String[] {"Talking to you is futile, draw yer weapons!", "What favors can I do?"});
                }});

        scriptActions.add(new ConditionalScriptAction() {{
        addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
            addRequirement(new FlagRequirement("krampusQuest", false));
            addRequirement(new FlagRequirement("talkedToKrampus", true));
            
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
            addRequirement(new CustomRequirement() {
                @Override
                public boolean isRequirementMet() {
                    int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                    System.out.println(PlayLevelScreen.getMap().getFlagManager().isFlagSet("krampusQuest"));
                    return answer == 0;
                }
            });
            addScriptAction(new TextboxScriptAction() {{
                addText("Ah, I see how ye is...");
                addText("Always eager to defer to yer blades and guns");
                addText("Very well, I'll entertain ye in this duel");
            }});

            addScriptAction(new ChangeFlagScriptAction("krampusEnemy", true));
            addScriptAction(new ChangeFlagScriptAction("combatTriggered", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("krampusQuest", false));
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1;
                    }
                });
    
                addScriptAction(new TextboxScriptAction() {{
                    addText("Ye agree to help? What a surprise...");
                    addText("If thats the case, here what I need you to do...");
                    addText("I lost my dear magic wand in a tussle I had with Saint Nicholas of the North Pole.");
                    addText("There inhabits a witch here that might be able to make another");
                    addText("The young lass is quite new here, and I think I frighten her with my appearance");
                    addText("Ye need to converse with her and ask her to make me a new shiny wand!", new String[] {"But I can't talk to lasses!", "Sure thing, I'll see what I can do"});
                }});

                scriptActions.add(new ConditionalScriptAction() {{
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new FlagRequirement("krampusQuestComplete", false));
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                return answer == 0;
                            }
                        });
    
                        addScriptAction(new TextboxScriptAction() {{
                            addText("Well blow me down, then!");
                            addText("Ye sailing the high seas, fightin through waves and monsters, and yet cannot converse with a lady?", new String[]{"Me have social anxiety, I'm afraid"});
                            addText("Oh buzz off, social anxiety? Ye making that up!", new String[] {"It's very real"});
                            addText("Ye know what my kind does for yer ailment?", new String[] {"...What do ye do?"});
                            addText("We make the lad talk! Ye younguns so entranced in yer \"innovations\" that ye bunch can't hold\nno conversation with yer kin, ye don't think thats an issue?", new String[]{"You don't get it"});
                            addText("Of course I get it! Both ye and the funny people looking in through the sky need to just toughen up and just talk! Ye hear?", new String[] {"Aye, I do...", "What people in the sky?"});
                            addText("Anyways. Take this magical tome and go talk with the lass! I'm sure a witch like her would fancy trinkets like this.");
                            addText("Now get a move on!");
                        }});
                    }});
    
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{   
                        addRequirement(new FlagRequirement("krampusQuestComplete", false));
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                return answer == 1;
                            }
                        });
    
                        addScriptAction(new TextboxScriptAction() {{
                            addText("Ye have me thanks, traveler.");
                            addText("Here take this tome, a witch loves magical spells");
                            addText("Now get a move on!");
    
                        }});
    
                    }});

                }});
                addScriptAction(new ChangeFlagScriptAction("krampusQuest", true));
            }});
        }});
                }});
        }});
    }});
    // }
    

    scriptActions.add(new UnlockPlayerScriptAction());

    return scriptActions;
    }
    
}
