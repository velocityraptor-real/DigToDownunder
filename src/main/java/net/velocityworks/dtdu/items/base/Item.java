package net.velocityworks.dtdu.items.base;

import static java.lang.System.out;
import static net.velocityworks.dtdu.world.Registry.*;

import net.velocityworks.dtdu.util.*;
import net.velocityworks.dtdu.world.Inventory;

public class Item {
	public String name;
	public char icon;
	protected int slot;
	public int amount;
	public Item(final int amount) {
		this.amount = amount;
		attributes();
	}
	public Item(final int amount, final String name, final char icon) {
		this.amount = amount;
		this.icon = icon;
		this.name = name;
	}
	protected void attributes() {
		this.icon = 'i';
		this.name = "Item";
	}
	public void interact(int slot) {
		this.slot = slot;
		if(amount == 1) {
			out.println(amount + " " + name);
		} else {
			out.println(amount + " " + name + "s");
		}
		out.println("Choose your action");
		itemActions(Logger.readInputChar(false, '?'));
	}
	protected void itemActions(char input) {
		switch(input) {
		case 'm' -> Inventory.move(slot);
		case 'e' -> equip();
		case 'q' -> {
			return;
		}
		case '?' -> help();
		default -> Logger.errorEntry("This action is not available for this Item");
		}
	}
	protected void equip() {
		itemActions(' ');
	}
	protected final void help() {
		out.println("Listing actions:");
		out.println("q: Quit");
		out.println("m: Move");
		out.println("e: Equip");
		scanner.nextLine();
		interact(slot);
	}
	public void add(final int amount) {
		this.amount += amount;
	}
}