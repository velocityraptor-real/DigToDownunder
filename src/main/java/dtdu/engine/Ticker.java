package main.java.dtdu.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.dtdu.graphics.Screens;
import main.java.dtdu.object.base.Individual;
import main.java.dtdu.util.Mathe;
import main.java.dtdu.world.Save;

public class Ticker extends Thread {
	public static final Ticker ticker = new Ticker();
	public static final List<Tickable> ticklist = Collections.synchronizedList(new ArrayList<>());
	public static final List<Integer> sceduledRemoval = Collections.synchronizedList(new ArrayList<>());
	/**
	 * Use to Avoid ConcurrentModificationExceptions. Not necessary if dynamic map removal does not result in a ConcurrentModificationException;
	 */
	public static final List<Individual> sceduledMapRemoval = Collections.synchronizedList(new ArrayList<>());
	public static volatile boolean gamePaused = false;
	public static volatile boolean shouldRedrawWorld = false;
	public static volatile boolean update = false;
	@Override
	public void run() {
		while(true) {
			try {Thread.sleep(20);}
			catch (InterruptedException e) {}
			if(!gamePaused) {
//				System.out.println("Tick");
				synchronized(ticklist) {for(Tickable t : ticklist) t.tick();}
				synchronized(sceduledRemoval) {
					sceduledRemoval.sort(null);
					for(int i = sceduledRemoval.size() - 1, j; i > -1; i--) {
						j = sceduledRemoval.remove(i).intValue();
						if(j != -1 && j < ticklist.size()) ticklist.remove(j);
					}
				} synchronized(sceduledMapRemoval) {
					sceduledMapRemoval.sort(null);
					for(int i = sceduledMapRemoval.size() - 1, j; i > -1; i--) {
						j = Mathe.binarySearch(Save.map.dynamicMap, sceduledMapRemoval.remove(i));
						if(j != -1) Save.map.dynamicMap.remove(j);
					}
				} if(shouldRedrawWorld) Screens.worldScreen.draw(true);
				else if(update) Screens.render();
			}
		}
	}
}