package Scripts.AtlantisMapScripts;

import java.util.ArrayList;

import Level.Script;
import Level.ScriptState;
import Screens.PlayLevelScreen;
import ScriptActions.*;
import Utils.Direction;


// trigger script at beginning of game to set that heavy emotional plot
// checkout the documentation website for a detailed guide on how this script works
public class MermanGuard1Script extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        
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
                addRequirement(new FlagRequirement("merman1", true));
                addRequirement(new FlagRequirement("wingmanQuest", true));
                addRequirement(new FlagRequirement("wingmanDone", false));

                addScriptAction(new TextboxScriptAction() {{
                    addText("Well? How is it going so far?", new String[]{"Still working on it.","Tell me about yourself again"});
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
                            addText("There is no haste, I have high expectations for you.", new String[] {"Thanks"});
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
                            addText("About me? Well I like drinking, partying, and serious about commitments whether its\nmy job or intrapersonal relationships");
                            addText("And I have this jar of dirt from the surface");
                            addText("Need anything more?", new String[] {"'Tis all I need. Thanks."});
                        }});
                    }});
                }});
            }});
        }});
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("merman1", true));
                addRequirement(new FlagRequirement("wingmanQuest", false));
                addRequirement(new FlagRequirement("wingmanDone", false));
                addScriptAction(new TextboxScriptAction() {{
                    addText("What will it be?", new String[] { "This is a pain on the arse, prepare for a duel!", "What offer do ye want?" });
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
                        addText("Very well...");
                        addText("These azure depths will be your resting place...");
                    }});
                    addScriptAction(new ChangeFlagScriptAction("mermanEnemy", true));
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
                        addText("Ah, so you've selected diplomacy... wise choice", new String[]{"Stop dilly-dallyig, what do ye ask of me?"});
                        addText("Now you might find this peculiar, but I've taken a fancy with one of the mermaids that over west of\nthe castle");
                        addText("THe one with the dark hair, Azalea. We've only exchanged pleasantries but I have not\nthe courage to talk to her more.");
                        addText("But I often catch her throwing glances at me, as much as I glance at her. I feel like\nwe might have mutual feelings for each other.");
                        addText("If you could go talk to her and find out if thats true, I'll let you pass and see our ruler.", new String[] {"Apologies but why not just confess to her yourself?"});
                        addText("Ah... You see I follow some rather... improper... habits.", new String[]{"Like?"});
                        addText("I often indulge in alcohol and host/attend parties around the city, and I also\ngot this jar of dirt from that one sailor that came aorund some time ago.",new String[] {"Think ye might be just dandy for the lass"});
                        addText("You think so?", new String[]{"Ye, I'll go talk to her"});
                        addText("Thank you, sailor. By the way the names is Aqua.", new String[] {"'Tis a pleasure"});
                    }});
                    addScriptAction(new ChangeFlagScriptAction("wingmanQuest", true));
                }});
            }});

            }});
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("merman1", true));
                addRequirement(new FlagRequirement("wingmanQuest", true));
                addRequirement(new FlagRequirement("wingmanDone", true));
                addRequirement(new FlagRequirement("mermanGuardComplete", false));

                addScriptAction(new TextboxScriptAction() {{
                    addText("How did it go?", new String[]{"She likes ye."});
                    addText("Really? You're speakign the truth?", new String[]{"Couldn't speak truer words"});
                    addText("Oh man, this is quite the revelation");
                    addText("I sincerely thank you, sailor, with your help I found true love");
                    addText("You may pass and see the ruler", new String[]{"Thank you!"});
                }});

                addScriptAction(new NPCWalkScriptAction(Direction.UP, (PlayLevelScreen.getMap().getNPCById(13).getY()-PlayLevelScreen.getMap().getNPCById(11).getY()), 10));
                addScriptAction(new NPCWalkScriptAction(Direction.LEFT, (PlayLevelScreen.getMap().getNPCById(13).getX()-PlayLevelScreen.getMap().getNPCById(11).getX()-50), 10));

                addScriptAction(new ChangeFlagScriptAction("mermanGuardComplete", true));
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}

