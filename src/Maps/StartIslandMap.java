package Maps;

import EnhancedMapTiles.PushableRock;
import Level.*;
import NPCs.*;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.CustomRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import Scripts.SimpleTextScript;
import Scripts.StartIslandMap.*;
import Tilesets.CommonTileset;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class StartIslandMap extends Map {

    public StartIslandMap() {
        super("starting_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(17, 20).getLocation();
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        PushableRock pushableRock = new PushableRock(getMapTile(2, 7).getLocation());
        enhancedMapTiles.add(pushableRock);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Walrus walrus = new Walrus(1, getMapTile(10, 24).getLocation().subtractY(40));
        walrus.setInteractScript(new WalrusScript());
        npcs.add(walrus);

        Walrus walrus2 = new Walrus(2, getMapTile(15, 24).getLocation().subtractY(40));
        walrus2.setInteractScript(new WalrusScript());
        npcs.add(walrus2);

        // Dinosaur dinosaur = new Dinosaur(3, getMapTile(13, 4).getLocation());
        // dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        // dinosaur.setInteractScript(new DinoScript());
        // npcs.add(dinosaur);

        //Shrek 'enemy' for combat test
        // Shrek shrek = new Shrek(5, getMapTile(15, 20).getLocation().subtractX(20));
        // shrek.setExistenceFlag("combatTriggered");
        // shrek.setInteractScript(new ShrekScript());
        // npcs.add(shrek);

        // Portal portal = new Portal(6, getMapTile(16, 25).getLocation());
        // // portal.setExistenceFlag("interactPortal");
        // portal.setInteractScript(new PortalScript());
        // npcs.add(portal);

        CapJV capJDV = new CapJV(3,getMapTile(14, 16).getLocation().subtractX(20), -1, -1);
        capJDV.setInteractScript(new TutorialScript());
        npcs.add(capJDV);

        RedPotion potion = new RedPotion(7, getMapTile(13, 20).getLocation());
        potion.setExistenceFlag("startIslandPotion");
        potion.setInteractScript(new PotionScript());
        npcs.add(potion);

        Meat mysteryMeat = new Meat(9, getMapTile(16,5).getLocation());
        mysteryMeat.setInteractScript(new MeatScript());
        npcs.add(mysteryMeat);

        MysteriousMan mysteryMan = new MysteriousMan(10,getMapTile(8, 2).getLocation());
        mysteryMan.setInteractScript(new ManScript());
        npcs.add(mysteryMan);

        Girl girl = new Girl(12,getMapTile(9,9).getLocation());
        girl.setInteractScript(new GirlScript());
        npcs.add(girl);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(200, 1360, 200, 10, new Script() {

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

                        addScriptAction(new ChangeFlagScriptAction("exitIsland", true));
                    }});

                }});
                scriptActions.add(new UnlockPlayerScriptAction());

                return scriptActions;
            }
        }
        ));
        // triggers.add(new Trigger(790, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        // triggers.add(new Trigger(890, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        return triggers;
    }

    @Override
    public void loadScripts() {
        // getMapTile(21, 19).setInteractScript(new SimpleTextScript("Cat's house"));

        // getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));

        // getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        // getMapTile(2, 6).setInteractScript(new TreeScript());
    }

    //all new maps must override
    @Override
    public void loadMusic() {
        // Music.playMusic("Music/Bossa_Drum_Beat16bit.wav");
    }
}

