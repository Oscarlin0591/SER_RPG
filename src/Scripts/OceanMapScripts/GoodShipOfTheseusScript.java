package Scripts.OceanMapScripts;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import GameObject.Frame;
import ScriptActions.*;
import Utils.Point;
import Level.MapTile;
import Level.Script;
import Level.ScriptState;
import Maps.OceanMap;
import Screens.PlayLevelScreen;

public class GoodShipOfTheseusScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return PlayLevelScreen.getMap().getFlagManager().isFlagSet("goodShipEncountered");
                    }
                });
                
                scriptActions.add(new ConditionalScriptAction() {{
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                return (PlayLevelScreen.getMap().getFlagManager().isFlagSet("goodShipInformed") && !PlayLevelScreen.flagManager.isFlagSet("goodShipMoved"));
                            }
                        });
                    
                        addScriptAction(new TextboxScriptAction(){{
                            addText("It's driving me insane.");
                            addText("There can't be two of me ship sailin the seas.");
                            addText("We should meet up and talk this out.");
                            addText("Aye, I think that's what I'll do.");
                        }});

                        addScriptAction(new ChangeFlagScriptAction("goodShipMoved", true));
                    }});

                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                //if goodShipMove flag tripped and ship physically moved and badShipUltimatum not yet tripped
                                return (PlayLevelScreen.flagManager.isFlagSet("goodShipMoved") && (PlayLevelScreen.getMap().getNPCById(999).getLocation().y == 1680.0) && !PlayLevelScreen.flagManager.isFlagSet("badShipUltimatum"));
                            }
                        });

                        addScriptAction(new TextboxScriptAction(){{
                            addText("I wish we could just talk this out properly.");
                            addText("As is, I'm staying just out of cannon range.");
                            addText("To think there's two of me ship, and one of em's a bastard...");
                        }});
                    }});

                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                return (PlayLevelScreen.flagManager.isFlagSet("badShipUltimatum") && !PlayLevelScreen.flagManager.isFlagSet("goodShipPloy"));
                            }
                        });

                        addScriptAction(new TextboxScriptAction(){{
                            addText("I overhead your conversation.");
                            addText("This flag is me flag. Peace between us may be impossible.");
                            addText("That being said, tell him I agree.");
                            addText("May be me only chance to settle this...");
                            addText("Peacefully or otherwise.");
                        }});

                        addScriptAction(new ChangeFlagScriptAction("goodShipPloy", true));
                    }});

                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                return (PlayLevelScreen.flagManager.isFlagSet("goodShipPloy") && (PlayLevelScreen.getMap().getNPCById(999).getLocation().y == 1488.0) && !PlayLevelScreen.flagManager.isFlagSet("shipDiscussion"));
                            }
                        });

                        addScriptAction(new TextboxScriptAction(){{
                            addText("We managed to talk it all out, believe it or not.");
                            addText("Big misunderstandin. Yer not gonna believe what happened.");
                            addText("...");
                            addText("These really do be strange seas indeed.");
                        }});
                    }});

                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                System.out.println("DEBUG2: " + PlayLevelScreen.getMap().getNPCById(666).getLocation().y);
                                return (PlayLevelScreen.flagManager.isFlagSet("shipDiscussion") && (!PlayLevelScreen.getMap().getNPCById(666).exists()));
                            }
                        });

                        addScriptAction(new TextboxScriptAction(){{
                            addText("Guess he's gone.");
                            addText("Still don't know what teh think.");
                            addText("The ghosts of bastards are still bastards, ay?");
                            addText("Though its still a fine ship.");
                        }});
                    }});

                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                return !PlayLevelScreen.getMap().getFlagManager().isFlagSet("goodShipInformed");
                            }
                        });

                        addScriptAction(new TextboxScriptAction() {{
                            addText("Tell me, 'ave you seen...");
                        }});

                        scriptActions.add(new ConditionalScriptAction() {{
                            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                                addRequirement(new CustomRequirement() {
                                    @Override
                                    public boolean isRequirementMet() {
                                        return !PlayLevelScreen.getMap().getFlagManager().isFlagSet("badShipEncountered");
                                    }
                                });
        
                                addScriptAction(new TextboxScriptAction() {{
                                    addText("Ack, never ye mind");
                                }});
                            }});
        
                            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                                addRequirement(new CustomRequirement() {
                                    @Override
                                    public boolean isRequirementMet() {
                                        return PlayLevelScreen.getMap().getFlagManager().isFlagSet("badShipEncountered");
                                    }
                                });
        
                                addScriptAction(new TextboxScriptAction() {{
                                    addText("...");
                                    addText("'ve heard tales of another ship resembling mine");
                                    addText("Yeh know anything about that?", new String[] { "Tell him about the other ship.", "Say you haven't seen it."});
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
                                            addText("So it be true.");
                                            addText("Must be them from that mutiny during the storm...");
                                            addText("Me boat was split clean in half!");
                                            addText("Had to repair our side just to stay afloat as we drifted away.");
                                            addText("Twould seem that the other side survived as well.");
                                            addText("Strange seas these be indeed.");
                                        }});
        
                                        addScriptAction(new ChangeFlagScriptAction("goodShipInformed", true));
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
                                            addText("Aye.");
                                            addText("Let me know if you do.");
                                            addText("Would love to put to rest a sinking suspicion of mine...");
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
                        return !PlayLevelScreen.getMap().getFlagManager().isFlagSet("goodShipEncountered");
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("Ahoy lad! It's good to see another ship!");
                    addText("I mean, ye are another ship, aye?");
                    addText("Who am I kidding, of course ye are... heheh");
                    addText("If yer another ship then ye must be another ship...");
                }});

                addScriptAction(new ChangeFlagScriptAction("goodShipEncountered", true));
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
