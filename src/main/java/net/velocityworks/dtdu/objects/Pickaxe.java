package net.velocityworks.dtdu.objects;

import static net.velocityworks.dtdu.world.Registry.*;

import net.velocityworks.dtdu.objects.base.*;
import net.velocityworks.dtdu.objects.statico.type.HarvestType;
import net.velocityworks.dtdu.world.Map;

public class Pickaxe extends Harvestable {
	public Pickaxe() {
		super(new HarvestType("Pickaxe", 'p', pickaxe), 3, 2);
	}
	@Override
	public void interaction(Generic o) {
		super.interaction(o);
		Map.set(bed, x, y);
	}
}