package Maps;

import Level.Map;
import Level.Music;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Trigger;
import NPCs.Bosses.*;
import NPCs.Interactable.BlueWitch;
import NPCs.Interactable.ExitPort;
import NPCs.Interactable.SkullTorch;
import ScriptActions.NPCStandScriptAction;
import ScriptActions.ScriptAction;
import NPCs.*;
// import Scripts.EndMapScripts.*;
import Tilesets.EndTileset;
import Utils.Direction;

import java.util.ArrayList;

public class EndMap extends Map{

    public EndMap() {
        super("end_map.txt", new EndTileset());
        this.playerStartPosition = getMapTile(7, 2).getLocation();
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
        torch3.setLocation(torch3.getX()-18, torch3.getY());
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


        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        // triggers.add(new Trigger(getMapTile(7, 3).getLocation().x, getMapTile(7, 3).getLocation().y+30, 100, 15, new EndExitScript()));
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
