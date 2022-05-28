package net.velocityworks.dtdu.objects.base;

import static net.velocityworks.dtdu.Main.*;

import net.velocityworks.dtdu.objects.statico.StaticObject;
import net.velocityworks.dtdu.world.Scene;

public class Gate extends TrackedObject {
	protected final Scene sceneGate;
	public Gate(final StaticObject o, final int x, final int y, Scene sceneGate) {
		super(o, x, y);
		this.sceneGate = sceneGate;
	}
	@Override
	public void interaction(Generic o) {
		SCENE_TRANSITION = sceneGate;
	}
}