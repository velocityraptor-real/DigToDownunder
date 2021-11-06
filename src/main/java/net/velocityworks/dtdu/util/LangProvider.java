package net.velocityworks.dtdu.util;

import net.velocityworks.dtdu.world.World;

public class LangProvider {
	public static String getSceneName() {
		return switch(World.scene) {
		case 0 -> "Bar";
		case 1 -> "Schlafzimmer";
		default -> {
			Logger.errorEntry("Unknown Place: " + World.scene);
			yield "Unknown";}
		};
	}
	public static String getLocation() {
		return "Neuseeland";
	}
}