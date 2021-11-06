package net.velocityworks.dtdu.world;

import net.velocityworks.dtdu.items.base.Item;
import net.velocityworks.dtdu.objects.base.Generic;

public class World {
	public static boolean inventoryToggle = false;
	public static Item itemInventory[] = new Item[Inventory.length];
	public static int scene = 0;
	public static int sceneTransition = scene;
	public static Generic map[][];
	public static char iconMap[][];
}