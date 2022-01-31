package net.velocityworks.dtdu.world;

import static java.lang.System.out;
import static net.velocityworks.dtdu.world.Registry.*;
import static net.velocityworks.dtdu.world.World.*;

import net.velocityworks.dtdu.items.base.Item;
import net.velocityworks.dtdu.util.*;

public class SceneHandler {
	public static void runScene() {
		switch(scene) {
		case 0 -> Map.genBar();
		case 1 -> Map.genSchlafzimmer();
		case 2 -> Map.genGarten();
		default -> {
			out.println("Game Over");
			System.exit(0);}
		}
		while(true) {
			if(sceneTransition != scene) {
				scene = sceneTransition;
				return;
			}
			if(inventoryToggle) {
				renderInventory();
				Inventory.inventoryControl();
			} else {
				renderScene();
				player.playerControl();
			}
		}
	}
	public static void renderInventory() {
		String line = "-------------------";
		out.println("---");
		out.println("|" + (Inventory.equipSlot == null ? " " : Inventory.equipSlot.icon) + "|");
		out.println(line);
		for(int i = 0; i < Inventory.length; i++) {
			out.print("|");
			Item item = Inventory.main[i];
			if(item == null) {
				out.print(" ");
			} else {
				out.print(item.icon);
			}
			if(i % 9 + 1 == 9) {
				out.println("|");
				out.println(line);
			}
		}
	}
	public static void renderScene() {
		out.println("Place: " + LangProvider.getSceneName());
		out.println("Location: " + LangProvider.getLocation());
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[0].length; x++) {
				char c = Map.getIcon(x, y);
				if(c == ' ') {
					out.print("  ");
				} else {
					out.print(c + " ");
				}
			}
			out.println();
		}
	}
}