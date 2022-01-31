package net.velocityworks.dtdu.objects;

import static net.velocityworks.dtdu.world.Registry.*;

import net.velocityworks.dtdu.objects.base.Harvestable;
import net.velocityworks.dtdu.world.Map;

public class Pickaxe extends Harvestable {
	public Pickaxe() {
		this.icon = 'p';
		this.name = "Pickaxe";
		this.container = pickaxe;
		this.amount = 1;
		this.message = "a pickaxe";
	}
	@Override
	public boolean interaction(final int x, final int y) {
		super.interaction(x, y);
		if(quest) {
			Map.set(bed, x, y);
		}
		return false;
	}
}