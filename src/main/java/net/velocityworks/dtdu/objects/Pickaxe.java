package net.velocityworks.dtdu.objects;

import static net.velocityworks.dtdu.world.Registry.*;

import net.velocityworks.dtdu.objects.base.Harvestable;
import net.velocityworks.dtdu.util.Logger;
import net.velocityworks.dtdu.world.Map;
import net.velocityworks.dtdu.items.base.Item;

public class Pickaxe extends Harvestable {
	public Pickaxe() {
		super();
		this.container = new Item(1, "Pickaxe", 'p');
	}
	@Override
	protected void attributes() {
		this.icon = 'p';
		this.name = "Pickaxe";
	}
	@Override
	public boolean interaction(final int x, final int y) {
		super.interaction(x, y);
		Logger.debugEntry(name);
		if(quest) {
			Map.set(bed, x, y);
		}
		return false;
	}
	@Override
	protected void rewardMessage() {
		aquiredMessage("a pickaxe");
	}
}