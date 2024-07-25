package main.java.dtdu.graphics;

import java.awt.Color;
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
	public static BufferedImage trashcan;
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
					"gui/pixelfont/6", "gui/pixelfont/7", "gui/pixelfont/8", "gui/pixelfont/9", "gui/pixelfont/eden_portal", "gui/pixelfont/.",
					"gui/pixelfont/doppelpunkt", "gui/pixelfont/fragezeichen", "gui/pixelfont/!", "gui/pixelfont/lowercase/a", "gui/pixelfont/lowercase/ä",
					"gui/pixelfont/lowercase/b", "gui/pixelfont/lowercase/c", "gui/pixelfont/lowercase/d", "gui/pixelfont/lowercase/e",
					"gui/pixelfont/lowercase/f", "gui/pixelfont/lowercase/g", "gui/pixelfont/lowercase/h", "gui/pixelfont/lowercase/i",
					"gui/pixelfont/lowercase/j", "gui/pixelfont/lowercase/k", "gui/pixelfont/lowercase/l", "gui/pixelfont/lowercase/m",
					"gui/pixelfont/lowercase/n", "gui/pixelfont/lowercase/o", "gui/pixelfont/lowercase/ö", "gui/pixelfont/lowercase/p",
					"gui/pixelfont/lowercase/q", "gui/pixelfont/lowercase/r", "gui/pixelfont/lowercase/s", "gui/pixelfont/lowercase/ß",
					"gui/pixelfont/lowercase/t", "gui/pixelfont/lowercase/u", "gui/pixelfont/lowercase/ü", "gui/pixelfont/lowercase/v",
					"gui/pixelfont/lowercase/w", "gui/pixelfont/lowercase/x", "gui/pixelfont/lowercase/y", "gui/pixelfont/lowercase/z",
					"gui/pixelfont/uppercase/a", "gui/pixelfont/uppercase/ä", "gui/pixelfont/uppercase/b", "gui/pixelfont/uppercase/c",
					"gui/pixelfont/uppercase/d", "gui/pixelfont/uppercase/e", "gui/pixelfont/uppercase/f", "gui/pixelfont/uppercase/g",
					"gui/pixelfont/uppercase/h", "gui/pixelfont/uppercase/i", "gui/pixelfont/uppercase/j", "gui/pixelfont/uppercase/k",
					"gui/pixelfont/uppercase/l", "gui/pixelfont/uppercase/m", "gui/pixelfont/uppercase/n", "gui/pixelfont/uppercase/o",
					"gui/pixelfont/uppercase/ö", "gui/pixelfont/uppercase/p", "gui/pixelfont/uppercase/q", "gui/pixelfont/uppercase/r",
					"gui/pixelfont/uppercase/s", "gui/pixelfont/uppercase/t", "gui/pixelfont/uppercase/u", "gui/pixelfont/uppercase/ü",
					"gui/pixelfont/uppercase/v", "gui/pixelfont/uppercase/w", "gui/pixelfont/uppercase/x", "gui/pixelfont/uppercase/y",
					"gui/pixelfont/uppercase/z"
			});
			//Persons
			pianist = load(new String[] {
				"objects/persons/klavierspieler/i", "objects/persons/klavierspieler/p1", "objects/persons/klavierspieler/p2", "objects/persons/klavierspieler/p3"	
			});
			//Objects
			trashcan = load("objects/general/trashcan");
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
					"character/goldgraeber/Tanzmooves/t24", "character/goldgraeber/Tanzmooves/t25"
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
		case '9' -> 9; case '.' -> 11; case ':' -> 12; case '?' -> 13; case '!' -> 14; case 'a' -> 15; case 'ä' -> 16; case 'b' -> 17; case 'c' -> 18;
		case 'd' -> 19; case 'e' -> 20; case 'f' -> 21; case 'g' -> 22; case 'h' -> 23; case 'i' -> 24; case 'j' -> 25; case 'k' -> 26; case 'l' -> 27;
		case 'm' -> 28; case 'n' -> 29; case 'o' -> 30; case 'ö' -> 31; case 'p' -> 32; case 'q' -> 33; case 'r' -> 34; case 's' -> 35; case 'ß' -> 36;
		case 't' -> 37; case 'u' -> 38; case 'ü' -> 39; case 'v' -> 40; case 'w' -> 41; case 'x' -> 42; case 'y' -> 43; case 'z' -> 44; case 'A' -> 45;
		case 'Ä' -> 46; case 'B' -> 47; case 'C' -> 48; case 'D' -> 49; case 'E' -> 50; case 'F' -> 51; case 'G' -> 52; case 'H' -> 53; case 'I' -> 54;
		case 'J' -> 55; case 'K' -> 56; case 'L' -> 57; case 'M' -> 58; case 'N' -> 59; case 'O' -> 60; case 'Ö' -> 61; case 'P' -> 62; case 'Q' -> 63;
		case 'R' -> 64; case 'S' -> 65; case 'T' -> 66; case 'U' -> 67; case 'Ü' -> 68; case 'V' -> 69; case 'W' -> 70; case 'X' -> 71; case 'Y' -> 72;
		case 'Z' -> 73;
		default -> 10;
		}];
	}
}