package net.velocityworks.dtdu.world;

import static java.lang.System.out;
import static net.velocityworks.dtdu.world.Registry.scanner;
import static net.velocityworks.dtdu.util.Logger.*;
import static net.velocityworks.dtdu.world.World.*;

import net.velocityworks.dtdu.items.base.Item;
import net.velocityworks.dtdu.util.Mathe;

public class Inventory {
	public static final int length = 27;
	public static Item equipSlot = null;
	public static void inventoryControl() {
		out.println("Select an Item");
		String input = readInput();
		if(input.charAt(0) == 'e') {
			inventoryToggle = false;
		} else {
			int inputInt = Mathe.stringToInt(input);
			inputInt--;
			if(isInside(inputInt)) {
				Item item = get(inputInt);
				if(item == null) {
					out.println("This slot is empty");
					scanner.nextLine();
				} else {
					item.interact(inputInt);
				}
			}
		}
	}
	public static void move(final int slot) {
		Item i = get(slot);
		if(i.amount == 1) {
			out.println("Select slot to move " + i.name + " to");
		} else {
			out.println("Select slot to move " + i.name + "s to");
		}
		int targetSlot = Mathe.stringToInt(readInput());
		targetSlot--;
		if(isInside(targetSlot)) {
			set(null, slot);
			if(get(targetSlot) != null) {
				move(targetSlot);
			}
			set(i, targetSlot);
		}
	}
	public static Item addToInventory(Item item) {
		boolean contains = contains(item);
		for(int slot = 0; slot < length; slot++) {
			Item invSlot = get(slot);
			if(contains) {
				if(invSlot.name == item.name) {
					invSlot.add(item.amount);
					return null;
				}
			} else {
				if(invSlot == null) {
					set(item, slot);
					return null;
				}
			}
		}
		errorEntry("Inventory full");
		return item;
	}
	public static Item addToInventory(Item item, int slot) {
		if(isInside(slot)) {
			Item invSlot = get(slot);
			if(invSlot == null) {
				set(item, slot);
			} else if(invSlot.name == item.name) {
				invSlot.add(item.amount);
			} else {
				set(item, slot);
				return addToInventory(invSlot);
			}
			return null;
		}
		return item;
	}
	public static void equip(final Item item) {
		equipSlot = item;
	}
	public static void set(final Item item, final int slot) {
		itemInventory[slot] = item;
	}
	public static Item get(final int slot) {
		return itemInventory[slot];
	}
	public static boolean contains(final Item item) {
		for(Item i : itemInventory) {
			if(i != null && item.name == i.name) {
				return true;
			}
		}
		return false;
	}
	public static boolean isInside(int slot) {
		if(slot >= length || slot < 0) {
			errorEntry(++slot + " is outside of Inventory (1-27)");
			return false;
		}
		return true;
	}
}