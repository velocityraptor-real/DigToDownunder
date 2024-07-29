package dtdu.graphics.gui;

import java.awt.image.BufferedImage;

import dtdu.engine.*;
import dtdu.graphics.Screens;

public class Dialogue implements Tickable {
	public static volatile Dialogue d;
	BufferedImage[][] image;
	String[] dialogue;
	boolean[] sides;
	int i = 0, j = 0;
	private Dialogue() {}
	public BufferedImage getImage() {
		if(image == null) return null;
		BufferedImage[] array = image[i % image.length];
		return array[j % array.length];
	}
	public static void start(BufferedImage image, String[] dialogue, boolean[] sides) {
		start(image == null ? null : new BufferedImage[][] {{image}}, dialogue, sides);
	}
	public static void start(BufferedImage[][] image, String[] dialogue, boolean[] sides) {
		new Thread(() -> {
			if(d == null && dialogue != null && dialogue.length > 0 && sides != null && dialogue.length == sides.length) {
				d = new Dialogue();
				d.image = image;
				d.dialogue = dialogue;
				d.sides = sides;
				Screens.worldScreen.hearText = null;
				Screens.worldScreen.speechText = null;
				d.addToTickList();
			} while(d != null) Thread.onSpinWait();
		}).start();
	}
	public void keySpacePressed() {
		String s = dialogue[i];
		if(j < s.length()) {
			j = s.length();
			if(sides[i]) Screens.worldScreen.hearText = s;
			else Screens.worldScreen.speechText = s;
		} else {
			i++;
			j = 0;
			Screens.worldScreen.hearText = null;
			Screens.worldScreen.speechText = null;
			if(i >= dialogue.length) {
				removeFromTickList();
				d = null;
			}
		} Ticker.shouldRedrawWorld = true;
	}
	@Override public void tick() {
		if(dialogue != null && i < dialogue.length) {
			String s = dialogue[i];
			if(j < s.length()) {
				if(sides[i]) Screens.worldScreen.hearText = s.substring(0, ++j);
				else Screens.worldScreen.speechText = s.substring(0, ++j);
				Ticker.shouldRedrawWorld = true;
			}
		}
	}
}