package dtdu.object.bar;

import dtdu.object.base.GameObject;
import dtdu.util.*;
import dtdu.world.Save;
import dtdu.object.*;
import dtdu.item.ItemCreator;
import dtdu.graphics.gui.Dialogue;
import dtdu.graphics.*;
import dtdu.engine.Registry;

public class Trashcan extends GameObject {
    public Direction interacted(GameObject from, Direction direction) {
        if(from instanceof Player && Save.map.additionalData[0] == 0) {
        	Save.map.additionalData[0] = 1;
            ((ItemCreator) Registry.get("coin")).create((byte) 3).addToInventory();
            Dialogue.start(Textures.trashcan, new String[] { Language.get("threecoins") }, new boolean[] { true });
        } return Direction.NONE;
    };
    // Type
    public String getRegistryName() {
        return "trash_can";
    }
}