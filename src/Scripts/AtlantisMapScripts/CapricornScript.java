package Scripts.AtlantisMapScripts;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.CustomRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class CapricornScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Who be this here in fore o'me, a human?");
            addText("What bring ye down 'ere to atlantis \n I apologize but it be not what it once been", new String[] {"I need ye to remove the barrier"});
            addText("Ah the barrier ye say, I could do suc a thing", new String[] {"Ye could?", "screw this fight me"});
        }});
            //ye could reponse 
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
                    addText("Yes I can");
                    addText("Could I ask as to why ye want to get rid of the barrier?", new String[] {"I want to get the Nave d'Oro"});
                    addText("The Nave d'Oro, eh?");
                    addText("Intriging");
                    addText("Well ye would have to do something for me though \n all I need ye to do is 'elp me get my pic tures of the stars \nback into order");
                    addText("they go all mixed up at somepoint an' I can't really seem to fix it \nThe waves tend to mess up the clarity of the night sky", new String[]{"Aye I can", "No prepare to die"});
                }});
            }});
            //aye I can reponse 
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
                    addText("Ah perfect thank you so much");
                    addText("I'll show them to you and you give me their name");
                    addText("Now truth be told I figured all but 5 of them out \nThe ones still mized up are: \nAries, Taurus, Leo, Cancer, and Capricorn");
                    
                }});

                addScriptAction(new UnlockPlayerScriptAction());               
            }});

            //nay fight response 
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1;
                    }
                });
                
                addScriptAction(new TextboxScriptAction() {{
                    addText("Alright then, be prepared");
                    addText("I won't go down easily...");
                }});
                
                addScriptAction(new UnlockPlayerScriptAction());

              //  addScriptAction(new ChangeFlagScriptAction("beetleEnemy", true));
               // addScriptAction(new ChangeFlagScriptAction("combatTriggered", true));
            
        }});
               // addScriptAction(new UnlockPlayerScriptAction());               
            }});

            //screw this fight me reponse 
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1;
                    }
                });
                
                addScriptAction(new TextboxScriptAction() {{
                    addText("Alright then, be prepared");
                    addText("I won't go down easily...");
                }});
                
                addScriptAction(new UnlockPlayerScriptAction());

              //  addScriptAction(new ChangeFlagScriptAction("beetleEnemy", true));
               // addScriptAction(new ChangeFlagScriptAction("combatTriggered", true));
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
