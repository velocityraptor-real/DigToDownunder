package dtdu.world;

import java.io.*;

import dtdu.Main;
import dtdu.graphics.Language;
import dtdu.graphics.Screens;
import dtdu.graphics.Textures;
import dtdu.object.Player;
import dtdu.util.*;

public class Save {
	public static volatile File save;
	public static volatile Scene scene = Scene.MAIN_MENU;
	public static volatile Player player;
	public static volatile Map map;
	public static volatile Language language;
	public static void newSave(File f) {
		f.mkdirs();
		Save.save = f;
		Textures.loadCharacterTextures();
		Scene.transitTo(Scene.BAR);
	}
	public static void loadSave(File f) {
		save = f;
		int i = 1;
		try(DataReader reader = DataReader.create(f.getAbsolutePath() + File.separator + "data.ddd")) {
			Textures.loadCharacterTextures();
			if(reader == null) Scene.transitTo(Scene.MAIN_MENU);
			else {
				i = reader.readInt();
				Screens.inventoryScreen.loadInventory(reader);
			}
		} catch(EndOfStreamException en) {} catch(IOException e) {Main.printError(e);}
		Textures.loadCharacterTextures();
		Scene.transitTo(Scene.values()[i]);
	}
	public static void save() {
		if(save != null) try(DataWriter writer = DataWriter.create(save.getAbsolutePath() + File.separator + "data.ddd")) {
			writer.write(scene.ordinal());
			Screens.inventoryScreen.saveInventory(writer);
		} catch (IOException e) {Main.printError(e);}
	}
}