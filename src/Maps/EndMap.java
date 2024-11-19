package Maps;

import NPCs.*;
import NPCs.Bosses.*;
import NPCs.Decoration.*;
import Level.*;
import NPCs.Interactable.*;
import ScriptActions.*;
// import Scripts.EndMapScripts.*;
import Tilesets.EndTileset;
import Utils.Direction;

import java.util.ArrayList;

public class EndMap extends Map{

    public EndMap() {
        super("end_map.txt", new EndTileset());
        this.playerStartPosition = getMapTile(11, 1).getLocation();
    }
    // @Override
    // public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    //     ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

    //     return enhancedMapTiles;
    // }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        SkullTorch torch1 = new SkullTorch(981, getMapTile(58,2).getLocation());
        torch1.setInteractScript(new Script() {
            
            @Override
            public ArrayList<ScriptAction> loadScriptActions() {
                ArrayList<ScriptAction> scriptActions = new ArrayList<>();

                scriptActions.add(new NPCStandScriptAction(Direction.RIGHT));
                scriptActions.add(new ScriptAction() {
                   @Override
                   public ScriptState execute() {
                    torch1.lightTorch();
                    return ScriptState.COMPLETED;
                }
                });
                return scriptActions;
            }
        });
        npcs.add(torch1);

        SkullTorch torch2 = new SkullTorch(982, getMapTile(5,40).getLocation());
        torch2.setInteractScript(new Script() {
            
            @Override
            public ArrayList<ScriptAction> loadScriptActions() {
                ArrayList<ScriptAction> scriptActions = new ArrayList<>();

                scriptActions.add(new NPCStandScriptAction(Direction.RIGHT));
                scriptActions.add(new ScriptAction() {
                   @Override
                   public ScriptState execute() {
                    torch2.lightTorch();
                    return ScriptState.COMPLETED;
                }
                });
                return scriptActions;
            }
        });
        npcs.add(torch2);

        SkullTorch torch3 = new SkullTorch(983, getMapTile(27,20).getLocation());
        torch3.setLocation(torch3.getX()+16, torch3.getY());
        torch3.setInteractScript(new Script() {
            
            @Override
            public ArrayList<ScriptAction> loadScriptActions() {
                ArrayList<ScriptAction> scriptActions = new ArrayList<>();

                scriptActions.add(new NPCStandScriptAction(Direction.RIGHT));
                scriptActions.add(new ScriptAction() {
                   @Override
                   public ScriptState execute() {
                    torch3.lightTorch();
                    return ScriptState.COMPLETED;
                }
                });
                return scriptActions;
            }
        });
        npcs.add(torch3);

        DeadTree1 deadTree1 = new DeadTree1(984, getMapTile(3, 32).getLocation());
        npcs.add(deadTree1);

        DeadTree2 deadTree2 = new DeadTree2(984, getMapTile(14, 15).getLocation());
        deadTree2.stand(Direction.LEFT);
        npcs.add(deadTree2);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(getMapTile(10, 0).getLocation().x, getMapTile(10, 0).getLocation().y+30, 100, 15, new Script() {
            @Override
            public ArrayList<ScriptAction> loadScriptActions() {
                ArrayList<ScriptAction> scriptActions = new ArrayList<>();
                scriptActions.add(new LockPlayerScriptAction());

                scriptActions.add(new TextboxScriptAction() {{
                    addText("Exit Island?", new String[] { "Yes", "No" });
                }});

                scriptActions.add(new ConditionalScriptAction() {{
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                return answer == 0;
                            }
                        });

                        addScriptAction(new ChangeFlagScriptAction("exitEndIsland", true));
                    }});

                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                return answer == 1;
                            }
                        });

                        addScriptAction(new ScriptAction() {
                            @Override
                            public ScriptState execute() {
                                getPlayer().setLocation(getPlayer().getX(), getPlayer().getY() + 10);
                                return ScriptState.COMPLETED;
                            }
                        });
                    }});

                }});
                scriptActions.add(new UnlockPlayerScriptAction());

                return scriptActions;
        }}));
        return triggers;
    }

    @Override
    public void loadScripts() {
        // getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        // getMapTile(37, 5).setInteractScript(new AtlantisHoleScript());
    }

    @Override
    public void loadMusic() {
        // Music.playMusic("Music/Sparkling_Rime16bit.wav");
    }
    
}
