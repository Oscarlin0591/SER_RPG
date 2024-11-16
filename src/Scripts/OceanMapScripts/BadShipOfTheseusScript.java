package Scripts.OceanMapScripts;

import java.util.ArrayList;

import ScriptActions.*;
import Level.Script;
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
                            return PlayLevelScreen.getMap().getFlagManager().isFlagSet("goodShipMoved");
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
                        }});

                        addConditionalScriptActionGroup(new ConditionalScriptActionGroup(){{
                            addRequirement(new CustomRequirement() {
                                @Override
                                public boolean isRequirementMet() {
                                    return PlayLevelScreen.flagManager.isFlagSet("shipDiscussion");
                                }
                            });

                            addScriptAction(new TextboxScriptAction(){{
                                addText("...");
                                addText("This may be a silly question... but humor me.");
                                addText("You're a ship too, ain't yeh?", new String[] {"Yes, I am a ship.", "No, I'm a captain."});
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

                                    
                                }});
                            }});
                        }});
                    }});
                }});
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
