package net.velocityworks.dtdu.objects;

import net.velocityworks.dtdu.objects.base.Generic;
import net.velocityworks.dtdu.util.Logger;

public class Pianist extends Generic {
	public Pianist() {
		super();
	}
	@Override
	protected void attributes() {
		this.icon = 'p';
		this.name = "Pianist";
	}
	@Override
	public boolean interaction(final int x, final int y) {
		Logger.say(name, "Hallo!");
		return false;
	}
}