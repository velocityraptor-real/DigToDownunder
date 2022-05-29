package net.velocityworks.dtdu.objects.base;

import net.velocityworks.dtdu.objects.statico.StaticObject;

public abstract class TypableObject extends Generic {
	public final StaticObject staticPart;
	public TypableObject(StaticObject o) {
		staticPart = o;
	}
	@Override
	public void interaction(Generic o) {staticPart.interaction(null);}
	@Override
	public char getIcon() {return staticPart.getIcon();}
	@Override
	public String getName() {return staticPart.getName();}
}