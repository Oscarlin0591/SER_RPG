package Scripts.ShipwreckScripts;

import java.util.ArrayList;

import Level.Script;
import Level.ScriptState;
import Maps.BattleMap;
import Screens.PlayLevelScreen;
import ScriptActions.*;

public class GhostPirateScript extends Script{

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction(){{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (!PlayLevelScreen.flagManager.isFlagSet("boo"));
                    }
                });
                    
                addScriptAction(new TextboxScriptAction() {{
                    addText("BOO!");
                    addText("...");
                    addText("Sorry laddy, I'm legally obligated to do that.");
                }});

                addScriptAction(new ChangeFlagScriptAction("boo", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.flagManager.isFlagSet("boo") && !PlayLevelScreen.flagManager.isFlagSet("krakenKilled"));
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("That damned beast picked me ship out of the water and get this!");
                    addText("It placed it gently down on this 'island' if ye can call it that.");
                    addText("Oh I starved to death if ye wanted to know.");
                }});
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.flagManager.isFlagSet("krakenKilled") && !PlayLevelScreen.flagManager.isFlagSet("finishedBusiness?"));
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("So you've finally done it, eh?");
                    addText("Serves that damned Kraken right.");
                    addText("It must be time for this old soul to pass on to the next.");
                    addText("What with that unfinished business of mine being finished.");
                    addText("Goodbye laddy...");
                }});

                addScriptAction(new ChangeFlagScriptAction("finishedBusiness?", true));

                addScriptAction(new ScriptAction() {
                        @Override
                        public ScriptState execute() {
                            PlayLevelScreen.getMap().getNPCById(10).setIsHidden(true);
                            return ScriptState.COMPLETED;
                        }
                    });
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.flagManager.isFlagSet("finishedBusiness?") && !(PlayLevelScreen.flagManager.isFlagSet("treePocketed") || PlayLevelScreen.flagManager.isFlagSet("atlantisTreeBroken")));
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("...");
                    addText("So its funny.");
                    addText("I tried to pass onto the next, and for a moment it even felt like I had.");
                    addText("But... I still felt compelled to stay here for something else...");
                    addText("Something to do with the lost city of Atlantis?");
                    addText("Me livin memories are all so damned fuzzy...");
                }});
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.flagManager.isFlagSet("treePocketed") || PlayLevelScreen.flagManager.isFlagSet("atlantisTreeBroken")) && !(PlayLevelScreen.flagManager.isFlagSet("treeReplanted"));
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("Hold up.");
                    addText("Something's different this time.");
                    addText("Ye did something when ye visited Atlantis, didn't ye?");
                    addText("I've got the strangest feeling like...");
                    addText("Like someone walkin over me tombstone, or to that effect.");
                    addText("Yer lucky I happen to like ye laddy, heh heh.");
                }});
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.flagManager.isFlagSet("treeReplanted") && !PlayLevelScreen.flagManager.isFlagSet("sonReveal"));
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("I'm getting a feeling like you've really outdone yerself this time.");
                    addText("I think ye may have done something special for someone I used teh know.", new String [] {"You mean replanting the farmer's tree? How do you know about that?"});
                    addText("Yeah, me ol pops...");
                    addText("...");
                    addText("Oh me god. I'm rememberin... He's me father!");
                    addText("I died just before I could ever tell him that I reached Atlantis!\nPlanted the ol family tree there and everythin...");
                    addText("I hope he don't miss me too much... I wonder how long it's even been...");
                    addText("...");
                }});

                addScriptAction(new ChangeFlagScriptAction("sonReveal", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.flagManager.isFlagSet("sonReveal") && !PlayLevelScreen.flagManager.isFlagSet("appleGiven"));
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("I miss me pops.");
                    addText("If only me soul weren't bound to this damned island...");
                }});
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.flagManager.isFlagSet("appleGiven"));
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("...", new String[] {"I brought you something... It's from your father."});
                    addText("An apple from the family tree, eh? Looks delicious.");
                    addText("Wish I could actually, yknow, eat it. Very sweet of him though!");
                    addText("Hey wait a second... Couldn't I just...");
                }});

                addScriptAction(new ChangeFlagScriptAction("appleHaunted", true));
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
    
}
