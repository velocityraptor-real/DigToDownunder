package net.velocityworks.dtdu.objects.base;

import static java.lang.System.out;
import static net.velocityworks.dtdu.world.Registry.scanner;

import net.velocityworks.dtdu.items.base.Item;
import net.velocityworks.dtdu.world.Inventory;

public class Harvestable extends Quest {
	public Item container;
	public int amount = 0;
	protected String message = "something";
	public Harvestable() {
		super();
	}
	public Harvestable(final Item container, final int amount, final String name, final char icon, final String message) {
		super(name, icon);
		this.container = container;
		this.amount = amount;
		this.message = message;
	}
	@Override
	protected void attributes() {
		this.icon = 'h';
		this.name = "HarvestableObject";
	}
	@Override
	protected void questReward() {
		if(container == null) {
			out.println("You aquired nothing!");
			scanner.nextLine();
		} else {
			container = Inventory.pickUp(container, amount);
			if(container == null) {
				rewardMessage();
			} else {
				quest = false;
			}
		}
	}
	protected void rewardMessage() {
		out.println("You aquired " + message + "!");
		scanner.nextLine();
	}
}