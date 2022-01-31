package net.velocityworks.dtdu.world;

import static java.lang.System.out;
import static net.velocityworks.dtdu.world.Registry.scanner;
import static net.velocityworks.dtdu.util.Logger.*;
import static net.velocityworks.dtdu.world.World.*;
import static net.velocityworks.dtdu.util.Mathe.*;

import net.velocityworks.dtdu.items.base.Item;
import net.velocityworks.dtdu.util.Logger;

public class Inventory {
	public static final byte length = 27;
	public static Item equipSlot = null;
	public static Item main[] = new Item[length];
	public static void inventoryControl() {
		out.println("Select an Item");
		String input = readInput();
		char inputChar = input.charAt(0);
		if(inputChar == 'e') {
			inventoryToggle = false;
		} else if(inputChar == '?') {
			out.println("Help: press 'e' to close Inventory or select an Item by typing 1-27(main inventory space) or 0(equipslot)");
			inventoryControl();
		} else {
			while(!isNumeric(input)) {
				out.println("Please enter a value");
				input = readInput();
			}
			byte inputbyte = Byte.parseByte(input);
			inputbyte--;
			if(inputbyte == -1) {
				interact(equipSlot);
			} else if(inputbyte < length) {
				Item selectedItem = main[inputbyte];
				interact(selectedItem);
			} else {
				out.println((inputbyte + 1) + " is outside of Inventory main(1-27) equipSlot(0)");
			}
		}
	}
	public static void interact(Item item) {
		if(item == null) {
			out.println("This slot is empty");
			scanner.nextLine();
			return;
		}
		out.println(item.name);
		out.println("Amount: " + item.amount);
		out.println("Select an action");
		switch(Logger.readInputChar(false, '?')) {
		case 'e' -> equip(item);
		case 'u' -> item.use();
		case 'd' -> remove(item);
		case 'q' -> {
			return;
		}
		case '?' -> {
			help();
			interact(item);
		}
		default -> Logger.errorEntry("There is no such action");
		}
	}
	private static void help() {
		out.println("Listing actions:");
		out.println("e: (Un)Equip");
		out.println("u: Use");
		out.println("d: Destroy");
		out.println("q: Quit interaction");
		scanner.nextLine();
	}
	public static void equip(Item item) {
		if(equipSlot == null) {
			remove(item);
			equipSlot = item;
		} else if(equipSlot == item){
			equipSlot = null;
			item = add(item);
			if(item != null) {
				equipSlot = item;
			}
		} else {
			remove(item);
			add(equipSlot);
			equipSlot = item;
		}
	}
	public static void remove(Item item) {
		main[indexOf(item)] = null;
	}
	public static Item pickUp(Item item, int amount) {
		byte slot = indexOf(item);
		if(slot == -1) {
			item.amount = amount;
			return add(item);
		} else {
			main[slot].amount += amount;
			return null;
		}
	}
	public static Item add(Item item) {
		for(byte b = 0; b < length; b++) {
			if(main[b] == null) {
				main[b] = item;
				return null;
			}
		}
		out.println("Inventory full");
		return item;
	}
	public static byte indexOf(Item item) {
		for(byte b = 0; b < length; b++) {
			if(main[b] == item) {
				return b;
			}
		}
		return -1;
	}
}