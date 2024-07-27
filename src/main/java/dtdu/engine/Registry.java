package main.java.dtdu.engine;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.java.dtdu.graphics.Language;
import main.java.dtdu.graphics.Textures;
import main.java.dtdu.graphics.gui.Dialogue;
import main.java.dtdu.item.ItemCreator;
import main.java.dtdu.item.base.Item;
import main.java.dtdu.object.*;
import main.java.dtdu.object.bar.Pianist;
import main.java.dtdu.object.base.*;
import main.java.dtdu.util.*;
import main.java.dtdu.world.Save;

public interface Registry {
	static final ArrayList<String> KEYS = new ArrayList<>();
	static final ArrayList<Registry> REGISTRY = new ArrayList<>();
	static final boolean registered = register();
	static int register(String registryName, Registry c) {
		if(c != null) {
			int i = Mathe.binaryInsert(KEYS, registryName);
			if(i == REGISTRY.size()) REGISTRY.add(c);
			else REGISTRY.add(i, c);
			return i;
		} return -1;
	}
	static int register(GameObject o) {
		if(o instanceof Individual) throw new IllegalArgumentException("Wrong Registry usage! Register IndividualCreator instead.");
		return register(o.getRegistryName(), o);
	}
	static int register(String registryName, IndividualCreator c) {
		return register(registryName, (Registry) c);
	}
	static int register(String registryName, ItemCreator c) {
		return register(registryName, (Registry) c);
	}
	static Registry get(String registryName) {
		int i = Mathe.binarySearch(KEYS, registryName);
		if(i == -1) {
			System.err.println("Warning: element \"" + registryName + "\" not in Registry");
			System.out.print("Registry currently has: ");
			for(String s : KEYS) System.out.print(s + ", ");
			return null;
		}
		return REGISTRY.get(i);
	}
	static Registry get(int index) {
		return REGISTRY.get(index);
	}
	static int indexOf(String registryName) {
		return Mathe.binarySearch(KEYS, registryName);
	}
	static boolean register() {
		//Base
		register(new GameObject());
		register("player", Player::new);
		//Bar
		register("pianist", Pianist::new);
		register(new GameObject() {
			@Override public String getRegistryName() {return "trash_can";}
			public Direction interacted(GameObject from, Direction direction) {
				if(from instanceof Player && !DataReader.readBoolean(Save.map.additionalData[0])) {
					DataWriter.write(true, Save.map.additionalData, 0);
					((ItemCreator) get("coin")).create((byte)3).addToInventory();
					Dialogue.start(Textures.trashcan, new String[] {Language.get("threecoins")}, new boolean[] {true});
				} return Direction.NONE;
			};
		});
		//Items
		register("dropped_item", DroppedItem::new);
		register("coin", (amount) -> new Item(amount) {
			@Override public BufferedImage getImage() {return Textures.coin;}
			@Override public String getRegistryName() {return "coin";}
		});
		return true;
	}
}