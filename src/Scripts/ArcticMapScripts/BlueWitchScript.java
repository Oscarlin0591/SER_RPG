package Scripts.ArcticMapScripts;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

public class BlueWitchScript extends Script{

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCLockScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("krampusQuest", true));

                addScriptAction(new TextboxScriptAction() {{
                    addText("H-Hi!", new String[] {"Can ye make me a magic wand?"});
                    addText("What?", new String[]{"A wand, I'm asking if ye'd be so kind to make one"});
                    addText("Did Krampus send you?", new String[]{"Sure did, they also told me that they want to be cordial with ye.\nAsked me to give you this tome as a gift."});
                    addText("Oh wow...");
                    addText("That's relieving to hear...");
                    addText("Krampus looked kind of scary so I must say I was\nintimidated to talk to them at first...");
                    addText("It seems they are quite kind");
                    addText("I'll go over and say hi another time");
                    addText("What do you need from me?", new String[] {"A magic wand, please"});
                    addText("Hmm okay I should have the materials for one", new String[]{"Great! When will it be done."});
                    addText("In just a minute, but...", new String[]{"But?"});
                    addText("There is a condition for me giving you the wand.", new String[]{"What is it?"});
                    addText("Umm.... (oh god this is so embarrassing) so... (screaming)");
                    addText("Go on a date with me", new String[]{"Que?"});
                    addText("I'm asking you to go on a date with me", new String[]{"Why young lass, what is this haste for romance?"});
                    addText("It's been 50 years since I last interacted with a man, I've forgotten what romance is like\nI'll give you the wand if you agree.", new String[]{"50 years...? Wait how old are you?"});
                }});

                addScriptAction(new ChangeFlagScriptAction("witchSpokenTo", true));
            }});
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("krampusQuest", true));
                addRequirement(new FlagRequirement("witchSpokenTo", true));

                addScriptAction(new TextboxScriptAction() {{
                    addText("So is it a yes or a no?", new String[] {"Uhh... sure", "Give me time to think, lass."});
                }});

                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new CustomRequirement() {
                        public boolean isRequirementMet() {
                            int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                            return answer == 0;
                        }
                    });

                    addScriptAction(new TextboxScriptAction(){{
                        addText("Great come with me.");
                    }});
                    addScriptAction(new ChangeFlagScriptAction("dateTriggered", true));
                }});
            }});
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
            addRequirement(new FlagRequirement("krampusQuest", false));

            addScriptAction(new TextboxScriptAction() {{
                addText("Ummmm... hi?", new String[] {"Ahoy!"});
                addText("Oh...", new String[]{"Everything alright lass?"});
                addText("Um... nothing");
                addText("*She seems too timid to hold a conversation at the moment*");
                addText("*Perhaps she'll open up if you ask something magic related*");
            }});

            }});

        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
    
}
