package net.velocityworks.dtdu.items.base;

import static net.velocityworks.dtdu.Main.INVENTORY_TOGGLE;

import net.velocityworks.dtdu.util.Logger;

public abstract class DurabilityItem extends Item {
	private final int maxDurability;
	public int durability;
	public DurabilityItem(String name, char icon) {
		super(name, icon);
		this.maxDurability = 255;
		durability = maxDurability;
	}
	public DurabilityItem(String name, char icon, int durability) {
		super(name, icon);
		this.maxDurability = durability;
		this.durability = durability;
	}
	@Override
	public void use() {
		if(INVENTORY_TOGGLE) super.use();
		else durability--;
		if(durability == 0) destroy();
		else if(durability < 5) Logger.say(name, "Only " + durability + " uses remaining");
	}
	public abstract void destroy();
	public abstract void equip();
}