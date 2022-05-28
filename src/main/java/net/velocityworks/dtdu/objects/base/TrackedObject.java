package net.velocityworks.dtdu.objects.base;

import net.velocityworks.dtdu.objects.statico.StaticObject;

public class TrackedObject extends Generic {
	public final StaticObject staticPart;
	protected int x;
	protected int y;
	public TrackedObject(final StaticObject o) {
		staticPart = o;
	}
	public TrackedObject(final StaticObject o, final int x, final int y) {
		staticPart = o;
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
	@Override
	public void interaction(Generic o) {staticPart.interaction(null);}
	@Override
	public char getIcon() {return staticPart.getIcon();}
	@Override
	public String getName() {return staticPart.getName();}
}