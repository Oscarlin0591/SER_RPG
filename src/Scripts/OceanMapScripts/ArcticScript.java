package Scripts.OceanMapScripts;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;


// trigger script at beginning of game to set that heavy emotional plot
// checkout the documentation website for a detailed guide on how this script works
public class ArcticScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ChangeFlagScriptAction("toggleArctic", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}

