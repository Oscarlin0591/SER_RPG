package Scripts.CaveMapScripts;

import Level.Script;
import ScriptActions.*;

import java.util.ArrayList;

public class SpiderScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCLockScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Wasssssssuuuup bro!", new String[] {"What's up!"});
            addText("You're totally new around here, arentcha?", new String[]{"Yeah"});
            addText("Well let ye be known that my name is Pete!\nPete the Spider!");
            addText("It may not look like it but I'm gonna be a big shot once I venture out these caves!");
            addText("What will I do then? I'm gonna be a web developer, of course!");
            addText("Speaking of which, I'm lauching my new crypto: the WebToken!\n You should totally invest its gonna be the currency of the future!");
            addText("Way better than the heavy gold coins and bars...");
            addText("What? Fourth-wall? I don't know what you're talking about...");
        }});


        scriptActions.add(new NPCUnlockScriptAction());
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
