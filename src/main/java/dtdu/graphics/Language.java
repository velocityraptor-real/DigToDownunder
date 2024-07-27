package main.java.dtdu.graphics;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import main.java.dtdu.Main;
import main.java.dtdu.util.Mathe;
import main.java.dtdu.world.Save;

public class Language {
	public final char identifier;
	ArrayList<String> KEYS = new ArrayList<>(), VALUES = new ArrayList<>();
	private Language(char identifier) {
		this.identifier = identifier;
	}
	public static void loadLanguage(char identifier) {
		Language lang = new Language(identifier);
		try(FileReader reader = new FileReader(switch(identifier) {
		case 'd' -> new File("./src/main/resources/assets/lang/de.txt");
		case 'r' -> new File("./src/main/resources/assets/lang/ru.txt");
		case 'h' -> new File("./src/main/resources/assets/lang/hr.txt");
		case 'e' -> new File("./src/main/resources/assets/lang/es.txt");
		default -> new File("./src/main/resources/assets/lang/en.txt");//'#'
		}, StandardCharsets.UTF_8)) {
			int c = 0;
			while(c != -1) {
				StringBuilder key = new StringBuilder(), value = new StringBuilder();
				while((c = reader.read()) != ' ' && c != -1) key.append((char) c);
				while((c = reader.read()) != '\n' && c != -1) value.append((char) c);
				lang.VALUES.add(Mathe.binaryInsert(lang.KEYS, key.toString()), value.toString());
			}
		} catch(IOException e) {Main.printError(e);}
//		System.out.println("Constructed Language:");
//		for(int i = 0; i < lang.VALUES.size(); i++) System.out.println(lang.KEYS.get(i) + " -> " + lang.VALUES.get(i));
		Save.language = lang;
	}
	public static String get(String key) {
//		if(Save.language == null) Save.language = loadLanguage('#');
		int index = Mathe.binarySearch(Save.language.KEYS, key);
		if(index == -1) System.out.println("Warning: No translation for " + key);
		return index == -1 ? key : Save.language.VALUES.get(index);
	}
}