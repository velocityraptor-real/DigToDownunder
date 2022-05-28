package net.velocityworks.dtdu.items.base;

import static net.velocityworks.dtdu.Main.INVENTORY_TOGGLE;
import static net.velocityworks.dtdu.world.Inventory.*;

public class Tool extends Item {
	private final int maxDurability;
	public int durability;
	public Tool(String name, char icon) {
		super(name, icon);
		this.maxDurability = 255;
		durability = maxDurability;
	}
	public Tool(String name, char icon, int durability) {
		super(name, icon);
		this.maxDurability = durability;
		this.durability = durability;
	}
	@Override
	public void use() {
		if(INVENTORY_TOGGLE) super.use();
		else durability--;
		if(durability == 0) equipSlot = null;
	}
	public void equip() {
		if(equipSlot == this) {
			equipSlot = null;
			add(this);
		} else {
			remove(this);
			if(equipSlot == null) equipSlot = this;
			else {
				add(equipSlot);
				equipSlot = this;
			}
		}
	}
}