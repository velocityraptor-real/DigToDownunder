package main.java.dtdu.world;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import main.java.dtdu.Main;
import main.java.dtdu.engine.*;
import main.java.dtdu.graphics.*;
import main.java.dtdu.graphics.screens.FadeScreen;
import main.java.dtdu.object.IndividualCreator;
import main.java.dtdu.object.base.*;
import main.java.dtdu.util.*;

import static main.java.dtdu.graphics.Screens.*;

public enum Scene {
	MAIN_MENU {
		@Override
		public void sceneTranstionTo(Scene to) {
			mainMenuScreen.toggleBack(false);
			mainMenuScreen.toggleConfirm(false);
			mainMenuScreen.toggleExit(false);
			mainMenuScreen.toggleLoadGame(false);
			mainMenuScreen.toggleName(false);
		}
		@Override
		public void sceneTransitionFrom(Scene from) {
			Sounds.mainTheme.setVolume(1F);
			if(from != BAR) Sounds.mainTheme.play();
			mainMenuScreen.toggleName(false);
			mainMenuScreen.toggleBack(false);
			mainMenuScreen.toggleConfirm(false);
			mainMenuScreen.toggleLoadGame(true);
			mainMenuScreen.toggleSelectLanguage(true);
			mainMenuScreen.toggleExit(true);
			super.sceneTransitionFrom(from);
			Save.save = null;
		}
		@Override
		public void loadGraphics() {
			inventoryScreen.setVisible(false);
			worldScreen.setVisible(false);
			mainMenuScreen.setVisible(true);
			vignette.setVisible(false);
		}
	},
	BAR {
		GameObject[][] staticMap = new GameObject[12][9];
		{
			staticMap[2][1] = staticMap[2][2] = staticMap[5][1] = staticMap[5][2] = staticMap[6][1] = staticMap[6][2]
				= staticMap[2][6] = staticMap[2][7] = staticMap[5][6] = staticMap[5][7] = staticMap[6][6] = staticMap[6][7]
				= staticMap[9][2] = staticMap[9][6] = staticMap[10][2] = staticMap[10][6] = staticMap[11][2] = staticMap[11][6]
				= (GameObject) Registry.get("object");
			staticMap[11][8] = (GameObject) Registry.get("trash_can");
			additionalDataLength = 1;
		}
		@Override
		public void sceneTransitionFrom(Scene from) {
			Sounds.mainTheme.setVolume(.5F);
			if(!Sounds.mainTheme.isPlaying()) Sounds.mainTheme.resume();
			super.sceneTransitionFrom(from);
		}
		@Override
		public BufferedImage getBackgroundImage() {
			return Textures.bar_background;
		}
		@Override
		public GameObject[][] getStaticMap() {
			return staticMap;
		}
		@Override
		public ArrayList<Individual> getDynamicMap() {
			ArrayList<Individual> map = new ArrayList<>();
			map.add(((IndividualCreator) Registry.get("pianist")).create(11F, 0.5F, null));
			IndividualCreator chairs = ((IndividualCreator) Registry.get("chair"));
			map.add(chairs.create(1.55F, 1.5F, new byte[] {1}));
			map.add(chairs.create(4.63F, 1.5F, new byte[] {1}));
			map.add(chairs.create(7.45F, 1.5F, new byte[] {0}));
			map.add(chairs.create(4.5F, 2.5F, new byte[] {1}));
			map.add(((IndividualCreator) Registry.get("player")).create(4F, 4F, null));
			map.add(chairs.create(4.5F, 6.5F, new byte[] {1}));
			map.add(chairs.create(7.45F, 7.47F, new byte[] {0}));
			map.add(chairs.create(1.55F, 7.5F, new byte[] {1}));
			map.add(chairs.create(3.48F, 7.5F, new byte[] {0}));
			return map;
		}
		@Override
		public boolean isFilledRender() {
			return true;
		}
		@Override
		public byte[] getAdditionalData() {
			return new byte[1];
		}
	},
	SCHLAFZIMMER,
	GARTEN,
	HÖHLE,
	MEXICO,
	FRANKREICH;
	private String staticPath, dynamicPath, dataPath;
	public int additionalDataLength = 0;
	public static void transitTo(Scene scene) {
		FadeScreen.ongoingTransition = true;
		fadescreen.setVisible(true);
		while(FadeScreen.darkness < 255) {
			FadeScreen.darkness += 15;
			fadescreen.draw(true);
			try {Thread.sleep(25);} catch (InterruptedException e) {}
			while(Ticker.gamePaused) Thread.onSpinWait();
		} Save.scene.sceneTranstionTo(scene);
		scene.sceneTransitionFrom(Save.scene);
		while(FadeScreen.darkness > 0) {
			FadeScreen.darkness -= 15;
			fadescreen.draw(true);
			try {Thread.sleep(25);} catch (InterruptedException e) {}
			while(Ticker.gamePaused) Thread.onSpinWait();
		} fadescreen.setVisible(false);
		FadeScreen.ongoingTransition = false;
	}
	public void sceneTranstionTo(Scene to) {
		
	}
	public void sceneTransitionFrom(Scene from) {
		Map.unload();
		Save.scene = this;
		Map.load();
		loadGraphics();
	}
	public void loadGraphics() {
		inventoryScreen.setVisible(false);
		worldScreen.setVisible(true);
		mainMenuScreen.setVisible(false);
		vignette.setVisible(false);
	}
	public GameObject[][] getStaticMap() {
		if(staticPath != null) {
			try(DataReader reader = DataReader.create(staticPath)) {
				int x = reader.readInt(), y = reader.readInt(), c;
				GameObject[][] map = new GameObject[x][y];
				for(x = 0; x < map.length; x++) for(y = 0; y < map[x].length; y++) {
					c = reader.readInt();
					if(c > -1 && c < Registry.REGISTRY.size()) map[x][y] = (GameObject) Registry.get(c);
				} return map;
			} catch (IOException e) {Main.printError(e);}
		} return null;
	}
	public ArrayList<Individual> getDynamicMap() {
		if(dynamicPath != null) {
			ArrayList<Individual> map = new ArrayList<>();
			try(DataReader reader = DataReader.create(dynamicPath)) {
				int c;
				float x, y;
				byte[] ch;
				IndividualCreator r;
				while(true) {
					x = reader.readFloat();
					y = reader.readFloat();
					r = (IndividualCreator) Registry.get(reader.readInt());
					c = reader.readInt();
					if(c == 0) ch = null;
					else reader.read(ch = new byte[c]);
					Mathe.binaryInsert(map, r.create(x, y, ch));
				}
			} catch(EndOfStreamException en) {} catch (IOException e) {Main.printError(e);}
			return map;
		} return null;
	}
	public byte[] getAdditionalData() {
		if(dataPath != null) {
			try(DataReader reader = DataReader.create(dataPath)) {
				return reader.readAllBytes();
			} catch (IOException e) {Main.printError(e);}
		} return null;
	}
	public BufferedImage getBackgroundImage() {
		return null;
	}
	public boolean isFilledRender() {
		return false;
	}
}