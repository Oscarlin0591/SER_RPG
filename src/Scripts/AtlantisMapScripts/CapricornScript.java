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
                    addText("they go all mixed up at somepoint an' I can't really seem to fix it \nThe waves tend to mess up the clarity of the night sky");
                    addText("Now truth be told I figured all but 5 of them out \nThe ones still mized up are: \nAries, Taurus, Leo, Cancer, and Capricorn", new String[]{"Aye I can", "No prepare to die"});
                }});

                //Screw this fight me response
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
    
    
                    addScriptAction(new ChangeFlagScriptAction("capricornEnemy", true));
                    addScriptAction(new ChangeFlagScriptAction("combatTriggered", true));
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
                    scriptActions.add(new ChangeFlagScriptAction("capricornGameTriggered", true));    
                }});
                    
                //no prepare to die reponse 
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
    
    
                    addScriptAction(new ChangeFlagScriptAction("capricornEnemy", true));
                    addScriptAction(new ChangeFlagScriptAction("combatTriggered", true));
                }});
            
     
            }});
                }});// end of scriptActions.add(new ConditionalScriptAction()
            
            }});// end of addConditionalScriptActionGroup(new ConditionalScriptActionGroup()
        
        }}); //end of ArrayList<ScriptAction> loadScriptActions()
      
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}