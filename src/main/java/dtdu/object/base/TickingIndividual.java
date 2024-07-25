package main.java.dtdu.object.base;

import main.java.dtdu.engine.Tickable;

public abstract class TickingIndividual extends Individual implements Tickable {
	public TickingIndividual(float x, float y, byte[] data) {
		super(x, y, data);
		addToTickList();
	}
	@Override
	public void discard() {
		removeFromTickList();
		super.discard();
	}
}