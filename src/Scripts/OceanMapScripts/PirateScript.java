package Scripts.OceanMapScripts;

import java.util.ArrayList;

import Level.Script;
import Screens.PlayLevelScreen;
import ScriptActions.*;
import Utils.Visibility;

public class PirateScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction(){{
            addConditionalScriptActionGroup (new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("pirateBeaten", false));

                addScriptAction(new TextboxScriptAction() {{
                    addText("Yaarrrrgh! Who goes there?", new String[] {"Just a wanderin' ship, let me pass will ye?"});
                    addText("Oh ho! A \"wanderin' ship\" ye say? And why shouldn't the crew plunder ye ship asunder?", new String[] {"Because this vessel ain't got nothin valuable."});
                    addText("Ye call yerself a buccaneer and got \"nothin valuable\"?");
                    addText("Nice try partner, but this old salt knows when a vessels got riches.");
                    addText("Lay down yer coffers, and I might just let you go.", new String[]{"You hornswaggle! I'm not leaving anythin!"});
                    addText("So be it! Blow the man down!");
                }});

                addScriptAction(new UnlockPlayerScriptAction());

                //toggle combat flag
                addScriptAction(new ChangeFlagScriptAction("pirateEnemy", true));
                addScriptAction(new ChangeFlagScriptAction("combatTriggered", true));
            }});

            addConditionalScriptActionGroup (new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("pirateBeaten", true));

                addScriptAction(new TextboxScriptAction(){{
                    addText("...");
                    addText("So this is awkward.");
                    addText("I'm honestly surprised yeh didn't sink me ship.", new String[] {"Couldn't exactly sink the riches on board, could I? Hand em over.", "I'm a pacifist. Now give me your money."});
                    addText("Yargh. So be it.");
                    addText("Guess I'm now a penniless pirate. Better get lootin!");
                }});
            }});
        }});
        
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}