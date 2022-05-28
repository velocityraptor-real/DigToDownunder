package net.velocityworks.dtdu.world;

import static java.lang.System.out;
import static net.velocityworks.dtdu.world.Registry.*;
import static net.velocityworks.dtdu.util.Logger.*;
import static net.velocityworks.dtdu.Main.*;
import static net.velocityworks.dtdu.util.Mathe.*;

import net.velocityworks.dtdu.items.base.*;
import net.velocityworks.dtdu.util.Logger;

public final class Inventory {
	private Inventory() {}
	public static final byte length = 27;
	public static Armor armorSlot;
	public static Tool toolSlot;
	public static Item inventory[] = new Item[length];
	public static void run() {
		String line = "-------------------";
		out.println("-----");
		out.println("|" + getIcon(toolSlot) + "|" + getIcon(armorSlot) + "| Health: " + player.health);
		out.println(line);
		for(int i = 0; i < length; i++) {
			out.print("|");
			Item item = inventory[i];
			out.print(getIcon(item));
			if(i % 9 == 8) {
				out.println("|");
				out.println(line);
			}
		}
		controls();
	}
	static char getIcon(Item item) {
		return item == null ? ' ' : item.icon;
	}
	private static void controls() {
		String input = readInput();
		char inputChar = input.charAt(0);
		if(inputChar == 'e') INVENTORY_TOGGLE = false;
		else if(inputChar == '?') {
			out.println("Help: press 'e' to close Inventory or select an Item by typing 1-27(main inventory space) or 0(equipslot) or -1(armor)");
			controls();
		} else {
			if(!isNumeric(input)) {
				out.println("Please enter a valid control");
				controls();
			}
			byte inputbyte = Byte.parseByte(input);
			if(inputbyte > length || inputbyte < -1) out.println(inputbyte + " is outside of Inventory main(1-27) equipSlot(0) armor(-1)");
			else {
				inputbyte--;
				if(inputbyte == -1) interact(toolSlot);
				else if(inputbyte == -2) interact(armorSlot);
				else {
					Item selectedItem = inventory[inputbyte];
					interact(selectedItem);
				}
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
		case 'e' -> {
			if(item instanceof DurabilityItem) ((DurabilityItem) item).equip();
			else {
				out.println("You can't equip this item.");
				scanner.nextLine();
			}
		}
		case 'u' -> item.use();
		case 'd' -> remove(item);
		case 'q' -> {
			return;
		}
		case '?' -> {
			out.println("Listing actions:");
			out.println("e: (Un)Equip");
			out.println("u: Use");
			out.println("d: Destroy");
			out.println("q: Quit interaction");
			scanner.nextLine();
			interact(item);
		}
		default -> Logger.errorEntry("There is no such action");
		}
	}
	public static void remove(Item item) {inventory[indexOf(item)] = null;}
	public static boolean pickUp(Item item) {return pickUp(item, 1);}
	public static boolean pickUp(Item item, int amount) {
		if(item == null || amount == 0) return true;
		out.println("You picked up " + amount + " " + item.name + "!");
		scanner.nextLine();
		if(toolSlot == item) {
			toolSlot.amount += amount;
			return true;
		}
		byte slot = indexOf(item);
		if(slot == -1) {
			item.amount = amount;
			return add(item) == null;
		} else {
			inventory[slot].amount += amount;
			return true;
		}
	}
	public static Item add(Item item) {
		for(byte b = 0; b < length; b++) {
			if(inventory[b] == null) {
				inventory[b] = item;
				return null;
			}
		}
		out.println("Inventory full");
		scanner.nextLine();
		return item;
	}
	static byte indexOf(Item item) {
		for(byte b = 0; b < length; b++) if(inventory[b] == item) return b;
		return -1;
	}
}