package net.velocityworks.dtdu.items;

import net.velocityworks.dtdu.items.base.Item;

public class ItemContainer {
	public Item item;
	public int amount;
	public ItemContainer(Item item) {
		this(item, 1);
	}
	public ItemContainer(Item item, int amount) {
		this.item = item;
		this.amount = amount;
	}
}