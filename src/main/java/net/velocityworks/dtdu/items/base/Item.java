package net.velocityworks.dtdu.items.base;

import static java.lang.System.out;
import static net.velocityworks.dtdu.world.Registry.scanner;

public class Item {
	public final String name;
	public char icon;
	public int amount;
	public Item(final String name, final char icon) {
		this.name = name;
		this.icon = icon;
		this.amount = 1;
	}
	public Item(final String name, final char icon, final int amount) {
		this.name = name;
		this.icon = icon;
		this.amount = amount;
	}
	public void use() {
		out.println("You can't use " + name + " in that way");
		scanner.nextLine();
	}
}