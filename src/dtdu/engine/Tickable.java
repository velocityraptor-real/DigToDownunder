package dtdu.engine;

public interface Tickable {
	void tick();
	default void addToTickList() {Ticker.ticklist.add(this);}
	default void removeFromTickList() {synchronized(Ticker.ticklist) {Ticker.sceduledRemoval.add(Ticker.ticklist.indexOf(this));}}
}