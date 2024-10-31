package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Player;
import Level.Script;
import Level.ScriptState;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class SuperPotionScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("health boost!");
        }});

        scriptActions.add(new ScriptAction() {
            @Override
            public ScriptState execute() {
                player.setMaxHealth(getMap().getPlayer().getMaxHealth() + 20);
                return ScriptState.COMPLETED;
            }
        });

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}