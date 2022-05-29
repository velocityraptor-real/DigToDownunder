package net.velocityworks.dtdu.objects.base;

import static java.lang.System.out;
import static net.velocityworks.dtdu.world.Inventory.toolSlot;
import static net.velocityworks.dtdu.world.Registry.scanner;

import net.velocityworks.dtdu.items.base.Tool;
import net.velocityworks.dtdu.objects.statico.StaticObject;
import net.velocityworks.dtdu.world.Scene;

public class LockedGate extends Gate {
	private final Tool key;
	public LockedGate(final StaticObject o, final int x, final int y, Scene sceneGate, final Tool key) {
		super(o, x, y, sceneGate);
		this.key = key;
	}
	@Override
	public void interaction(Generic o) {
		if(toolSlot == key) super.interaction(o);
		else {
			out.println(getName() + " requires a " + key.name + " to use");
			scanner.nextLine();
		}
	}
}