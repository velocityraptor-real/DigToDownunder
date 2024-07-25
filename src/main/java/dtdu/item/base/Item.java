package main.java.dtdu.item.base;

import java.awt.image.BufferedImage;

import main.java.dtdu.graphics.Screens;
import main.java.dtdu.graphics.gui.GButton;
import main.java.dtdu.object.base.DroppedItem;
import main.java.dtdu.world.Save;

public abstract class Item implements Comparable<Item> {
	byte amount;
	public Item() {amount = 1;}
	public Item(byte amount) {
		this.amount = amount;
	}
	public void setAmount(byte amount) {
		if(amount < 1) removeFromInventory();
		else this.amount = amount > getMaxStackSize() ? getMaxStackSize() : amount;
	}
	public byte getAmount() {
		return amount;
	}
	public void changeAmount(byte amount) {
		this.amount += amount;
		if(this.amount < 0) removeFromInventory();
		else if(this.amount > getMaxStackSize()) this.amount = getMaxStackSize();
	}
	public abstract BufferedImage getImage();
	public abstract String getRegistryName();
	public byte getMaxStackSize() {
		return 99;
	}
	public int getEquipmentSlot() {
		return -1;
	}
	/**
	 * Buttons for possible interactions you can have with the Item via Inventory.
	 * Drop and Equip/Unequip Buttons already provided automatically and should not be part of the array.
	 * @return non-null array of the Item's Inventory interaction Buttons.
	 */
	public GButton[] getButtons() {
		return new GButton[0];
	}
	public void drop() {
		Screens.inventoryScreen.remove(this);
		Save.map.addToWorld(new DroppedItem(Save.player.x, Save.player.y, this));
	}
	public int addToInventory() {
		int index = Screens.inventoryScreen.add(this);
		if(index == -1) Save.map.addToWorld(new DroppedItem(Save.player.x, Save.player.y, this));
		return index;
	}
	public void removeFromInventory() {
		Screens.inventoryScreen.remove(this);
	}
	public void equip() {
		Screens.inventoryScreen.equip(this);
	}
	public void unequip() {
		Screens.inventoryScreen.unequip(this);
	}
	/**
	 * This method provides Item stacking and merging capabilities.
	 * @param i the Item that tries to get stacked onto {@code this}
	 * @return whether the item gets fully absorbed into {@code this}
	 */
	public boolean recieve(Item i) {
		if(i.getRegistryName().equals(getRegistryName())) {
			int j = amount + i.amount;
			changeAmount(i.amount);
			if(amount < j) {
				i.setAmount((byte) (j - amount));
				return false;
			} return true;
		} return false;
	}
	@Override
	public int compareTo(Item o) {
		if(o == null) return -1;
		int c = getRegistryName().compareTo(o.getRegistryName());
		return c != 0 ? c : (amount > o.amount ? -1 : (amount < o.amount ? 1 : 0));
	}
}