package Scripts.StartIslandMap;

import Level.*;
import Maps.BattleMap;
import NPCs.*;
import NPCs.Bosses.*;
import Screens.PlayLevelScreen;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.CustomRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import Scripts.StartIslandMap.*;
import Tilesets.BattleMapTileset;
import Tilesets.RPGTileset;
import Utils.Direction;

import java.util.ArrayList;
import java.util.Random;

public class DateScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.loveLevel >= PlayLevelScreen.loveGoal);
                    }
                });

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        PlayLevelScreen.getMap().getFlagManager().setFlag("dateWonText");
                            return ScriptState.COMPLETED;
                        }
                    });
                }});
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                            return PlayLevelScreen.getMap().getFlagManager().isFlagSet("dateWonText");
                        }
                    });

                addScriptAction(new TextboxScriptAction(){{
                    addText("Your date went super well! Congratulations!");
                }});

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        PlayLevelScreen.isDating = false;
                            return ScriptState.COMPLETED;
                        }
                });
                addScriptAction(new ChangeFlagScriptAction("dateWon", true));
            }});
        }});
        scriptActions.add(new TextboxScriptAction() {{
            addText("Testing", new String[]{"Date"});
        }});

        scriptActions.add(new ScriptAction() {
            @Override
            public ScriptState execute() {
                PlayLevelScreen.flagManager.setFlag("datePanel");
                    return ScriptState.COMPLETED;
                }
        });

        scriptActions.add(new TextboxScriptAction() {{
            addText("You used your premium, tropical charisma at your partner");
            addText("They feel a little charmed");
        }});

        scriptActions.add(new ScriptAction() {
            @Override
            public ScriptState execute() {
                PlayLevelScreen.loveLevel += PlayLevelScreen.dateScreen.returnMultiplier();
                    return ScriptState.COMPLETED;
                }
        });

        scriptActions.add(new UnlockPlayerScriptAction());


        return scriptActions;
    }
    
}
