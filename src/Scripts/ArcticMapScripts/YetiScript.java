package Scripts.ArcticMapScripts;

import Level.Script;
import Level.ScriptState;
import Screens.PlayLevelScreen;
import ScriptActions.*;
import Utils.Direction;

import java.util.ArrayList;

public class YetiScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction(){{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("yetiBeaten", true));
                
                addScriptAction(new TextboxScriptAction() {{
                    addText("Damn dude you're pretty tough");
                    addText("Thats actually kind of respectable");
                    addText("Here, take some upgrades for your ship, they're left behind by a sailor a while back.");
                }});
                
                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        player.setMaxHealth(player.getMaxHealth()+10);
                        player.setStrength(player.getStrength() + 3);
                        PlayLevelScreen.upgrade();
                        
                        return ScriptState.COMPLETED;
                    }
                });

                scriptActions.add(new ChangeFlagScriptAction("yetiBeaten", false));
            }});
        }});
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("yetiBeaten", false));

                // scriptActions.add(new ConditionalScriptAction() {{
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new FlagRequirement("talkedToYeti", false));
                        addScriptAction(new WaitScriptAction(70));
                        addScriptAction(new TextboxScriptAction() {{
                            addText("...", new String[] {"Ahoy?"});
                        }});
                        
                        addScriptAction(new WaitScriptAction(70));
                        addScriptAction(new NPCStandScriptAction(Direction.RIGHT));
                        addScriptAction(new WaitScriptAction(20));
                        addScriptAction(new NPCFacePlayerScriptAction());
                        
                        addScriptAction(new TextboxScriptAction() {{
                            addText("...oh. You're talking to me?", new String[] {"Sure don't see anyone else 'round us"});
                            addText("damn okay... jeez dude you don't gotta give me attitude like that.", new String[] {"What're ye raving about? My attitude is mighty peachy"});
                            addText("Speak for yourself dude...");
                        }});
        
                        addScriptAction(new ChangeFlagScriptAction("talkedToYeti", true));
                    }});
                    
                // }});
                
                scriptActions.add(new ConditionalScriptAction() {{
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new FlagRequirement("talkedToYeti", true));
                        addScriptAction(new TextboxScriptAction() {{
                            addText("So what do you want with me?", new String[] {"Ye glimpse any magical beasts guarding a barrier around here?", "I heard ye is strong, let us fight!"});
            
                        }});
                    }});
        
                }});
        
                scriptActions.add(new ConditionalScriptAction(){{
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new FlagRequirement("talkedToYeti", true));
                        addRequirement(new CustomRequirement() {
                            @Override
                                    public boolean isRequirementMet() {
                                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                        return answer == 0;
                                    }
                        });
        
                        addScriptAction(new TextboxScriptAction() {{
                            addText("Hmm, I don't know about any magical beasts, but Krampus lives right down the corner");
                            addText("I remember they said something about maintaining a barrier, that would be \nmy best bet");
                            addText("They're a sweet old creature, surely you'll convince them");
                        }});
                    }});
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new FlagRequirement("talkedToYeti", true));
                        addRequirement(new CustomRequirement() {
                            @Override
                                    public boolean isRequirementMet() {
                                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                        return answer == 1;
                                    }
                        });
        
                        addScriptAction(new TextboxScriptAction() {{
                            addText("Damn dude, we just met each other and you want to fight?");
                            addText("I mean, thats pretty messed up if you ask me...");
                            addText("Whatever, I'll fight you");
                        }});
        
                        addScriptAction(new ChangeFlagScriptAction("yetiEnemy", true));
                        addScriptAction(new ChangeFlagScriptAction("combatTriggered", true));
                    }});
                }});
            }});
        }});
    
        
        
        scriptActions.add(new UnlockPlayerScriptAction());
        
        return scriptActions;
    }
}
