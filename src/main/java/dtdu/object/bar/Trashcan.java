package main.java.dtdu.object.bar;

import main.java.dtdu.object.base.GameObject;
import main.java.dtdu.util.*;
import main.java.dtdu.world.Save;
import main.java.dtdu.object.*;
import main.java.dtdu.item.ItemCreator;
import main.java.dtdu.graphics.gui.Dialogue;
import main.java.dtdu.graphics.*;
import main.java.dtdu.engine.Registry;

public class Trashcan extends GameObject {
    public Direction interacted(GameObject from, Direction direction) {
        if (from instanceof Player && !DataReader.readBoolean(Save.map.additionalData[0])) {
            DataWriter.write(true, Save.map.additionalData, 0);
            ((ItemCreator) Registry.get("coin")).create((byte) 3).addToInventory();
            Dialogue.start(Textures.trashcan, new String[] { Language.get("threecoins") },
                    new boolean[] { true });
        }
        return Direction.NONE;
    };

    // Type
    public String getRegistryName() {
        return "trash_can";
    }

}
