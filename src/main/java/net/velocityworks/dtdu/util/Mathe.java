package net.velocityworks.dtdu.util;

import static net.velocityworks.dtdu.util.Direction.*;

public final class Mathe {
	private Mathe() {}
	public static int getVerticalM(Direction dir) {
		if(dir == DOWN) return 1;
		else if(dir == UP) return -1;
		else return 0;
	}
	public static int getHorizontalM(Direction dir) {
		if(dir == RIGHT) return 1;
		else if(dir == LEFT) return -1;
		else return 0;
	}
	public static boolean isNumeric(String s) {
		if(s == null || s.isEmpty()) return false;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(!Character.isDigit(c) && c != '-' && c != '.') return false;
		}
		return true;
	}
	public static int stringToInt(String s, final int basis) {
		int output = 0, i = 0;
		char first = s.charAt(0);
		if(first == '-') {
			i++;
		}
		for(; i < s.length(); i++) {
			output *= basis;
			output += Character.getNumericValue(s.charAt(i));
		}
		if(first == '-') {
			output *= -1;
		}
		return output;
	}
}