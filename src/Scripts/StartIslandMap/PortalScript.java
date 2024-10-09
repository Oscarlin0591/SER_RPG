package Scripts.StartIslandMap;

import java.util.ArrayList;

import Level.*;
import MapEditor.EditorMaps;
import ScriptActions.*;


// script for "portal" npc
public class PortalScript extends Script {

    public static Map curMap;

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        ArrayList<String> mapArray = EditorMaps.getMapNames();

        String mapList[] = mapArray.toArray(new String[mapArray.size()]);

        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Nothing to see here...", mapList);
        }});

        scriptActions.add(new ScriptAction() {
            @Override
            public ScriptState execute() {
                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                map.setChosenMap(mapList[answer]);
                return ScriptState.COMPLETED;
            }
        });
    
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}

