package net.velocityworks.dtdu.items.base;

import static java.lang.System.out;
import static net.velocityworks.dtdu.world.Registry.scanner;

public class Item {
	public String name;
	public char icon;
	public int amount = 1;
	public Item() {
		attributes();
	}
	public Item(final String name, final char icon) {
		this.name = name;
		this.icon = icon;
	}
	public Item(final String name, final char icon, final int amount) {
		this.name = name;
		this.icon = icon;
		this.amount = amount;
	}
	protected void attributes() {
		this.icon = 'i';
		this.name = "Item";
	}
	public void use() {
		out.println("This item has no use");
		scanner.nextLine();
	}
}