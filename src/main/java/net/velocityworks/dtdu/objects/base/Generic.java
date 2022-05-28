package net.velocityworks.dtdu.objects.base;

import net.velocityworks.dtdu.world.Map;

public abstract class Generic {
	public abstract String getName();
	public abstract char getIcon();
	public abstract void interaction(final Generic o);
	public final void interactWith(final int x, final int y) {
		Map.get(x, y).interaction(this);
	}
	public void moveTo(final int fromX, final int fromY, final int toX, final int toY) {
		if(Map.isInsideMap(toX, toY)) {
			if(Map.hasSpace(toX, toY)) move(fromX, fromY, toX, toY);
			else interactWith(toX, toY);
		}
	}
	public void move(final int fromX, final int fromY, final int toX, final int toY) {
		setLocation(toX, toY);
		Map.remove(fromX, fromY);
	}
	public void setLocation(final int x, final int y) {
		if(Map.isInsideMap(x, y)) {
			Map.set(this, x, y);
		}
	}
}
