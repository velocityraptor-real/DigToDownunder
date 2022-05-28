package net.velocityworks.dtdu.objects.statico;

import static java.lang.System.out;
import static net.velocityworks.dtdu.world.Registry.scanner;

import net.velocityworks.dtdu.objects.base.Generic;

public class StaticObject extends Generic {
	protected final String name;
	protected final char icon;
	public StaticObject(final String name, final char icon) {
		this.name = name;
		this.icon = icon;
	}
	@Override
	public void interaction(final Generic o) {
		out.println(name);
		scanner.nextLine();
	}
	@Override
	public char getIcon() {return icon;}
	@Override
	public String getName() {return name;}
}