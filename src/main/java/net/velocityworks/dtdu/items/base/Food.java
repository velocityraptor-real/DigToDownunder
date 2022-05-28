package net.velocityworks.dtdu.items.base;

import static net.velocityworks.dtdu.world.Registry.player;

import net.velocityworks.dtdu.world.Inventory;

public class Food extends Item {
	public final float healthboost;
	public Food(String name, char icon) {
		super(name, icon);
		this.healthboost = 1F;
	}
	public Food(String name, char icon, float healthboost) {
		super(name, icon);
		this.healthboost = healthboost;
	}
	@Override
	public void use() {
		player.heal(healthboost);
		amount--;
		if(amount == 0) Inventory.remove(this);
	}
}