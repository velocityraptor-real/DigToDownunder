package main.java.dtdu.world;

import java.io.*;
import java.util.*;

import main.java.dtdu.Main;
import main.java.dtdu.engine.Registry;
import main.java.dtdu.engine.Ticker;
import main.java.dtdu.object.IndividualCreator;
import main.java.dtdu.object.base.*;
import main.java.dtdu.util.*;

import static main.java.dtdu.world.Save.*;

public class Map {
	public static void load() {
		map = new Map();
	}
	public static void unload() {
		if(map != null) map.ul();
	}
	public GameObject[][] staticMap;
	public final List<Individual> dynamicMap = Collections.synchronizedList(new ArrayList<>());
	public byte[] additionalData;
	public void addToWorld(String registryName, float x, float y, byte[] data) {
		addToWorld(((IndividualCreator) Registry.get(registryName)).create(x, y, data));
	}
	public void addToWorld(int registryIndex, float x, float y, byte[] data) {
		addToWorld(((IndividualCreator) Registry.get(registryIndex)).create(x, y, data));
	}
	public void addToWorld(Individual i) {
		Mathe.binaryInsert(dynamicMap, i);
		if(i.getImage() != null) Ticker.shouldRedrawWorld = true;
	}
	private Map() {
		staticMap = scene.getStaticMap();
		if(staticMap != null && new File(save.getAbsolutePath() + File.separator + scene.toString()).exists()) {
			try(DataReader reader = DataReader.create(save.getAbsolutePath() + File.separator + scene.toString() + File.separator + "static.ddd")){
				int x, y;
				while(true) {
					x = reader.readInt();
					y = reader.readInt();
					staticMap[x][y] = (GameObject) Registry.get(reader.readInt());
				}
			} catch(EndOfStreamException en) {} catch (IOException e) {Main.printError(e);}
			try(DataReader reader = DataReader.create(save.getAbsolutePath() + File.separator + scene.toString() + File.separator + "dynamic.ddd")) {
				int i;
				float x, y;
				byte[] data;
				IndividualCreator r;
				while(true) {
					x = reader.readFloat();
					y = reader.readFloat();
					r = (IndividualCreator) Registry.REGISTRY.get(reader.readInt());
					i = reader.readInt();
					data = i == 0 ? null : reader.readNBytes(i);
					Mathe.binaryInsert(dynamicMap, r.create(x, y, data));
				}
			} catch(EndOfStreamException en) {} catch (IOException e) {Main.printError(e);}
			if(scene.additionalDataLength > 0) try(DataReader reader = DataReader.create(save.getAbsolutePath() + File.separator + scene.toString() + File.separator + "additional.ddd")) {
				additionalData = reader.readAllBytes();
			} catch (IOException e) {Main.printError(e);}
		} else {
			ArrayList<Individual> list = scene.getDynamicMap();
			if(list != null) dynamicMap.addAll(list);
			additionalData = scene.getAdditionalData();
		}
	}
	private void ul() {
		if(staticMap != null) {
			try(DataWriter writer = DataWriter.create(save.getAbsolutePath() + File.separator + scene.toString() + File.separator + "static.ddd")) {
				GameObject[][] compare = scene.getStaticMap();
				for(int x = 0; x < compare.length; x++) for(int y = 0;  y < compare[x].length; y++) {
					GameObject o = staticMap[x][y];
					if(o != compare[x][y]) {
						writer.write(x);
						writer.write(y);
						writer.write(Registry.indexOf(o.getRegistryName()));
					}
				}
			} catch (IOException e) {Main.printError(e);}
			try(DataWriter writer = DataWriter.create(save.getAbsolutePath() + File.separator + scene.toString() + File.separator + "dynamic.ddd")) {
				byte[] data;
				int s;
				Individual[] dynMap;
				synchronized(dynamicMap) {dynMap = dynamicMap.toArray(new Individual[dynamicMap.size()]);}
				for(Individual i : dynMap) {
					s = Registry.indexOf(i.getRegistryName());
					if(s != -1) {
//						System.out.println("Saving " + i.getRegistryName());
						writer.write(i.x);
						writer.write(i.y);
						writer.write(s);
						s = i.getDataSize();
						if(s == 0) writer.write((int) 0);
						else {
							data = i.saveTo(new byte[s]);
							writer.write(data.length);
							writer.write(data);
						}
					} i.discard();
				}
			} catch (IOException e) {Main.printError(e);}
			if(additionalData != null) try(DataWriter additional = DataWriter.create(save.getAbsolutePath() + File.separator + scene.toString() + File.separator + "additional.ddd")) {
				additional.write(additionalData);
			} catch (IOException e) {Main.printError(e);}
		}
	}
}