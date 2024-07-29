package dtdu.engine;

import java.util.ArrayList;

import dtdu.item.ItemCreator;
import dtdu.item.bar.*;
import dtdu.object.*;
import dtdu.object.bar.*;
import dtdu.object.base.*;
import dtdu.util.*;

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
		register("chair", Chair::new);
		register(new Trashcan());
		//Items
		register("dropped_item", DroppedItem::new);
		register("coin", Coin::new);
		return true;
	}
}
