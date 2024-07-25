package main.java.dtdu.object.base;

import main.java.dtdu.engine.Ticker;
import main.java.dtdu.util.*;
import main.java.dtdu.world.Save;

public class Individual extends GameObject implements Comparable<Individual> {
	public float x, y;
	/**
	 * Standardized Individual constructor. WARNING: constructed object is not automatically added to the world! Call {@code Save.map.addToWorld(...)} to add the Individual to the currently loaded dynamic map (aka world).
	 * @param x position
	 * @param y position
	 * @param data for additional information. {@code loadFrom(data)} will not get called if {@code data} is {@code null}.
	 */
	public Individual(float x, float y, byte[] data) {
		this.x = x;
		this.y = y;
		if(data != null) loadFrom(data);
	}
	/**
	 * Removes the world references to this object. Call this method to get rid of the object.
	 */
	public void discard() {
		Mathe.binaryRemove(Save.map.dynamicMap, this);
		if(getImage() != null) Ticker.shouldRedrawWorld = true;
	}
	/**
	 * Use if {@code discard()} results in a {@code ConcurrentModificationException}.
	 */
	public final void safeDiscard() {
		Ticker.sceduledMapRemoval.add(this);
		if(getImage() != null) Ticker.shouldRedrawWorld = true;
	}
	//Type
	public float getSize() {
		return .8F;
	}
	/**
	 * Extra size method for math convenience. Recommend overriding
	 * @return half of the Individual's size.
	 */
	public float getHalfSize() {
		return .4F;
	}
	public float getWidth() {
		return getSize();
	}
	public float getHeight() {
		return getSize();
	}
	/**
	 * Extra size method for math convenience. Recommend overriding
	 * @return half of the Individual's width.
	 */
	public float getHalfWidth() {
		return getHalfSize();
	}
	/**
	 * Extra size method for math convenience. Recommend overriding
	 * @return half of the Individual's Height.
	 */
	public float getHalfHeight() {
		return getHalfSize();
	}
	@Override
	public String getRegistryName() {
		return "individual";
	}
	/**
	 * Load from additional data. This method will not get called if the constructor does not receive a data array.
	 * @param data array with additional information for loading.
	 */
	public void loadFrom(byte[] data) {
		
	}
	/**
	 * Gets the initial length for the data array. If returned length is {@value 0}, {@code saveTo(data)} will not get called.
	 * @return length for the construction of the data array.
	 */
	public int getDataSize() {
		return 0;
	}
	/**
	 * Method to store additional data. coordinates are already stored by the map and do not need to get stored in the array
	 * @param data array to store additional information in
	 * @return the data array now with the additional information stored. may be a new array of different size if necessary. may be null
	 */
	public byte[] saveTo(byte[] data) {
		return data;
	}
	/**
	 * Checks wether the object is situated at the specifiec location. acts as a basic collision / interaction check
	 * @param x coordinate to check
	 * @param y coordinate to check
	 * @return if this space is occupied by the individual
	 */
	public boolean occupies(float x, float y) {
		return Math.abs(this.x - x) <= getHalfWidth() && Math.abs(this.y - y) <= getHalfHeight();
	}
	public boolean collidesWith(Individual i) {
		return Math.abs(x - i.x) <= getHalfWidth() + i.getHalfWidth() || Math.abs(y - i.y) <= getHalfHeight() + i.getHalfHeight();
	}
	public Direction checkCollisions(float x, float y) {
		GameObject o;
		Direction dir = Direction.fromDXY(x - this.x, y - this.y);
		int xmin, xmax = (int) (x + getHalfWidth()) + 1, ymin, ymax = (int) (y + getHalfHeight()) + 1, j;
		{
			float xminf = x - getHalfWidth(), yminf = y - getHalfHeight();
			if(xminf < 0F) {
				dir = Direction.block(dir, Direction.RIGHT);
				xmin = 0;
			} else xmin = (int) xminf;
			if(yminf < 0F) {
				dir = Direction.block(dir, Direction.DOWN);
				ymin = 0;
			} else ymin = (int) yminf;
		} if(xmax > Save.map.staticMap.length) {
			dir = Direction.block(dir, Direction.LEFT);
			xmax = Save.map.staticMap.length;
		} if(ymax > Save.map.staticMap[0].length) {
			dir = Direction.block(dir, Direction.UP);
			ymax = Save.map.staticMap[0].length;
		} while(xmin < xmax) {
			j = ymin;
			while(j < ymax) {
				o = Save.map.staticMap[xmin][j];
				if(o != null) dir = o.interacted(this, dir);
				j++;
			} xmin++;
		} if(dir != Direction.NONE) synchronized(Save.map.dynamicMap) {for(Individual i : Save.map.dynamicMap) if(i != this && (Math.abs(x - i.x) <= getHalfWidth() + i.getHalfWidth() && Math.abs(y - i.y) <= getHalfHeight() + i.getHalfHeight())) {
			Direction d = dir;
			dir = i.interacted(this, dir);
			if(dir != d) return dir;
		}} return dir;
	}
	public void setPos(float x, float y) {
		Mathe.binaryRemove(Save.map.dynamicMap, this);
		this.x = x;
		this.y = y;
		Mathe.binaryInsert(Save.map.dynamicMap, this);
		if(getImage() != null) Ticker.shouldRedrawWorld = true;
	}
	@Override
	public int compareTo(Individual o) {
		return o == null || y > o.y || (y == o.y && x > o.x) ? 1 : (x == o.x && y == o.y ? (o == this ? 0 : -1) : -1);
	}
	@Override
	public String toString() {
		return getRegistryName() + " at [" + x + "|" + y + "]";
	}
}