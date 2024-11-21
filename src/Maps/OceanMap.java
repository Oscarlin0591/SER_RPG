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
        shipwreck1.setInteractScript(new ShipwreckScript());
        npcs.add(shipwreck1);

        PirateShip pirateShip = new PirateShip(6, getMapTile(33,33).getLocation(), "pirateShip.png", -1, -1, -1, -1);
        pirateShip.setInteractScript(new PirateScript());
        npcs.add(pirateShip);
        
        Cave cave = new Cave(4, getMapTile(2,13).getLocation());
        cave.setInteractScript(new CaveScript());
        npcs.add(cave);

        Atlantis atlantis = new Atlantis(5, getMapTile(39, 12).getLocation());
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
                                getPlayer().setLocation(getPlayer().getX(), getPlayer().getY());
                                return ScriptState.COMPLETED;
                            }
                        });
                    }});

                }});
                scriptActions.add(new UnlockPlayerScriptAction());

                return scriptActions;
        }});
        npcs.add(endIsland);

        Diver diver = new Diver(13,getMapTile(1,33).getLocation());
        diver.setInteractScript(new DiverScript());
        npcs.add(diver);
        //determine various ship of theseus quest variable values based on quest progression
        this.goodShipAnimation = "LEFT";
        this.badShipExistenceFlag = "";

        BlueGem gem = new BlueGem(24,getMapTile(18,44).getLocation());
        gem.setExistenceFlag("blueGem");
        gem.setInteractScript(new BlueGemScript());
        npcs.add(gem);

        WorriedMan man = new WorriedMan(31,getMapTile(37,2).getLocation());
        man.setInteractScript(new WorriedManScript());
        npcs.add(man);
        
        //determine various ship of theseus quest variable values based on quest progression
        this.goodShipAnimation = "LEFT";
        this.badShipExistenceFlag = "";
        if (PlayLevelScreen.flagManager.isFlagSet("badShipKilled")) {
            this.goodShipXPosition = 39;
            this.goodShipYPosition = 8;
            this.goodShipAnimation = "LEFT";
            this.badShipExistenceFlag = "badShipKilled";
        } else if (PlayLevelScreen.flagManager.isFlagSet("goodShipMoved") && !PlayLevelScreen.flagManager.isFlagSet("goodShipPloy")) {
            this.goodShipXPosition = 6;
            this.goodShipYPosition = 35;
        } else if (PlayLevelScreen.flagManager.isFlagSet("goodShipPloy") && !PlayLevelScreen.flagManager.isFlagSet("shipDiscussion")) {
            this.goodShipXPosition = 6;
            this.goodShipYPosition = 33;
        } else if (PlayLevelScreen.flagManager.isFlagSet("shipDiscussion")) {
            this.goodShipXPosition = 6;
            this.goodShipYPosition = 31;
            this.goodShipAnimation = "RIGHT";
            this.badShipExistenceFlag = "shipDiscussion";
        } else {
            this.goodShipXPosition = 39;
            this.goodShipYPosition = 8;
        }
        ShipOfTheseus goodShipOfTheseus = new ShipOfTheseus(999, getMapTile(this.goodShipXPosition, this.goodShipYPosition).getLocation(), this.goodShipAnimation, -1, -1, -1, -1);
        goodShipOfTheseus.setInteractScript(new GoodShipOfTheseusScript());
        npcs.add(goodShipOfTheseus);
        
        ShipOfTheseus badShipOfTheseus = new ShipOfTheseus(666, getMapTile(6, 30).getLocation(), "RIGHT", -1, -1, -1, -1);
        badShipOfTheseus.setInteractScript(new BadShipOfTheseusScript());
        badShipOfTheseus.setExistenceFlag(this.badShipExistenceFlag);
        npcs.add(badShipOfTheseus);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //add triggers below, commented out one is an example.

        triggers.add(new Trigger(800, 2200, 500, 50, new ArcticScript()));
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

