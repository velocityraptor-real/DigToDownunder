package net.velocityworks.dtdu.world;

import java.util.Scanner;

import net.velocityworks.dtdu.items.ItemContainer;
import net.velocityworks.dtdu.items.base.*;
import net.velocityworks.dtdu.objects.*;
import net.velocityworks.dtdu.objects.living.Player;
import net.velocityworks.dtdu.objects.statico.*;
import net.velocityworks.dtdu.objects.statico.type.HarvestType;
import net.velocityworks.dtdu.objects.statico.type.ToolHarvest;

public final class Registry {
	private Registry() {}
	public static final Scanner scanner = new Scanner(System.in);
	
	public static final Player player = new Player();
	public static final HoehlenAusgang höhlenAusgang = new HoehlenAusgang();
	
	public static final Item
		coin = new Item("Coin", 'c'),
		stamperl = new Item("Stamperl", 's');
	
	public static final Food devCookie = new Food("DevCookie", '€', 25);
	
	public static final Tool pickaxe = new Tool("Pickaxe", 'p', 255, 3F);
	
	public static final StaticObject
		wall = new StaticObject("Wall", 'W'),
		table = new StaticObject("Table", 'T'),
		chair = new StaticObject("Chair", 'C'),
		bed = new StaticObject("Bed", 'B'),
		stoneWall = new StaticObject("Stone_Wall", 'S'),
		door = new StaticObject("Door", 'd');
	
	public static final HarvestType
		erdbeerbusch = new HarvestType("Erdbeerbusch", 'e', new Food("Erdbeere", 'e')),
		himbeerbusch = new HarvestType("Himbeerbusch", 'h', new Food("Himbeere", 'h', 1.5F)),
		brombeerbusch = new HarvestType("Brombeerbusch", 'b', new Food("Brombeere", 'b'));
	
	public static final ToolHarvest
		gold_ore = new ToolHarvest(pickaxe, "Gold ore", 'g', new Item("Gold", 'g')),
		stone = new ToolHarvest(pickaxe, "Stone", 's', new ItemContainer(null));
}