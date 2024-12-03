package Scripts.CaveMapScripts;

import Level.FlagManager;
import Level.Script;
import ScriptActions.*;
import Utils.Direction;
import Utils.Visibility;

import java.util.ArrayList;

public class BossBeetleScript extends Script{

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCLockScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        // scriptActions.add(new NPCWalkScriptAction(Direction.RIGHT, 196, 2));
        // scriptActions.add(new NPCWalkScriptAction(Direction.UP, 196, 2));

        // scriptActions.add(new TextboxScriptAction() {{
        //     addText("WHO DARES TINKERING ABOUT MY THRONE?!?!");
        // }});
        // scriptActions.add(new NPCChangeVisibilityScriptAction(Visibility.VISIBLE));
        // scriptActions.add(new NPCWalkScriptAction(Direction.DOWN, 196, 2));
        // scriptActions.add(new NPCWalkScriptAction(Direction.LEFT, 196, 2));

        scriptActions.add(new ConditionalScriptAction(){{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("beetleBeaten", true));

                addScriptAction(new TextboxScriptAction(){{
                    addText("You beat me.");
                    addText("As agreed, I will allow you through the barrier to the best of my ability.");
                    addText("I only ask you be careful.");
                    addText("A terrible fate awaits us if you blunder...");
                }});

                addScriptAction(new ChangeFlagScriptAction("beetleQuestCompleted", true));
            }});
            
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("beetleBeaten", false));

                scriptActions.add(new TextboxScriptAction() {{
                    addText("YOU!");
                    addText("A foreign adversary I see...");
                    addText("Whatever business you may have here in our cave... take it elsewhere.");
                    addText("You are not welcome.", new String[] { "I need you to take down the barrier", "No one asked, let's fight." });
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
                            addText("The barrier?");
                            addText("And why would you need the this barrier taken down?", new String[] {"I want to get the Nave d'Oro"});
                            addText("The Nave d'Oro, eh?");
                            addText("Over my dead exoskeleton!");
                            addText("You're just like the others, so eager on the shine of gold that you are\nblinded by the terror that could be released if I let that barrier down...");
                            addText("There are no other options. En Garde!", new String[] {"Wait! Please, let us speak on this for just a moment more.", "Very well. Bring it on!"});
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
                            
                                addScriptAction(new TextboxScriptAction(){{
                                    addText("I am a warrior! I do not settle such matters with words, sea rat.");
                                    addText("I fight. That is the language I speak.", new String[] {"Will you willingly yield then if I defeat you?", "Well that's just plain stupid, aren't we're talking right now?"});
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

                                        addScriptAction(new TextboxScriptAction(){{
                                            addText("Such is consistent with my stated ideology on settling such matters and such.");
                                            addText("Very well...");
                                        }});

                                        addScriptAction(new ChangeFlagScriptAction("beetleQuestComplete", true));
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
                                            addText("Yknow that's actually a very good point.");
                                            addText("I NEED TO LIVE TRUE TO MYSELF!");
                                        }});
                                    }});
                                }});
                                
                                addScriptAction(new TextboxScriptAction(){{
                                    addText("Have at ye!");
                                }});

                                addScriptAction(new UnlockPlayerScriptAction());

                                addScriptAction(new ChangeFlagScriptAction("beetleEnemy", true));
                                addScriptAction(new ChangeFlagScriptAction("combatTriggered", true));
                            }});

                            addConditionalScriptActionGroup(new ConditionalScriptActionGroup(){{
                                addRequirement(new CustomRequirement() {
                                    @Override
                                    public boolean isRequirementMet() {
                                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                        return answer == 1;
                                    }
                                });
                            
                                addScriptAction(new UnlockPlayerScriptAction());

                                addScriptAction(new ChangeFlagScriptAction("beetleEnemy", true));
                                addScriptAction(new ChangeFlagScriptAction("combatTriggered", true));
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
                            addText("What a sour attitude you have!");
                            addText("Think you could take me on? Bring it!");
                        }});
                        
                        addScriptAction(new UnlockPlayerScriptAction());

                        addScriptAction(new ChangeFlagScriptAction("beetleEnemy", true));
                        addScriptAction(new ChangeFlagScriptAction("combatTriggered", true));
                    }});
                }});
            }});
        }});

        // scriptActions.add(new NPCUnlockScriptAction());
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
