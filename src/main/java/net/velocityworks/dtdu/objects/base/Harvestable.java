package net.velocityworks.dtdu.objects.base;

import static java.lang.System.out;
import static net.velocityworks.dtdu.world.Registry.scanner;

import net.velocityworks.dtdu.items.base.Item;
import net.velocityworks.dtdu.world.Inventory;

public class Harvestable extends Quest {
	public Item container = null;
	public Harvestable() {
		super();
	}
	public Harvestable(final Item container) {
		super();
		this.container = container;
	}
	public Harvestable(final String name, final char icon) {
		super(name, icon);
	}
	public Harvestable(final Item container, final String name, final char icon) {
		super(name, icon);
		this.container = container;
	}
	@Override
	protected void attributes() {
		this.icon = 'h';
		this.name = "HarvestableObject";
	}
	@Override
	protected void questReward() {
		if(container == null) {
			aquiredMessage("nothing");
		} else {
			rewardMessage();
			container = Inventory.addToInventory(container);
			if(container != null) {
				quest = false;
			}
		}
	}
	@Override
	protected void rewardMessage() {
		if(container.amount > 1) {
			aquiredMessage(container.amount + " " + container.name + "s");
		} else {
			aquiredMessage("a " + container.name);
		}
	}
	protected void aquiredMessage(String itemName) {
		out.println("You Aquired " + itemName + "!");
		scanner.nextLine();
	}
}