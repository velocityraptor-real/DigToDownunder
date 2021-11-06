package net.velocityworks.dtdu.world;

import static net.velocityworks.dtdu.util.Logger.errorEntry;
import static net.velocityworks.dtdu.world.Registry.*;
import static net.velocityworks.dtdu.world.World.*;

import java.util.Arrays;

import net.velocityworks.dtdu.items.base.Item;
import net.velocityworks.dtdu.objects.*;
import net.velocityworks.dtdu.objects.base.*;

public class Map {
	public static char getIcon(final int x, final int y) {
		return iconMap[y][x];
	}
	public static Generic get(final int x, final int y) {
		return map[y][x];
	}
	public static boolean hasSpace(final int x, final int y) {
		return get(x, y) == null;
	}
	public static boolean isInsideMap(final int x, final int y) {
		if(map.length <= y || map[0].length <= x || y < 0 || x < 0) {
			errorEntry("Location " + x + " " + y + " is outside of map");
			return false;
		}
		return true;
	}
	public static void create(final int width, final int length) {
		map = new Generic[length][width];
		iconMap = new char[length][width];
	}
	public static void fill(final Generic o, final int y) {
		fill(o, o.icon, y);
	}
	public static void fill(final Generic o, final char icon, final int y) {
		Arrays.fill(map[y], o);
		Arrays.fill(iconMap[y], icon);
	}
	public static void remove(final int x, final int y) {
		map[y][x] = null;
		iconMap[y][x] = ' ';
	}
	public static void set(final Generic o, final char icon, final int x, final int y) {
		map[y][x] = o;
		iconMap[y][x] = icon;
	}
	public static void set(final Generic o, final int x, final int y) {
		set(o, o.icon, x, y);
	}
	private static void genRoomWalls() {
		int maxY = map.length - 1;
		for(int y = 1; y < maxY; y++) {
			set(wall, 0, y);
			set(wall, map[y].length - 1, y);
		}
		fill(wall, 0);
		fill(wall, maxY);
	}
	public static void genBar() {
		create(14, 11);
		genRoomWalls();
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[0].length; x++) {
				if((y > 2 && y < 8 && (x == 10 || x == 12)) || ((x == 3 || x == 6 || x == 7) && (y == 2 || y == 3 || y == 7 || y == 8))) {
					set(table, x, y);
				} else if((y == 2 || y == 8) && (x == 2 || x == 8) || (x == 5 && (y == 2 || y == 3 || y == 7)) || (y == 8 && x == 4)) {
					set(chair, x, y);
				} else if(x == 9 && (y > 3 && y < 7)) {
					set(barkeeper, 'c', x, y);
				} else if(y == 1 && x == 10) {
					set(new Pianist(), x, y);
				} else if(y == 5 && x == 11) {
					set(barkeeper, x, y);
				} else if(y == 9 && x == 12) {
					set(new Harvestable(new Item(3, "Coin", 'c'), "Trashcan", 't'), x, y);
				}
			}
		}
		player.setLocation(5, 5);
	}
	public static void genSchlafzimmer() {
		create(10, 8);
		genRoomWalls();
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[0].length; x++) {
				if(((y == 1 || y == 2) && (x == 1 || (x == 4 && y != 2) || x == 7 || x == 8)) || ((y == 4 || y == 5) && (x == 2 || x == 3 || x == 8)) || (y == 6 && x == 8)) {
					set(table, x, y);
				} else if((y == 4 && x == 4) || (y == 5 && x == 1)) {
					set(chair, x, y);
				} else if(y == 1 && x == 3) {
					set(bed, x, y);
				} else if(y == 2 && x == 3) {
					set(new Pickaxe(), x, y);
				} else if(y == 7 && x == 7) {
					set(new Gate(-1), x, y);
				}
			}
		}
		player.setLocation(5, 3);
	}
	public static void genGarten() {
		create(18, 12);
		player.setLocation(4, 14);
	}
}