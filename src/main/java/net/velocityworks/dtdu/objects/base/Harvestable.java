package net.velocityworks.dtdu.objects.base;

import net.velocityworks.dtdu.objects.statico.type.HarvestType;

public class Harvestable extends Quest {
	public Harvestable(final HarvestType o, final int x, final int y) {
		super(o, x, y);
	}
	public Harvestable(final HarvestType o, final boolean removable, final int x, final int y) {
		super(o, removable, x, y);
	}
	public Harvestable(final HarvestType o, final int x, final int y, boolean quest) {
		super(o, x, y, quest);
	}
	@Override
	protected boolean questCondition() {
		return ((HarvestType) staticPart).harvest();
	}
	@Override
	protected void questReward() {}
}