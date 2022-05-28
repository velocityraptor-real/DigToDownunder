package net.velocityworks.dtdu;

import static java.lang.System.out;

import java.util.ArrayList;

import net.velocityworks.dtdu.objects.base.Generic;
import net.velocityworks.dtdu.objects.living.LivingObject;
//import net.velocityworks.dtdu.util.*;
import net.velocityworks.dtdu.world.*;

public final class Main {
	private Main() {}
	public static boolean INVENTORY_TOGGLE = false;
	public static Scene
		SCENE = Scene.BAR,
		SCENE_TRANSITION = SCENE,
		PREVIOUS_SCENE = Scene.BAR;
	public static Generic MAP[][];
	public static ArrayList<LivingObject> ticklist = new ArrayList<LivingObject>();
	
	public static void main(String ... args) {
		//TODO: Saving and loading
//		String location;
//		if(Logger.decision("Welcome to DTDU\nLoad a save?", false)) {
//			out.println("Enter save file location");
//			location = Logger.readInput();
//			if(location.substring(location.lastIndexOf('.')) == ".dtdu") load(location);
//			else Logger.errorEntry("invalid file extension");
//		} else {
//			out.println("Creating new game...");
//			out.println("Enter location + name for the new save");
//			location = Logger.readInput();
//			create(location + ".dtdu");
//		}
		out.println("Starting game...");
		//Game
		while(true) {
			Map.generate(SCENE);
			while(true) {
				if(SCENE_TRANSITION != SCENE) {
					PREVIOUS_SCENE = SCENE;
					SCENE = SCENE_TRANSITION;
					break;
				}
				if(INVENTORY_TOGGLE) Inventory.run();
				else {
					Map.render();
					for(int i = 0; i < ticklist.size(); i++) ticklist.get(i).tick();
				}
			}
		}
	}
	public static void endGame() {
		out.println("Game Over");
		System.exit(0);
	}
	static void create(String location) {
		//TODO: creating a new save
	}
	static void load(String location) {
		//TODO: loading a new save
	}
}