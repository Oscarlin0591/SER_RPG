package Maps;

//import EnhancedMapTiles.PushableRock;
import Level.*;
import NPCs.*;
import NPCs.Bosses.Kraken;
import NPCs.Islands.*;
import Screens.PlayLevelScreen;
import Scripts.SimpleTextScript;
import Scripts.ShipwreckScripts.*;
import Scripts.OceanMapScripts.*;
import Scripts.StartIslandMap.*;
import ScriptActions.*;
import Tilesets.MasterTileset;
import Utils.Point;

import java.util.ArrayList;
import Screens.PlayLevelScreen;

// Represents a test map to be used in a level
public class OceanMap extends Map {
    protected int goodShipXPosition;
    protected int goodShipYPosition;
    protected String goodShipAnimation;
    protected String badShipExistenceFlag;
    
    public OceanMap() {
        super("game_map.txt", new MasterTileset());
        this.playerStartPosition = getMapTile(13, 6).getLocation();
    }

    public OceanMap(Point playerLoc) {
        super("game_map.txt", new MasterTileset());
        System.out.println((int)(Math.ceil(playerLoc.x/16)) + " " + (int)playerLoc.y);
        this.playerStartPosition = getMapTile((int)(Math.ceil(playerLoc.x/16)), (int)(Math.ceil(playerLoc.y/16))).getLocation();
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        MainIsland island = new MainIsland(2, getMapTile(13, 4).getLocation());
        island.setInteractScript(new IslandScript());
        npcs.add(island);

        Shipwreck shipwreck1 = new Shipwreck(3, getMapTile(5,20).getLocation(),"Shipwreck.png");
        shipwreck1.setInteractScript(new ShipwreckScript()/*SimpleTextScript("An unfortunate vessel appears to have fallen into the\nmarine abyss. You pray for the sailors' lost souls...")*/);
        npcs.add(shipwreck1);

        PirateShip pirateShip = new PirateShip(6, getMapTile(36,33).getLocation(), "pirateShip.png", -1, -1, -1, -1);
        pirateShip.setInteractScript(new PirateScript());
        npcs.add(pirateShip);
        
        Cave cave = new Cave(4, getMapTile(2, 13).getLocation());
        cave.setInteractScript(new CaveScript());
        npcs.add(cave);

        Atlantis atlantis = new Atlantis(5, getMapTile(36, 18).getLocation());
        atlantis.setInteractScript(new AtlantisScript());
        npcs.add(atlantis);

        EndIsland endIsland = new EndIsland(7, getMapTile(24,24).getLocation());
        endIsland.setInteractScript(new Script() {
            @Override
            public ArrayList<ScriptAction> loadScriptActions() {
                ArrayList<ScriptAction> scriptActions = new ArrayList<>();
                scriptActions.add(new LockPlayerScriptAction());

                scriptActions.add(new TextboxScriptAction() {{
                    addText("Enter Island?", new String[] { "Yes", "No" });
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

                        addScriptAction(new ChangeFlagScriptAction("toggleEndIsland", true));
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
        }});
        npcs.add(endIsland);

        RedPotion potion = new RedPotion(77, getMapTile(2,10).getLocation());
        potion.setExistenceFlag("oceanPotion");
        potion.setInteractScript(new PotionScript());
        npcs.add(potion);

        BluePotion bluePotion = new BluePotion(11,getMapTile(1,5).getLocation());
        bluePotion.setInteractScript(new SuperPotionScript());
        npcs.add(bluePotion);

        Diver diver = new Diver(13,getMapTile(1,33).getLocation());
        diver.setInteractScript(new DiverScript());
        npcs.add(diver);

        BlueGem gem = new BlueGem(24,getMapTile(18,46).getLocation());
        gem.setExistenceFlag("blueGem");
        gem.setInteractScript(new BlueGemScript());
        npcs.add(gem);

        WorriedMan man = new WorriedMan(31,getMapTile(37,2).getLocation());
        man.setInteractScript(new WorriedManScript());
        npcs.add(man);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //add triggers below, commented out one is an example.

        triggers.add(new Trigger(930, 2200, 300, 20, new ArcticScript()));
        return triggers;
    }

    @Override
    public void loadScripts() {
        getMapTile(28, 34).setInteractScript(new SimpleTextScript("South Pole Below"));

        // getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));

        // getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        // getMapTile(2, 6).setInteractScript(new TreeScript());
    }

    @Override
    public void loadMusic() {
        Music.playMusic("Music/Seafaring Humdrum.wav");
    }
}

