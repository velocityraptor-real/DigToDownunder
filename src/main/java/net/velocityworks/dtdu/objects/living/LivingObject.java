package net.velocityworks.dtdu.objects.living;

import net.velocityworks.dtdu.objects.base.Generic;
import net.velocityworks.dtdu.objects.base.TrackedObject;
import net.velocityworks.dtdu.objects.statico.StaticObject;
import net.velocityworks.dtdu.util.Logger;
import net.velocityworks.dtdu.world.Map;

public class LivingObject extends TrackedObject {
	private final float maxHealth;
	public float health;
	public LivingObject(StaticObject o) {
		super(o);
		maxHealth = 25F;
		health = maxHealth;
	}
	public LivingObject(StaticObject o, int x, int y) {
		super(o, x, y);
		maxHealth = 25F;
		health = maxHealth;
	}
	public LivingObject(StaticObject o, int maxHealth, int x, int y) {
		super(o, x, y);
		this.maxHealth = maxHealth;
		health = maxHealth;
	}
	public void damage(float amount) {
		health -= amount;
		if(health < 0.1F) death();
	}
	protected void death() {Map.remove(x, y);}
	public final float getMaxHealth() {return maxHealth;}
	@Override
	public void interaction(Generic o) {Logger.say(getName(), "Hallo!");}
}