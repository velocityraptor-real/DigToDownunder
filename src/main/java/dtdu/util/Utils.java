package main.java.dtdu.util;

import java.awt.Graphics2D;
import java.io.File;

import main.java.dtdu.graphics.Textures;

public class Utils {
	public static void delete(File f) {
		if(f.exists()) {
			if(f.isDirectory()) {
				File[] files = f.listFiles();
				for(File fi : files) delete(fi);
			} f.delete();
		}
	}
	public static void drawText(String text, Graphics2D graphics, int x, int y, int charWidth, int charHeight, int rowSize) {
		char c;
		for(int j = 0, min = x; j < text.length(); j++) {
			if(j % rowSize == 0) {
				x = min;
				y += charHeight;
			} switch(c = text.charAt(j)) {
			case '\n': x = min - charWidth; y += charHeight; break;
			case '\t': x += charWidth; break;
			case '\r': x = min - charWidth; break;
			case ' ': break;
			default: graphics.drawImage(Textures.font(c), x, y, charWidth, charHeight, null); break;
			} x += charWidth;
		}
	}
}