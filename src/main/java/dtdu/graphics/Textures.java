package main.java.dtdu.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import main.java.dtdu.Main;

public class Textures {
	public static final Color blue_background = new Color(0x233770);
	public static final Color transparent_black = new Color(0, 0, 0, 100);
	public static final Color speechbox_blue = new Color(0x06184a);
	public static final Color gold = new Color(0xc8b83f);
	public static BufferedImage button_idle, button_hover, button_active, bar_background, vignette, inventory, handslot, highlight;
	public static BufferedImage coin;
	public static BufferedImage trashcan, chair_left, chair_right;
	public static BufferedImage[] font, pianist, player;
	static {
		try {
			//GUI
			button_idle = load("gui/button/Knopf1");
			button_active = load("gui/button/Knopf1-active");
			button_hover = load("gui/button/Knopf1-hover");
			bar_background = load("backgrounds/Bar hintergrund");
			vignette = load("gui/vignette");
			inventory = load("gui/inventory");
			handslot = load("gui/handslot");
			highlight = load("gui/highlight");
			//Font
			font = load(new String[] {
					"gui/pixelfont/0", "gui/pixelfont/1", "gui/pixelfont/2", "gui/pixelfont/3", "gui/pixelfont/4", "gui/pixelfont/5",
					"gui/pixelfont/6", "gui/pixelfont/7", "gui/pixelfont/8", "gui/pixelfont/9", "gui/pixelfont/eden_portal", "gui/pixelfont/."
			});
			//Persons
			pianist = load(new String[] {
				"objects/persons/klavierspieler/i", "objects/persons/klavierspieler/p1", "objects/persons/klavierspieler/p2", "objects/persons/klavierspieler/p3"	
			});
			//Objects
			trashcan = load("objects/general/trashcan");
			chair_left = load("objects/general/chair_left");
			chair_right = load("objects/general/chair_right");
			//Items
			coin = load("items/coin");
		} catch (IOException e) {Main.printError(e);}
	}
	public static void loadCharacterTextures() {
		try {
			player = load(new String[] {
					"character/goldgraeber/b", "character/goldgraeber/bw1", "character/goldgraeber/bw2", "character/goldgraeber/br1",
					"character/goldgraeber/br2", "character/goldgraeber/f", "character/goldgraeber/fw1", "character/goldgraeber/fw2",
					"character/goldgraeber/fr1", "character/goldgraeber/fr2", "character/goldgraeber/l", "character/goldgraeber/lw1",
					"character/goldgraeber/lw2", "character/goldgraeber/lr1", "character/goldgraeber/lr2", "character/goldgraeber/r",
					"character/goldgraeber/rw1", "character/goldgraeber/rw2", "character/goldgraeber/rr1", "character/goldgraeber/rr2",
					"character/goldgraeber/Tanzmooves/t0", "character/goldgraeber/Tanzmooves/t1", "character/goldgraeber/Tanzmooves/t2",
					"character/goldgraeber/Tanzmooves/t3", "character/goldgraeber/Tanzmooves/t4", "character/goldgraeber/Tanzmooves/t5",
					"character/goldgraeber/Tanzmooves/t6", "character/goldgraeber/Tanzmooves/t7", "character/goldgraeber/Tanzmooves/t8",
					"character/goldgraeber/Tanzmooves/t9", "character/goldgraeber/Tanzmooves/t10", "character/goldgraeber/Tanzmooves/t11",
					"character/goldgraeber/Tanzmooves/t12", "character/goldgraeber/Tanzmooves/t13", "character/goldgraeber/Tanzmooves/t14",
					"character/goldgraeber/Tanzmooves/t15", "character/goldgraeber/Tanzmooves/t16", "character/goldgraeber/Tanzmooves/t17",
					"character/goldgraeber/Tanzmooves/t18", "character/goldgraeber/Tanzmooves/t19", "character/goldgraeber/Tanzmooves/t20",
					"character/goldgraeber/Tanzmooves/t21", "character/goldgraeber/Tanzmooves/t22", "character/goldgraeber/Tanzmooves/t23",
					"character/goldgraeber/Tanzmooves/t24", "character/goldgraeber/Tanzmooves/t25",
					"character/goldgraeber/rs", "character/goldgraeber/ls"
			});
		} catch (IOException e) {Main.printError(e);}
	}
	private static BufferedImage[] load(String[] resourcePath) throws IOException {
		BufferedImage[] array = new BufferedImage[resourcePath.length];
		for(int i = 0; i < array.length; i++) array[i] = load(resourcePath[i]);
		return array;
	}
	private static BufferedImage load(String resourcePath) throws IOException {
//		System.out.println("Loading texture: " + resourcePath);
		return ImageIO.read(new File("./src/main/resources/assets/textures/" + resourcePath + ".png"));
	}
	public static BufferedImage font(char c) {
		return font[switch(c) {
		case '0' -> 0; case '1' -> 1; case '2' -> 2; case '3' -> 3; case '4' -> 4; case '5' -> 5; case '6' -> 6; case '7' -> 7; case '8' -> 8;
		case '9' -> 9; case '.' -> 11; default -> 10;
		}];
	}
	public static Font getFont(int width, int height) {
		height = height * 3 / 40;
		width = width >> 5;
		return new Font(Font.MONOSPACED, Font.BOLD, width < height ? width : height);
	}
}