package net.velocityworks.dtdu.objects.statico;

import net.velocityworks.dtdu.objects.base.Generic;
import net.velocityworks.dtdu.util.Logger;

//Static Object With Personality
public class SOWP extends StaticObject {
	private final String message;
	public SOWP(String name, char icon, String message) {
		super(name, icon);
		this.message = message;
	}
	@Override
	public void interaction(Generic o) {Logger.say(name, message);}
}