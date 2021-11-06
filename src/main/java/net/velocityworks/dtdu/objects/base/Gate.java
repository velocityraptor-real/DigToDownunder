package net.velocityworks.dtdu.objects.base;

import net.velocityworks.dtdu.world.World;

public class Gate extends Generic {
	protected int sceneGate;
	public Gate(final int sceneGate) {
		super();
		this.sceneGate = sceneGate;
	}
	@Override
	public void attributes() {
		this.icon = 'D';
		this.name = "Door";
	}
	@Override
	public boolean interaction(final int x, final int y) {
		World.sceneTransition = sceneGate;
		return false;
	}
}