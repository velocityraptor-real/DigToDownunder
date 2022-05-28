package net.velocityworks.dtdu.objects.statico;

import net.velocityworks.dtdu.items.base.Item;
import net.velocityworks.dtdu.world.Inventory;

public class Harvest extends StaticObject {
	private final Item container;
	private final int amount;
	public Harvest(String name, char icon, Item container) {
		this(name, icon, container, 1);
	}
	public Harvest(String name, char icon, Item container, int amount) {
		super(name, icon);
		this.container = container;
		this.amount = amount;
	}
	public boolean harvest() {
		return Inventory.pickUp(container, amount);
	}
}