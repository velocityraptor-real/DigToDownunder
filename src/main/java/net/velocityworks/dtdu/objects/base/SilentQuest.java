package net.velocityworks.dtdu.objects.base;

import net.velocityworks.dtdu.objects.statico.StaticObject;

public class SilentQuest extends Quest {
	public SilentQuest(final StaticObject o) {
		super(o);
	}
	public SilentQuest(final StaticObject o, final int x, final int y) {
		super(o, x, y);
	}
	public SilentQuest(final StaticObject o, final int x, final int y, boolean quest) {
		super(o, x, y, quest);
	}
	public SilentQuest(final StaticObject o, final boolean removable, final int x, final int y) {
		super(o, removable, x, y);
	}
	@Override
	protected void idleInteraction() {}
	@Override
	protected void questReward() {}
}