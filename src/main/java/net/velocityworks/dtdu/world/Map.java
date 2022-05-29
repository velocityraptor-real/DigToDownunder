package net.velocityworks.dtdu.world;

import static net.velocityworks.dtdu.Main.*;
import static net.velocityworks.dtdu.world.Scene.*;
import static net.velocityworks.dtdu.util.Direction.*;
import static net.velocityworks.dtdu.util.Logger.errorEntry;
import static net.velocityworks.dtdu.util.Mathe.*;
import static net.velocityworks.dtdu.world.Registry.*;
import static java.lang.System.out;

import java.util.*;

import net.velocityworks.dtdu.objects.*;
import net.velocityworks.dtdu.objects.base.*;
import net.velocityworks.dtdu.objects.living.Enemy;
import net.velocityworks.dtdu.objects.statico.*;
import net.velocityworks.dtdu.objects.statico.type.EnemyType;
import net.velocityworks.dtdu.objects.statico.type.HarvestType;
import net.velocityworks.dtdu.util.*;

public final class Map {
	private Map() {}
	public static void render() {
		out.println("Place: " + SCENE);
		out.println("Location: " + getLocation());
		for(int y = 0; y < MAP.length; y++) {
			for(int x = 0; x < MAP[0].length; x++) {
				char c = getIcon(x, y);
				out.print(c + " ");
			}
			out.println();
		}
	}
	private static String getLocation() {
		return switch(SCENE) {
		case HÖHLE -> "Underground";
		case MEXICO -> "Mexico";
		default -> "Neuseeland";
		};
	}
	public static void generate(Scene scene) {
		switch(scene) {
		case BAR -> genBar();
		case SCHLAFZIMMER -> genSchlafzimmer();
		case GARTEN -> genGarten();
		case HÖHLE -> genHöhle();
		case MEXICO -> genMexico();
		default -> endGame();
		}
	}
	public static char getIcon(final int x, final int y) {
		Generic o = MAP[y][x];
		return o == null ? ' ' : o.getIcon();
	}
	public static Generic get(final int x, final int y) {
		return MAP[y][x];
	}
	public static boolean hasSpace(final int x, final int y) {
		return get(x, y) == null;
	}
	public static boolean isInsideMap(final int x, final int y) {
		if(x < 0 || y < 0 || MAP.length <= y || MAP[y].length <= x) {
			errorEntry("Location " + x + " " + y + " is outside of map");
			return false;
		}
		return true;
	}
	public static boolean isInMap(final int x, final int y) {
		return x > -1 && y > -1 && y < MAP.length && x < MAP[y].length;
	}
	public static void create(final int width, final int length) {
		MAP = new Generic[length][width];
	}
	public static void remove(final int x, final int y) {
		if(isInsideMap(x, y)) MAP[y][x] = null;
	}
	public static final void set(Generic o, final int x, final int y) {
		MAP[y][x] = o;
	}
	private static final void genRoomWalls(StaticObject o) {
		int maxY = MAP.length - 1;
		for(int y = 1; y < maxY; y++) {
			set(o, 0, y);
			set(o, MAP[y].length - 1, y);
		}
		Arrays.fill(MAP[0], o);
		Arrays.fill(MAP[maxY], o);
	}
	private static void genBar() {
		Barkeeper barkeeper = new Barkeeper();
		Obfuscator barkeep = new Obfuscator(barkeeper, 'c');
		MAP = new Generic[][]{
			new Generic[14],
			new Generic[14],
			{null, null, chair, table, null, chair, table, table, chair, null, null, null, null, null},
			{null, null, null, table, null, chair, table, table, null, null, table, table, table, null},
			{null, null, null, null, null, null, null, null, null, barkeep, table, null, table, null},
			{null, null, null, null, null, null, null, null, null, barkeep, table, barkeeper, table, null},
			{null, null, null, null, null, null, null, null, null, barkeep, table, null, table, null},
			{null, null, null, table, null, chair, table, table, null, null, table, table, table, null},
			{null, null, chair, table, chair, null, table, table, chair, null, null, null, null, null},
			new Generic[14],
			new Generic[14]
		};
		genRoomWalls(wall);
		set(new SOWP("Pianist", 'p', "Hallo!"), 10, 1);
		new Harvestable(new HarvestType("Trashcan", 't', coin, 3), 12, 9);
		player.setLocation(5, 5);
	}
	private static void genSchlafzimmer() {
		MAP = new Generic[][]{
				new Generic[10],
				{null, table, null, bed, table, null, null, table, table, null},
				{null, table, null, null, null, null, null, table, table, null},
				new Generic[10],
				{null, null, table, table, chair, null, null, null, table, null},
				{null, chair, table, table, null, null, null, null, table, null},
				new Generic[10],
				new Generic[10]
		};
		new Pickaxe();
		set(table, 8, 6);
		genRoomWalls(wall);
		new Gate(door, 7, 7, GARTEN);
		if(PREVIOUS_SCENE == GARTEN) player.setLocation(7, 6);
		else player.setLocation(5, 3);
	}
	private static void genGarten() {
		create(18, 12);
		StaticObject house = new StaticObject("House", 'H');
		for(int y = 0; y < MAP.length; y++) for(int x = 0; x < MAP[y].length; x++) {
			if(x > 10 && y > 0 && y < 4) set(house, x, y);
			else if(y < 5 && x == 10) set(stoneWall, x, y);
		}
		new Harvestable(erdbeerbusch, 1, 1);
		new Harvestable(erdbeerbusch, 17, 11);
		new Harvestable(himbeerbusch, 1, 3);
		new Harvestable(himbeerbusch, 0, 8);
		new Harvestable(brombeerbusch, 9, 10);
		new Harvestable(brombeerbusch, 9, 11);
		new Harvestable(gold_ore, true, 2, 0);
		new Harvestable(gold_ore, true, 2, 6);
		new Harvestable(gold_ore, true, 17, 9);
		StaticObject tree = new StaticObject("Tree", 'T');
		set(tree, 3, 3);
		set(tree, 9, 4);
		set(tree, 1, 11);
		set(tree, 8, 6);
		new Harvestable(stone, true, 3, 0);
		StaticObject rock = new StaticObject("Rock", 'R');
		set(rock, 7, 4);
		set(rock, 0, 11);
		new Quest(new StaticObject("Sparrow", 'ß'), true, 6, 9);
		new LockedGate(new StaticObject("Grabstelle", 'x'), 4, 5, HÖHLE, pickaxe);
		new Gate(door, 14, 3, SCHLAFZIMMER);
		player.setLocation(14, 4);
	}
	private static void genHöhle() {
		Logger.say(player.getName(), "Eine Höhle! Interessant. Mal sehen was es hier zu alles zu sehen gibt...");
		Random random = new Random();
		EnemyType steingolem = new EnemyType("Steingolem", 'm', 40F, 2.5F);
		create(random.nextInt(9, 33), random.nextInt(9, 29));
		for(int y = 0; y < MAP.length; y++) for(int x = 0; x < MAP[0].length; x++) set(stoneWall, x, y);
		höhlenAusgang.setLocation(MAP[0].length - 2, MAP.length - 2);
		int x = 1, y = 1;
		Direction direction = RIGHT;
		caver:
		while(get(x, y) != höhlenAusgang) {
			if(random.nextInt(9) == 0) new Harvestable(stone, true, x, y);
			else if(random.nextInt(10) == 0) new Harvestable(gold_ore, true, x, y);
			else if(random.nextInt(25) == 0) new Enemy(steingolem, false, x, y);
			else remove(x, y);
			int cx = 2 * getHorizontalM(direction) + x, cy = 2 * getVerticalM(direction) + y;
			for(int tries = 8; direction == NONE || random.nextInt(5) == 0 || !isInMap(cx, cy) || hasSpace(cx, cy); tries--) {
				if(tries < 1) break caver;
				direction = Direction.values()[random.nextInt(4)];
				cx = 2 * getHorizontalM(direction) + x;
				cy = 2 * getVerticalM(direction) + y;
			}
			x += getHorizontalM(direction);
			y += getVerticalM(direction);
		}
		x = höhlenAusgang.getX();
		y = höhlenAusgang.getY() - 1;
		player.setLocation(1, 1);
		for(int tries = 255; !hasSpace(x, y); tries--) {
			if(tries < 1) {
				player.setLocation(höhlenAusgang.getX(), höhlenAusgang.getY() - 1);
				break;
			}
			remove(x, y);
			if(tries % 2 == 0 || y == 1 && x > 1) x--;
			else if(y > 1) y--;
		}
	}
	private static void genMexico() {
		
	}
}