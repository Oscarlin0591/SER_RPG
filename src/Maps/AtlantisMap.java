package Maps;

import Level.*;
import NPCs.AppleTree;
import NPCs.BluePotion;
import NPCs.Bosses.Capricorn;
import NPCs.Interactable.*;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.CustomRequirement;
import ScriptActions.FlagRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.NPCFacePlayerScriptAction;
import ScriptActions.Requirement;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import Scripts.SimpleTextScript;
import Scripts.AtlantisMapScripts.*;
import Scripts.StartIslandMap.AppleTreeScript;
import Scripts.StartIslandMap.SuperPotionScript;
import Tilesets.AtlantisTileset;
import Utils.Direction;

import java.util.ArrayList;

import Engine.ImageLoader;

public class AtlantisMap extends Map {

    
    public AtlantisMap() {
        super("atlantis_map.txt", new AtlantisTileset());
        this.playerStartPosition = getMapTile(2, 6).getLocation();
    }

    // @Override
    // public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    //     ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

    //     return enhancedMapTiles;
    // }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        AppleTree tree = new AppleTree(17, getMapTile(45,45).getLocation());
        tree.setInteractScript(new AppleTreeScript());
        tree.setExistenceFlag("atlantisTreeBroken");
        npcs.add(tree);

        Capricorn capricorn = new Capricorn(18, getMapTile(49,11).getLocation(),50,5,2,2);
        capricorn.setInteractScript(new CapricornScript());
        npcs.add(capricorn);

        BluePotion bluePotion = new BluePotion(15,getMapTile(35,45).getLocation());
        bluePotion.setInteractScript(new SuperPotionScript());
        npcs.add(bluePotion);

        Mermaid mermaid1 = new Mermaid(11, getMapTile(5, 5).getLocation(), "CharacterPNGs/mermaid1.png", 31, 52, 15, 2, 1, 1);
        mermaid1.stand(Direction.RIGHT);
        mermaid1.setInteractScript(new MermaidScript());
        npcs.add(mermaid1);

        Mermaid mermaid2 = new Mermaid(12, getMapTile(6, 20).getLocation(), "CharacterPNGs/mermaid2.png", 31, 52, 15, 2, 1, 1);
        mermaid2.setInteractScript(new Script() {

            @Override
            public ArrayList<ScriptAction> loadScriptActions() {
                ArrayList<ScriptAction> scriptActions = new ArrayList<>();
            scriptActions.add(new LockPlayerScriptAction());
            scriptActions.add(new NPCFacePlayerScriptAction());

            
            scriptActions.add(new ConditionalScriptAction() {{
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new FlagRequirement("mermaid2", false));

                    addScriptAction(new TextboxScriptAction() {{
                    addText("Umm Hi?", new String[] {"Ahoy!"});
                    addText("Oh... A pirate...", new String[] {"Something wrong with a buccaneer like me?"});
                    addText("No its just we don't see many pirates around these areas.", new String[] {"Ye had others here before?"});
                    addText("Yeah there was another guy that came by a while back, planted a tree by the castle, too.\n Said it was an \"Apple Tree\", assuming thats the red stuff growing out of it, pretty tasty.", new String[]{"We got plenty of fruits like that on the surface"});
                    addText("That sounds fun, I would definitely love exploring the surface if I can breath up there.", new String[] {"Ye don't got lungs?"});
                    addText("WOAH how dare you assume everyone has lungs! Offended...", new String[]{"Apologies"});
                    }});

                    addScriptAction(new ChangeFlagScriptAction("mermaid2", true));
                }});
            }});

            scriptActions.add(new ConditionalScriptAction() {{
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new FlagRequirement("mermaid1", true));

                    addScriptAction(new TextboxScriptAction() {{
                        addText("Looks like you spoke with my friend over there.", new String[] {"Yeah she didn't like me it seems"});
                        addText("I mean, after eavesdropping on your conversation, you do seem boring as a uncut pearl", new String[]{"Ouch"});
                        addText("If you want to speak with our ruler you're gonna have to go past the castle.", new String[]{"Thank ye"});
                        addText("But careful, the ruler seems awfully vexed about something lately.");
                    }});
                }});
            }});

            scriptActions.add(new ConditionalScriptAction() {{
                addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                    addRequirement(new FlagRequirement("mermaid2", true));

                    addScriptAction(new TextboxScriptAction() {{
                    addText("Still hanging around? Don't you have something more important to do?");
                    }});
                }});
            }});

            scriptActions.add(new UnlockPlayerScriptAction());

            return scriptActions;
            }
            
        });
        npcs.add(mermaid2);

        Merman merman1 = new Merman(13, getMapTile(44, 25).getLocation(), "CharacterPNGs/merman1.png", 24, 48, 15, 2, 1, 1);
        merman1.setInteractScript(new MermanGuard1Script());
        merman1.stand(Direction.RIGHT);
        npcs.add(merman1);

        Merman merman2 = new Merman(14, getMapTile(50, 20).getLocation(), "CharacterPNGs/merman2.png", 24, 48, 15, 2, 1, 1);
        npcs.add(merman2);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //add triggers below, commented out one is an example.

        triggers.add(new Trigger(getMapTile(41, 39).getLocation().x, getMapTile(41, 39).getLocation().y, 1100, 50, new MermanGuardMoveScript(), "guardScriptTriggered"));
        triggers.add(new Trigger(getMapTile(37, 0).getLocation().x, getMapTile(37, 0).getLocation().y, 50, 750, new MermanGuardMoveScript(), "guardScriptTriggered"));

        triggers.add(new Trigger(getMapTile(41, 38).getLocation().x, getMapTile(41, 38).getLocation().y, 1100, 50, new Script() {

            @Override
            public ArrayList<ScriptAction> loadScriptActions() {
                ArrayList<ScriptAction> scriptActions = new ArrayList<>();
                scriptActions.add(new LockPlayerScriptAction());

                scriptActions.add(new TextboxScriptAction() {{
                    addText("You do not have permission to proceed further");
                }});

                scriptActions.add(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        player.setLocation(player.getX(), player.getY()+10);
                        return ScriptState.COMPLETED;
                    }
                });

                scriptActions.add(new UnlockPlayerScriptAction());
                return scriptActions;
            }
            
        }, "mermanGuardComplete"));

        triggers.add(new Trigger(getMapTile(38,0).getLocation().x, getMapTile(38,0).getLocation().y, 50, 750, new Script() {

            @Override
            public ArrayList<ScriptAction> loadScriptActions() {
                ArrayList<ScriptAction> scriptActions = new ArrayList<>();
                scriptActions.add(new LockPlayerScriptAction());

                scriptActions.add(new TextboxScriptAction() {{
                    addText("You do not have permission to proceed further");
                }});

                scriptActions.add(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        player.setLocation(player.getX()-10, player.getY());
                        return ScriptState.COMPLETED;
                    }
                });

                scriptActions.add(new UnlockPlayerScriptAction());
                return scriptActions;
            }
            
        }, "mermanGuardComplete"));

        triggers.add(new Trigger(getMapTile(0, 6).getLocation().x, getMapTile(0, 6).getLocation().y, 15, 50, new AtlantisExitScript()));
        return triggers;
    }

    @Override
    public void loadScripts() {
        // getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        // getMapTile(37, 5).setInteractScript(new AtlantisHoleScript());
    }

    @Override
    public void loadMusic() {
        Music.playMusic("Music/Atlantis.wav");
    }
}

