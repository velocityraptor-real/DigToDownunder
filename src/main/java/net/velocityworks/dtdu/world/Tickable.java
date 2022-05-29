package net.velocityworks.dtdu.world;

import static net.velocityworks.dtdu.Main.ticklist;

public interface Tickable {
	void tick();
	/**
	 * @return the ID the tickable object NEEDS to save
	 */
	default int addToTickList() {
		int id = ticklist.size();
		ticklist.add(this);
		return id;
	}
	default void removeFromTickList(int id) {
		ticklist.remove(id);
	}
}