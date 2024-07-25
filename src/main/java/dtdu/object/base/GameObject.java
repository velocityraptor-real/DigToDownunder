package main.java.dtdu.object.base;

import java.awt.image.BufferedImage;

import main.java.dtdu.engine.Registry;
import main.java.dtdu.util.Direction;

public class GameObject implements Registry {
	/**
	 * Standard interface for object interactions.
	 * @param from the interacting object
	 * @param direction movement context
	 * @return the resulting movement direction after the interaction.
	 */
	public Direction interacted(GameObject from, Direction direction) {
		return Direction.NONE;
	}
	//Type
	public String getRegistryName() {
		return "object";
	}
	public BufferedImage getImage() {
		return null;
	}
	@Override
	public String toString() {
		return getRegistryName();
	}
}