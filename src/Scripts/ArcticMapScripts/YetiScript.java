package Scripts.ArcticMapScripts;

import Level.Script;
import ScriptActions.*;
import Utils.Direction;

import java.util.ArrayList;

public class YetiScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
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
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("talkedToYeti", true));
                addScriptAction(new TextboxScriptAction() {{
                    addText("So what do you want with me?", new String[] {"Ye glimpse any magical beasts guarding a barrier around here?", ""});

                }});
            }});
        }});
            

        scriptActions.add(new TextboxScriptAction() {{
            // addText("You're totally new around here, arentcha?", new String[]{"Yeah"});
            // addText("Well let ye be known that my name is Pete!\nPete the Spider!");
            // addText("It may not look like it but I'm gonna be a big shot once I venture out these caves!");
            // addText("What will I do then? I'm gonna be a web developer, of course!");
            // addText("Speaking of which, I'm lauching my new crypto: the WebToken!\n You should totally invest its gonna be the currency of the future!");
            // addText("Way better than the heavy gold coins and bars...");
            // addText("What? Fourth-wall? I don't know what you're talking about...");
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
