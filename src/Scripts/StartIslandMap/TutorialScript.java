package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

public class TutorialScript extends Script{

    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Ahoy Matey!", new String[] { "Hi?", "Ahoy!" });
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
                    addText("That aint right!");
                    addText("Ye clearly don't know yer hook from yer peg leg, do ya? Let's try this again.");
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
                    addText("My two intact eyes mistake me! Ye be a bonafide pirate after all!");
                    addText("Guess I don't need to tell ye much of anything at all then.");
                }});

                addScriptAction(new TextboxScriptAction() {{
                    addText("Do I?", new String[] { "Yarg!", "Narg?" });
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
                            addText("Lets get to it then!");
                            addText("To move around, ye can use wasd. Feel free to try it out.");
                            addText("Not right now though I'm talking. Ye can also use e to interact, though ye may have figured that one out already.");
                            addText("Interacting with certain objects is how ye can sail the seas, or board an island. It's even how ye can enter combat!");
                        }});

                        addScriptAction(new TextboxScriptAction() {{
                            addText("Wanna give combat a try?", new String[] { "Yarg!", "Hell narg." });
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
                            addText("Get sailin then!");
                        }});
                    }});
                }});
            }});
        }});
    }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return (answer == 0|| answer ==1);
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("Ye looking like yer hungry for booty!",new String[] { "I'm looking for the Nave d'Oro... You reckon tellin me 'bout it?", "Buzz off you coxswain!" });
                }});
            }});
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
                    addText("Of course I do! Not a single tar, lad, or seadog hasn’t heard of the fabled Nave d’Oro", new String[] { "Reckon you can tell me more?" });
                    addText("Well it was said that the Nave d’Oro is hidden in the heart of the seven seas!\nSealed in a magical barrier.");
                    addText("Only a courageous sea dog who’s sailed around the globe and slain the avatars of\nsafekeeping could that barrier fall.");
                    addText("I ne’er seen anyone return. No matter if they sailed in a raft or a man o’ war.");
                    addText("Perhaps you’re the exception, may Neptune’s blessings be upon ye.");
                }});
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}