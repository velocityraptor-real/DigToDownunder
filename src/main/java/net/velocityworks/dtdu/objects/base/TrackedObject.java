package net.velocityworks.dtdu.objects.base;

import net.velocityworks.dtdu.objects.ai.Moving;
import net.velocityworks.dtdu.objects.statico.StaticObject;

public class TrackedObject extends TypableObject implements Moving {
	protected int x;
	protected int y;
	public TrackedObject(final StaticObject o) {
		super(o);
	}
	public TrackedObject(final StaticObject o, final int x, final int y) {
		super(o);
		setLocation(x, y);
	}
	public int getX() {return x;}
	public int getY() {return y;}
	@Override
	public void setLocation(final int x, final int y) {
		super.setLocation(x, y);
		this.x = x;
		this.y = y;
	}
}