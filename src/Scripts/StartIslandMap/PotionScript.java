package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.Script;
import Level.ScriptState;
import Maps.StartIslandMap;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.NPCChangeVisibilityScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import Utils.Visibility;
import Level.MapEntityStatus;
import Screens.PlayLevelScreen;

public class PotionScript extends Script {
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
                player.setHealth(player.getHealth()+5);
                System.out.println(player.getHealth());

                //remove potion once used
                if (map.getNPCById(7) != null) {
                    PlayLevelScreen.flagManager.setFlag("startIslandPotion");
                } else if (map.getNPCById(77) != null) {
                    PlayLevelScreen.flagManager.setFlag("oceanPotion");
                }

                return ScriptState.COMPLETED;
            }
        });

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}