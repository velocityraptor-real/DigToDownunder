package net.velocityworks.dtdu.objects.living;

import net.velocityworks.dtdu.objects.base.*;
import net.velocityworks.dtdu.objects.statico.type.LivingType;
import net.velocityworks.dtdu.util.Logger;
import net.velocityworks.dtdu.world.Map;
import net.velocityworks.dtdu.world.Tickable;

public abstract class LivingObject extends TrackedObject implements Tickable {
	public final int id;
	public float health;
	public LivingObject(LivingType o) {
		super(o);
		health = getMaxHealth();
		id = addToTickList();
	}
	public LivingObject(LivingType o, int x, int y) {
		super(o, x, y);
		health = getMaxHealth();
		id = addToTickList();
	}
	public LivingObject(LivingType o, float health, int x, int y) {
		super(o, x, y);
		this.health = health;
		id = addToTickList();
	}
	public void damage(float amount) {
		health -= amount;
		if(health < 0.1F) death();
		Logger.say(getName(), "Autsch");
	}
	public void heal(float amount) {
		health += amount;
		if(health > getMaxHealth()) health = getMaxHealth();
	}
	protected void death() {
		deathMessage();
		Map.remove(x, y);
		removeFromTickList(id);
	}
	protected void deathMessage() {
		Logger.say(getName(), "AAAaaahhh...! X_X");
	}
	public final float getMaxHealth() {return ((LivingType) staticPart).health;}
	@Override
	public void interaction(Generic o) {Logger.say(getName(), "Hallo!");}
}