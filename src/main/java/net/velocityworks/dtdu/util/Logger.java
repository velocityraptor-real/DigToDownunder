package net.velocityworks.dtdu.util;

import static java.lang.System.*;
import static net.velocityworks.dtdu.world.Registry.*;

public class Logger {
	public static boolean decision(String entry, boolean preference) {
		char c;
		if(preference) {
			out.println(entry + " [Y/n]");
			c = readInputChar(false, 'y');
		} else {
			out.println(entry + " [y/N]");
			c = readInputChar(false, 'n');
		}
		return c == 'y';
	}
	public static void assume(String assumption) {
		out.println("Assuming: " + assumption);
	}
	public static char readInputChar(final boolean caseSensitive, char assumption) {
		String s = readInput();
		if(s != null && !s.equals("")) {
			if(!caseSensitive) {
				s = s.toLowerCase();
			}
			return s.charAt(0);
		} else return assumption;
	}
	public static String readInput() {
		inputEntry();
		return scanner.nextLine();
	}
	public static void say(String name, String sentence) {
		out.println(name + ": " + sentence);
		scanner.nextLine();
	}
	public static void inputEntry() {
		out.print("<input>: ");
	}
	public static void logEntry(String sentence) {
		out.println(sentence);
		scanner.nextLine();
	}
	public static void debugEntry(String entry) {
		out.println("[DEBUG] " + entry);
		scanner.nextLine();
	}
	public static void errorEntry(String entry) {
		err.println("[ERROR] " + entry);
		scanner.nextLine();
	}
}