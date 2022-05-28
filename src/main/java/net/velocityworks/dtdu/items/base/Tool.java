package net.velocityworks.dtdu.items.base;

import static net.velocityworks.dtdu.world.Inventory.*;

public class Tool extends DurabilityItem {
	public final float damage;
	public Tool(String name, char icon) {
		super(name, icon);
		damage = 1F;
	}
	public Tool(String name, char icon, int durability) {
		super(name, icon, durability);
		damage = 1F;
	}
	public Tool(String name, char icon, int durability, float damage) {
		super(name, icon, durability);
		this.damage = damage;
	}
	@Override
	public void destroy() {
		toolSlot = null;
	}
	@Override
	public void equip() {
		if(toolSlot == this) {
			toolSlot = null;
			add(this);
		} else {
			remove(this);
			if(toolSlot == null) toolSlot = this;
			else {
				add(toolSlot);
				toolSlot = this;
			}
		}
	}
}
