package main.java.dtdu.object.base;

import java.awt.image.BufferedImage;

import main.java.dtdu.engine.Registry;
import main.java.dtdu.item.ItemCreator;
import main.java.dtdu.item.base.Item;
import main.java.dtdu.object.Player;
import main.java.dtdu.util.*;

public class DroppedItem extends Individual {
	Item i;
	public DroppedItem(float x, float y, byte[] data) {
		super(x, y, data);
	}
	public DroppedItem(float x, float y, Item i) {
		super(x, y, null);
		if(i == null) throw new IllegalArgumentException("Can't drop nothing");
		this.i = i;
	}
	@Override
	public String getRegistryName() {
		return "dropped_item";
	}
	@Override
	public BufferedImage getImage() {
		return i.getImage();
	}
	@Override
	public int getDataSize() {
		return 5;
	}
	@Override
	public byte[] saveTo(byte[] data) {
		DataWriter.write(Registry.indexOf(i.getRegistryName()), data, 0);
		data[4] = i.getAmount();
		return data;
	}
	@Override
	public void loadFrom(byte[] data) {
		int i = DataReader.readInt(data, 0);
		if(i != -1) this.i = ((ItemCreator) Registry.get(i)).create(data[4]);
		else throw new IllegalArgumentException("Tried to load non existant Item");
	}
	@Override
	public Direction interacted(GameObject from, Direction direction) {
		if(from instanceof Player p && (Math.abs(x - p.x) > getHalfWidth() + p.getHalfWidth() || Math.abs(y - p.y) > getHalfHeight() + p.getHalfHeight())) {
			i.addToInventory();
			discard();
		} return direction;
	}
	@Override
	public void discard() {
		safeDiscard();
	}
}