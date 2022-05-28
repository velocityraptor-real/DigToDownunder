package net.velocityworks.dtdu.objects.living;

import static net.velocityworks.dtdu.Main.ticklist;
import static net.velocityworks.dtdu.util.Mathe.*;

import net.velocityworks.dtdu.objects.base.*;
import net.velocityworks.dtdu.objects.statico.type.LivingType;
import net.velocityworks.dtdu.util.Direction;
import net.velocityworks.dtdu.util.Logger;
import net.velocityworks.dtdu.world.Map;

public abstract class LivingObject extends TrackedObject {
	public final int id;
	public float health;
	public LivingObject(LivingType o) {
		super(o);
		health = getMaxHealth();
		id = ticklist.size();
		ticklist.add(this);
	}
	public LivingObject(LivingType o, int x, int y) {
		super(o, x, y);
		health = getMaxHealth();
		id = ticklist.size();
		ticklist.add(this);
	}
	public LivingObject(LivingType o, float health, int x, int y) {
		super(o, x, y);
		this.health = health;
		id = ticklist.size();
		ticklist.add(this);
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
	public abstract void tick();
	protected void death() {
		Map.remove(x, y);
		ticklist.remove(id);
		Logger.say(getName(), "AAAaaahhh...! X_X");
	}
	public void move(Direction direction) {
		if(direction != Direction.NONE) moveTo(x, y, x + getHorizontalM(direction), y + getVerticalM(direction));
	}
	public final float getMaxHealth() {return ((LivingType) staticPart).health;}
	@Override
	public void interaction(Generic o) {Logger.say(getName(), "Hallo!");}
}