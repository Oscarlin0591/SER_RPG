package Maps;

import Level.*;
import NPCs.AppleTree;
import NPCs.BluePotion;
import NPCs.Bosses.Capricorn;
import NPCs.Interactable.*;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
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

        BluePotion bluePotion = new BluePotion(11,getMapTile(35,45).getLocation());
        bluePotion.setInteractScript(new SuperPotionScript());
        npcs.add(bluePotion);

        Mermaid mermaid1 = new Mermaid(12, getMapTile(5, 5).getLocation(), "CharacterPNGs/mermaid1.png", 31, 52, 15, 2, 1, 1);
        mermaid1.stand(Direction.RIGHT);
        mermaid1.setInteractScript(new Script() {

            @Override
            public ArrayList<ScriptAction> loadScriptActions() {
                ArrayList<ScriptAction> scriptActions = new ArrayList<>();
            scriptActions.add(new LockPlayerScriptAction());
            scriptActions.add(new NPCFacePlayerScriptAction());

            scriptActions.add(new TextboxScriptAction() {{
                addText("What a beautiful day in Atlantis!");
                addText("What bring you here today, traveler?", new String[] {"I'm looking for your ruler"});
                addText("Ugh... Let me guess, that golden ship", new String[] {"Ye know about it?"});
                addText("All you sailor boys care about is \"The Nave d'Oro\" and I'm tired of hearin about it.");
                addText("Why can't we get a fun pirate who just drinks rum and bring down something like...\nI don't know, a jar of dirt from the surface.", new String[] {"I can bring ye gold if you'd like."});
                addText("Uggghhhh... go away, you're no fun.");

            }});

            scriptActions.add(new UnlockPlayerScriptAction());

            return scriptActions;
            }
            
        });
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
                    addRequirement(new FlagRequirement("mermaid1", true));
                }});
            }});

            scriptActions.add(new TextboxScriptAction() {{
                addText("What a beautiful day in Atlantis!");
                addText("What bring you here today, traveler?", new String[] {"I'm looking for your ruler"});
                addText("Ugh... Let me guess, that golden ship", new String[] {"Ye know about it?"});
                addText("All you sailor boys care about is \"The Nave d'Oro\" and I'm tired of hearin about it.");
                addText("Why can't we get a fun pirate who just drinks rum and bring down something like...\nI don't know, a jar of dirt from the surface.", new String[] {"I can bring ye gold if you'd like."});
                addText("Uggghhhh... go away, you're no fun.");

            }});

            scriptActions.add(new UnlockPlayerScriptAction());

            return scriptActions;
            }
            
        });
        npcs.add(mermaid2);

        Merman merman1 = new Merman(13, getMapTile(44, 25).getLocation(), "CharacterPNGs/merman1.png", 24, 48, 15, 2, 1, 1);
        merman1.stand(Direction.RIGHT);
        npcs.add(merman1);

        Merman merman2 = new Merman(13, getMapTile(50, 20).getLocation(), "CharacterPNGs/merman2.png", 24, 48, 15, 2, 1, 1);
        npcs.add(merman2);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //add triggers below, commented out one is an example.

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

