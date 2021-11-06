package net.velocityworks.dtdu.util;

public class Mathe {
	public static int stringToInt(String s) {
		return stringToInt(s, 10);
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