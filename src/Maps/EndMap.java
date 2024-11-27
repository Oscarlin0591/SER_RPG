package Maps;

import NPCs.*;
import NPCs.Bosses.*;
import NPCs.Decoration.*;
import Level.*;
import NPCs.Interactable.*;
import ScriptActions.*;
import Scripts.EndMapScripts.ShrineScript;
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

        SkullTorch torch1 = new SkullTorch(981, getMapTile(4,12).getLocation());
        torch1.setInteractScript(new Script() {
            
            @Override
            public ArrayList<ScriptAction> loadScriptActions() {
                ArrayList<ScriptAction> scriptActions = new ArrayList<>();

                scriptActions.add(new NPCStandScriptAction(Direction.RIGHT));
                scriptActions.add(new LockPlayerScriptAction());
                scriptActions.add(new ScriptAction() {
                   @Override
                   public ScriptState execute() {
                    torch1.lightTorch("torch1");
                    return ScriptState.COMPLETED;
                }
                });

                scriptActions.add(new TextboxScriptAction() {{
                    addText("As the unnatural flames dance in front of you. You see a memory of a distant past");
                }});

                scriptActions.add(new TextboxScriptAction() {{
                    addText("The King, hungry for gold, was in search for distant lands.");
                    addText("He wanted to trade for their insignificant goods so he can grow his kingdom's reserves.");
                    addText("There was kindess in his heart yet. Until the incident.");
                }});

                scriptActions.add(new UnlockPlayerScriptAction());
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
                scriptActions.add(new LockPlayerScriptAction());
                scriptActions.add(new ScriptAction() {
                   @Override
                   public ScriptState execute() {
                    torch2.lightTorch("torch2");
                    return ScriptState.COMPLETED;
                }
                });

                scriptActions.add(new TextboxScriptAction() {{
                    addText("As the unnatural flames dance in front of you. You see a memory of a distant past");
                }});

                scriptActions.add(new TextboxScriptAction() {{
                    addText("His right hand man, covetus of the King's powers, conspired a plot with the natives on\n one of the islands they are visiting.");
                    addText("On the fateful night, they took out the guards posted in from the King's chambers\nand apprached the King's bed.");
                    addText("The King, however, was a cautious man, when the traitors approached his bed, one\nstepped on a pressure plate, sounding an alarm above his bed.");
                    addText("The King awoke immediately, but his right hand man did not hesitate and brought down\nthe axe that he had with him.");
                    addText("The King tried to parry, but poetically, his right hand was severed. The wounded king\ntook off the protective gauntlet he wore and turned all of his dissidents into gold.");
                    addText("The immediate danger was quelled, but the King was not the same after the attempt on his life.");
                }});
                scriptActions.add(new UnlockPlayerScriptAction());
                return scriptActions;
            }
        });
        npcs.add(torch2);

        SkullTorch torch3 = new SkullTorch(983, getMapTile(51,28).getLocation());
        torch3.setLocation(torch3.getX()+16, torch3.getY());
        torch3.setInteractScript(new Script() {
            
            @Override
            public ArrayList<ScriptAction> loadScriptActions() {
                ArrayList<ScriptAction> scriptActions = new ArrayList<>();

                scriptActions.add(new NPCStandScriptAction(Direction.RIGHT));
                scriptActions.add(new LockPlayerScriptAction());
                scriptActions.add(new ScriptAction() {
                   @Override
                   public ScriptState execute() {
                    torch3.lightTorch("torch3");
                    return ScriptState.COMPLETED;
                }
                });
                scriptActions.add(new UnlockPlayerScriptAction());
                return scriptActions;
            }
        });
        npcs.add(torch3);

        SkullTorch torch4 = new SkullTorch(983, getMapTile(58,12).getLocation());
        torch4.setInteractScript(new Script() {
            
            @Override
            public ArrayList<ScriptAction> loadScriptActions() {
                ArrayList<ScriptAction> scriptActions = new ArrayList<>();

                scriptActions.add(new NPCStandScriptAction(Direction.RIGHT));
                scriptActions.add(new LockPlayerScriptAction());
                scriptActions.add(new ScriptAction() {
                   @Override
                   public ScriptState execute() {
                    torch3.lightTorch("torch4");
                    return ScriptState.COMPLETED;
                }
                });
                scriptActions.add(new UnlockPlayerScriptAction());
                return scriptActions;
            }
        });
        npcs.add(torch4);

        DeadTree1 deadTree1 = new DeadTree1(984, getMapTile(3, 32).getLocation());
        npcs.add(deadTree1);

        DeadTree2 deadTree2 = new DeadTree2(984, getMapTile(14, 15).getLocation());
        deadTree2.stand(Direction.LEFT);
        npcs.add(deadTree2);

        LichSkeleton bigSkeleton = new LichSkeleton(985, getMapTile(38, 2).getLocation());
        npcs.add(bigSkeleton);

        Rib1 rib1 = new Rib1(986,getMapTile(40,10).getLocation());
        npcs.add(rib1);

        Rib1 rib2 = new Rib1(986, getMapTile(46, 10).getLocation());
        rib2.stand(Direction.LEFT);
        npcs.add(rib2);

        BoneTree boneTree = new BoneTree(987, getMapTile(56,14).getLocation());
        boneTree.stand(Direction.LEFT);
        npcs.add(boneTree);

        Ruins1 ruins1 = new Ruins1(988, getMapTile(18,26).getLocation());
        npcs.add(ruins1);

        BossShrine shrine = new BossShrine(990, getMapTile(39, 35).getLocation());
        shrine.setInteractScript(new ShrineScript());
        npcs.add(shrine);


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
        Music.playMusic("Music/creepy.wav");
    }
    
}
