package Scripts.OceanMapScripts;

import java.util.ArrayList;

import Level.Script;
import Screens.PlayLevelScreen;
import ScriptActions.*;
import Utils.Visibility;

public class PirateScript1 extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Yaarrrrgh! Who goes there?", new String[] {"Just a wanderin' ship, let me pass will ye?"});
            addText("Oh ho! A \"wanderin' ship\" ye say? And why shouldn't the crew plunder ye ship asunder?", new String[] {"Because this vessel ain't got nothin valuable."});
            addText("Ye call yerself a buccaneer and got \"nothin valuable\"? Nice try partner, but this old salt knows when a vessels got riches");
            addText("Lay down yer coffers, and I might just let you go.", new String[]{"You hornswaggle! I'm not leaving anythin!"});
            addText("So be it! Blow the man down!");
        }});

        
        scriptActions.add(new UnlockPlayerScriptAction());
        //toggle combat flag
        // scriptActions.add(new ChangeFlagScriptAction("pirateEnemy", true));
        // scriptActions.add(new ChangeFlagScriptAction("combatTriggered", true));

        return scriptActions;
    }
}