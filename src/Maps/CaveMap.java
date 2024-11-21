package Maps;

import Level.*;
import NPCs.Bug;
// import NPCs.Island;
import NPCs.Bosses.HolyBeetle;
import NPCs.Interactable.Spider;
import Screens.PlayLevelScreen;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.CustomRequirement;
import ScriptActions.FlagRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import Scripts.SimpleTextScript;
import Scripts.CaveMapScripts.*;
import Tilesets.MasterTileset;
import Tilesets.CaveTileset;
import java.util.ArrayList;

public class CaveMap extends Map {

    
    public CaveMap() {
        super("cave_map.txt", new CaveTileset());
        this.playerStartPosition = getMapTile(17, 22).getLocation();
    }

    // @Override
    // public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    //     ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

    //     return enhancedMapTiles;
    // }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Bug bug = new Bug(4, getMapTile(7, 20).getLocation().subtractX(20));
        bug.setInteractScript(new BugScript());
        npcs.add(bug);

        HolyBeetle beetleBoss = new HolyBeetle(5, getMapTile(40,11).getLocation(), 50, 5, 1, 1);
        beetleBoss.setExistenceFlag("beetleKilled");
        beetleBoss.setInteractScript(new BossBeetleScript());
        npcs.add(beetleBoss);

        Spider spider = new Spider(6, getMapTile(30,10).getLocation());
        spider.setInteractScript(new SpiderScript());
        npcs.add(spider);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //add triggers below, commented out one is an example.

        triggers.add(new Trigger(790, 1130, 100, 10, new CaveExitScript()));
        return triggers;
    }

    @Override
    public void loadScripts() {

        getMapTile(37, 5).setInteractScript(new CaveHoleScript());
        getMapTile(8,4).setInteractScript(new Script() {
        @Override
        public ArrayList<ScriptAction> loadScriptActions() {
            ArrayList<ScriptAction> scriptActions = new ArrayList<>();
            scriptActions.add(new LockPlayerScriptAction());

            scriptActions.add(new ConditionalScriptAction() {{
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new FlagRequirement("chestOpened", false));
                    addScriptAction(new TextboxScriptAction() {{
                        addText("You find an old chest, theres no lock on it. Open?", new String[] {"Yes", "No"});
                    }});
                    
                }});
            }});

            scriptActions.add(new ConditionalScriptAction() {{
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new FlagRequirement("chestOpened", false));
                    addRequirement(new CustomRequirement() {
                        @Override
                        public boolean isRequirementMet() {
                            int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                            return answer == 0;
                        }
                    });
                    addScriptAction(new TextboxScriptAction() {{
                        addText("The chest creaks open, revealing some old rubbish.");
                        addText("But among the junk you find some ship armor");
                        addText("You gained 20 health!");
                    }});

                    addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        player.setMaxHealth(player.getMaxHealth()+20);
                        player.setStrength(player.getStrength()+1);
                        PlayLevelScreen.upgrade();
                        return ScriptState.COMPLETED;
                    }
                    });
                    addScriptAction(new ChangeFlagScriptAction("chestOpened", true));
                    
                }});

                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new CustomRequirement() {
                        @Override
                        public boolean isRequirementMet() {
                            int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                            return answer == 1;
                        }
                    });
                    addScriptAction(new TextboxScriptAction() {{
                        addText("You decided you'll come back for it another time");
                    }});
                    
                }});
            }});
            scriptActions.add(new ConditionalScriptAction() {{
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new FlagRequirement("chestOpened", true));

                    addScriptAction(new TextboxScriptAction() {{
                        addText("You already looted this chest");
                    }});
                }});
            }});
            scriptActions.add(new UnlockPlayerScriptAction());
    
            return scriptActions;
        }});
    }

    @Override
    public void loadMusic() {
        Music.playMusic("Music/Crystal Caves.wav");
    }
}

