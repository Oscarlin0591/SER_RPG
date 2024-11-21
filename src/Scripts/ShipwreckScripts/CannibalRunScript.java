package Scripts.ShipwreckScripts;

import java.util.ArrayList;

import Level.Script;

import ScriptActions.ChangeFlagScriptAction;

import ScriptActions.LockPlayerScriptAction;
import ScriptActions.NPCWalkScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import Utils.Direction;


public class CannibalRunScript extends Script{
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction("AAAAAHHHHHHHHHHH"));
        scriptActions.add(new NPCWalkScriptAction(36, Direction.UP, 1000, 10));
        scriptActions.add(new ChangeFlagScriptAction("doneWithCannibal", true));
        scriptActions.add(new ChangeFlagScriptAction("cannibalEnemy", true));
        scriptActions.add(new ChangeFlagScriptAction("cannibalGhost", false));
        scriptActions.add(new ChangeFlagScriptAction("combatTriggered", true));

        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}