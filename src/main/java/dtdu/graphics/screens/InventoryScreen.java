package main.java.dtdu.graphics.screens;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import main.java.dtdu.engine.*;
import main.java.dtdu.graphics.*;
import main.java.dtdu.graphics.gui.GButton;
import main.java.dtdu.item.ItemCreator;
import main.java.dtdu.item.base.Item;
import main.java.dtdu.util.*;
import main.java.dtdu.world.Save;

public class InventoryScreen extends GameScreen {
	private static final long serialVersionUID = 3848921914036796868L;
	Item[] items = new Item[28];
	GButton equipButton, useButton;
	Font font;
	public volatile int hover = -1, selected = -1;
	public int getSlotForPos(int x, int y) {
		if(y > 49) {//1
			if(x < 62) {//2
				if(x < 34) {//3
					if(y < 74) {//4
						if(x < 21) {//5
							if(y < 60) {//6
								if(x > 10) return 4;//7
							} else if(x > 9 && y > 63) return 12;//8
						} else if(y < 59) {//6
							if(x > 23) return 5;//7
						} else if(y > 63 && x > 23) return 13;//8
					} else if(x < 20) {//5
						if(x > 9 && y < 89 && y > 78) return 20;//8
					} else if(y < 89 && x > 23 && y > 78) return 21;//8
				} else if(y < 74) {//4
					if(y < 59) {//5
						if(x < 48) {//6
							if(x > 37) return 6;//7
						} else if(x > 51) return 7;//7
					} else if(x < 48) {//6
						if(y > 63 && x > 37) return 14;//8
					} else if(y > 63 && x > 51) return 15;//8
				} else if(x < 48) {//5
					if(y < 89 && y > 78 && x > 37) return 22;//8
				} else if(y < 89 && x > 51 && y > 78) return 23;//8
			} else if(x < 90) {//3
				if(y < 74) {//4
					if(x < 76) {//5
						if(y < 59) {//6
							if(x > 65) return 8;//7
						} else if(y > 63 && x > 65) return 16;//8
					} else if(y < 59) {//6
						if(x > 79) return 9;//7
					} else if(y > 63 && x > 79) return 17;//8
				} else if(x < 76) {//5
					if(y < 89 && y > 78 && y > 65) return 24;//8
				} else if(y < 89 && y > 78 && x > 79) return 25;//8
			} else if(x > 107) {//4
				if(y < 74) {//5
					if(y > 63) {//6
						if(x < 118) return 19;//7
					} else if(x < 118 && y < 59) return 11;//8
				} else if(x < 118 && y < 89 && y > 78) return 27;//8
			} else if(y < 74) {//5
				if(y < 59) {//6
					if(x > 93 && x <104) return 10;//8
				} else if(y > 63 && x > 93 && x < 104) return 18;//9
			} else if(y < 89 && y > 78 && x > 93 && x < 104) return 26;//9
		} else if(y < 27) {//2
			if(y < 13) {//3
				if(x < 41 && x > 30 && y > 2) return 1;//6
			} else if(x < 41 && x > 30 && y > 16) return 2;//6
		} else if(x < 13) {//3
			if(y < 41 && y > 30 && x > 2) return 0;//6
		} else if(x < 41 && x > 30 && y < 41 && y > 30) return 3;//7
		return -1;
	}
	public void loadInventory(DataReader reader) throws IOException {
		int i, j;
		while(true) {
			i = reader.readInt();
			j = reader.readInt();
			items[i] = ((ItemCreator) Registry.get(j)).create(reader.readByte());
		}
	}
	public void saveInventory(DataWriter writer) throws IOException {
		Item it;
		for(int i = 0; i < 28; i++) if((it = items[i]) != null) {
			writer.write(i);
			writer.write(Registry.indexOf(it.getRegistryName()));
			writer.write(it.getAmount());
		}
	}
	public void toggle() {
		if(visible) {
			Ticker.gamePaused = false;
			setVisible(false);
			Screens.worldScreen.setVisible(true);
			Screens.render();
		} else {
			Ticker.gamePaused = true;
			setVisible(true);
			Screens.worldScreen.setVisible(false);
			draw(true);
		}
	}
	@Override
	public void draw(boolean render) {
		if(visible && getHeight() > 0 && getWidth() > 0) {
			image = new BufferedImage(Textures.inventory.getWidth(), Textures.inventory.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(Textures.inventory, 0, 0, null);
			g.drawImage(Save.player.getImage(), -13, -5, null);
			g.drawImage(Textures.handslot, 3, 30, null);
			tryDraw(g, 0, 3, 31);
			tryDraw(g, 1, 31, 3);
			tryDraw(g, 2, 31, 17);
			tryDraw(g, 3, 31, 31);
			if(drawRow(g, 49, 0) && drawRow(g, 64, 8) && drawRow(g, 79, 16));
			g.dispose();
			BufferedImage i = image;
			image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			g = image.createGraphics();
			g.drawImage(i, 0, 0, getWidth(), getHeight(), null);
			float horizontalScale = (float) getWidth() / (float) i.getWidth(), verticalScale = (float) getHeight() / (float) i.getHeight();
			g.setColor(Textures.gold);
			g.setFont(font);
			removeAll();
			Item it = selected == -1 ? null : items[selected];
			if(selected == -1 || it == null) {
				Screens.drawCenteredText(Language.get("gamepaused"), g, (int) (65 * horizontalScale), (int) (23 * verticalScale));
				g.drawString(Language.get("HP") + ": " + String.format("%.1f", Save.player.getHealth()), 88 * horizontalScale, 8 * verticalScale);
				g.drawString(Language.get("AP") + ": " + String.format("%.1f", Save.player.getArmor()), 88 * horizontalScale, 13 * verticalScale);
			} else {
				i = it.getImage();
				if(i != null) g.drawImage(i, (int) (48 * horizontalScale), (int)(6 * verticalScale), (int) (i.getWidth() * horizontalScale), (int) (i.getHeight() * verticalScale), null);
				int x = (int) (88 * horizontalScale), y = (int) (3.5F * horizontalScale), width = (int) (36 * horizontalScale), height = (int) (5 * horizontalScale);
				GButton ib = new GButton(Language.get("Drop"), () -> {it.drop(); selected = -1; InventoryScreen.this.draw(true);});
				add(ib);
				ib.setBounds(x, y, width, height);
				y += height;
				if(selected < 4) {
					add(ib = new GButton(Language.get("Unequip"), () -> {it.unequip(); selected = -1; InventoryScreen.this.draw(true);}));
					ib.setBounds(x, y, width, height);
					y += height;
				} else if(it.getEquipmentSlot() != -1) {
					add(ib = new GButton(Language.get("Equip"), () -> {it.equip(); selected = -1; InventoryScreen.this.draw(true);}));
					ib.setBounds(x, y, width, height);
					y += height;
				} for(GButton ibu : it.getButtons()) {
					add(ibu);
					ibu.setBounds(x, y, width, height);
					y += height;
				} y += height;
				g.drawString(Language.get("Type") + ": " + Language.get(it.getRegistryName()), x, y);
				y += height;
				g.drawString(Language.get("Amount") + ": " + it.getAmount(), x, y);
			} g.dispose();
		} super.draw(render);
	}
	private boolean drawRow(Graphics2D g, int y, int offset) {
		return tryDraw(g, 4 + offset, 10, y) && tryDraw(g, 5 + offset, 24, y) && tryDraw(g, 6 + offset, 38, y) && tryDraw(g, 7 + offset, 52, y)
			&& tryDraw(g, 8 + offset, 66, y) && tryDraw(g, 9 + offset, 80, y) && tryDraw(g, 10 + offset, 94, y) && tryDraw(g, 11 + offset, 108, y);
	}
	private boolean tryDraw(Graphics2D g, int slot, int x, int y) {
		if(slot != -1) {
			Item i = items[slot];
			if(i != null) {
				BufferedImage im = i.getImage();
				if(im != null) g.drawImage(im, x, y, 10, 10, null);
				if(slot == hover) g.drawImage(Textures.highlight, x, y, null);
				if(i.getAmount() > 9) {
					String s = String.valueOf(i.getAmount());
					g.drawImage(Textures.font(s.charAt(0)), x - 1, y + 3, null);
					g.drawImage(Textures.font(s.charAt(1)), x + 5, y + 3, null);
				} else if(i.getAmount() > 1) g.drawImage(Textures.font(String.valueOf(i.getAmount()).charAt(0)), x + 5, y + 3, null);
				return true;
			}
		} return false;
	}
	@Override
	protected void updateDimensions(int width, int height) {
		font = Textures.getFont(width, height);
		super.updateDimensions(width, height);
	}
	public Item take(Item i) {
		return take(i, 0);
	}
	private Item take(Item i, int offset) {
		Item it;
		for(; offset < 28; offset++) if((it = items[offset]) != null && it.getRegistryName().equals(i.getRegistryName())) {
			if(i.getAmount() > it.getAmount()) {
				byte rest = (byte) (i.getAmount() - it.getAmount());
				i.setAmount(rest);
				Item ite = take(i, offset + 1);
				i.changeAmount(it.getAmount());
				it.removeFromInventory();
				return ite == null ? null : i;
			} else {
				it.changeAmount((byte) -i.getAmount());
				return i;
			}
		} return null;
	}
	public void remove(Item i) {
		for(int j = 0; j < 28; j++) if(items[j] == i) {
			items[j++] = null;
			if(j > 3) while(j < 28) {
				i = items[j];
				if(i == null) return;
				items[j - 1] = i;
				items[j++] = null;
			} return;
		}
	}
	public int add(Item i) {
		Item it;
		for(int j = 4; j < 28; j++) {
			it = items[j];
			if(it == null) {
				items[j] = i;
				return j;
			} else if(it.recieve(i)) return j;
		} return -1;
	}
	public void equip(Item i) {
		int j = i.getEquipmentSlot();
		if(j > -1 && j < 4) {
			remove(i);
			Item it = items[j];
			items[j] = i;
			if(it != null) add(it);
		}
	}
	public void unequip(Item i) {
		int j = i.getEquipmentSlot();
		if(j > -1 && j < 4 && items[j] == i) {
			items[j] = null;
			add(i);
		}
	}
}