package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import Level.ScriptState;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.NPCChangeVisibilityScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import Utils.Visibility;

public class MeatScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("You gained 1 strength!");
        }});

        scriptActions.add(new ScriptAction() {
            @Override
            public ScriptState execute() {
                player.setStrength(player.getStrength()+1);
                System.out.println(player.getHealth());
                return ScriptState.COMPLETED;
            }
        });

        scriptActions.add(new NPCChangeVisibilityScriptAction(Visibility.HIDDEN));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}