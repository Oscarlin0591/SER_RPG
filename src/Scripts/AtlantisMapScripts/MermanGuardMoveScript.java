package Scripts.AtlantisMapScripts;

import java.util.ArrayList;

import Level.Script;
import Screens.PlayLevelScreen;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.CustomRequirement;
import ScriptActions.FlagRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.NPCWalkScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import Utils.Direction;

public class MermanGuardMoveScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("merman1", false));

                addScriptAction(new TextboxScriptAction("HALT!"));

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.getMap().getNPCById(13).getY() > PlayLevelScreen.getPlayer().getY());
                    }
                });
                addScriptAction(new NPCWalkScriptAction(13, Direction.UP, (PlayLevelScreen.getMap().getNPCById(13).getY()-PlayLevelScreen.getPlayer().getY()), 7));
            }});
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.getMap().getNPCById(13).getY() < PlayLevelScreen.getPlayer().getY());
                    }
                });
                addScriptAction(new NPCWalkScriptAction(13, Direction.DOWN, (PlayLevelScreen.getPlayer().getY()-(PlayLevelScreen.getMap().getNPCById(13).getY()-PlayLevelScreen.getMap().getNPCById(13).getHeight()-15)), 7));
            }});
        }});

        scriptActions.add(new ConditionalScriptAction(){{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.getMap().getNPCById(13).getX() < PlayLevelScreen.getPlayer().getX());
                    }
                });
                addScriptAction(new NPCWalkScriptAction(13, Direction.RIGHT, (PlayLevelScreen.getPlayer().getX()-PlayLevelScreen.getMap().getNPCById(13).getX()), 7));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return (PlayLevelScreen.getMap().getNPCById(13).getX() > PlayLevelScreen.getPlayer().getX());
                    }
                });
                addScriptAction(new NPCWalkScriptAction(13, Direction.LEFT, (PlayLevelScreen.getMap().getNPCById(13).getX()-PlayLevelScreen.getPlayer().getX()-50), 7));
            }});
        }});

                addScriptAction(new TextboxScriptAction() {{
                    addText("Who are you?");
                    addText("What business do you have in Atlantis?", new String[]{"I request audience with yer ruler."});
                    addText("Our ruler is busy right now, they must not be disturbed.", new String[] {"What can I do to meet them?"});
                    addText("Nothing. Us guards will not let anyone meet our ruler with just a simple request", new String[] {"I insist. I must talk to yer Ruler"});
                    addText("The you'll have to persuade me with an offer or defeat me by force");
                    addText("Come back to me when you've made your decision");
                }});
            }});
        }});

        scriptActions.add(new ChangeFlagScriptAction("guardScriptTriggered", true));
        scriptActions.add(new ChangeFlagScriptAction("merman1", true));

        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
