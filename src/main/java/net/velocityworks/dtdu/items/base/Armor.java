package net.velocityworks.dtdu.items.base;

import static net.velocityworks.dtdu.world.Inventory.*;

public class Armor extends DurabilityItem {
	public final float armor;
	public Armor(String name, char icon) {
		super(name, icon);
		this.armor = 0F;
	}
	public Armor(String name, char icon, float armor) {
		super(name, icon);
		this.armor = armor;
	}
	public Armor(String name, char icon, int durability) {
		super(name, icon, durability);
		this.armor = 0F;
	}
	public Armor(String name, char icon, float armor, int durability) {
		super(name, icon, durability);
		this.armor = armor;
	}
	@Override
	public void destroy() {armorSlot = null;}
	@Override
	public void equip() {
		if(armorSlot == this) {
			armorSlot = null;
			add(this);
		} else {
			remove(this);
			if(armorSlot == null) armorSlot = this;
			else {
				add(armorSlot);
				armorSlot = this;
			}
		}
	}
}
