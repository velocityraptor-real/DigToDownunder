package net.velocityworks.dtdu.objects.statico.type;

import net.velocityworks.dtdu.objects.statico.StaticObject;

public class LivingType extends StaticObject {
	public final float health;
	public LivingType(String name, char icon) {
		super(name, icon);
		health = 25F;
	}
	public LivingType(String name, char icon, float health) {
		super(name, icon);
		this.health = health;
	}
}