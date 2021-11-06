package net.velocityworks.dtdu;

import static java.lang.System.out;

import net.velocityworks.dtdu.world.SceneHandler;

public class Main {
	public static void main(String[] args) {
		out.println("Starting game...");
		while(true) {
			SceneHandler.runScene();
		}
	}
}