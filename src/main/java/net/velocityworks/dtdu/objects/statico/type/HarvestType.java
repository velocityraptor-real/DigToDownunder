package net.velocityworks.dtdu.objects.statico.type;

import net.velocityworks.dtdu.items.ItemContainer;
import net.velocityworks.dtdu.items.base.Item;
import net.velocityworks.dtdu.objects.statico.StaticObject;
import net.velocityworks.dtdu.world.Inventory;

public class HarvestType extends StaticObject {
	private final ItemContainer[] container;
	public HarvestType(String name, char icon, Item item, int amount) {
		super(name, icon);
		container = new ItemContainer[]{new ItemContainer(item, amount)};
	}
	public HarvestType(String name, char icon, Item ... item) {
		super(name, icon);
		container = new ItemContainer[item.length];
		for(int i = 0; i < item.length; i++) container[i] = new ItemContainer(item[i]);
	}
	public HarvestType(String name, char icon, ItemContainer ... container) {
		super(name, icon);
		this.container = container;
	}
	public boolean harvest() {
		for(ItemContainer c : container) if(!Inventory.pickUp(c.item, c.amount)) return false;
		return true;
	}
}