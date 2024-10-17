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

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }


    // // scriptActions.add(new TextboxScriptAction() {{
    // //     addText("Hello!");
    // //     addText("Do you like bugs?", new String[] { "Yes", "No" });
    // // }});


    // }
}