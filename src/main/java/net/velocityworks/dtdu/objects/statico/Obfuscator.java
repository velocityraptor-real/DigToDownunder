package net.velocityworks.dtdu.objects.statico;

import net.velocityworks.dtdu.objects.base.Generic;

public class Obfuscator extends StaticObject {
	private final Generic pass;
	public Obfuscator(Generic pass, char icon) {
		super("", icon);
		this.pass = pass;
	}
	@Override
	public void interaction(Generic o) {pass.interaction(o);}
}
