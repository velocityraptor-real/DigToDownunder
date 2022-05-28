package net.velocityworks.dtdu.world;

import java.util.Scanner;

import net.velocityworks.dtdu.items.base.Item;
import net.velocityworks.dtdu.objects.*;
import net.velocityworks.dtdu.objects.living.Player;
import net.velocityworks.dtdu.objects.statico.*;

public class Registry {
	public static final Scanner scanner = new Scanner(System.in);
	
	public static final Player player = new Player();
	public static final HoelenAusgang hölenAusgang = new HoelenAusgang();
	
	public static final Item
		coin = new Item("Coin", 'c'),
		stamperl = new Item("Stamperl", 's'),
		pickaxe = new Item("Pickaxe", 'p');
	
	public static final StaticObject
		wall = new StaticObject("Wall", 'W'),
		table = new StaticObject("Table", 'T'),
		chair = new StaticObject("Chair", 'C'),
		bed = new StaticObject("Bed", 'B'),
		stoneWall = new StaticObject("Stone_Wall", 'S'),
		tree = new StaticObject("Tree", 'T'),
		rock = new StaticObject("Rock", 'R'),
		door = new StaticObject("Door", 'd'),
		house = new StaticObject("House", 'H');
	
	public static final Harvest
		erdbeerbusch = new Harvest("Erdbeerbusch", 'e', new Item("Erdbeere", 'e')),
		himbeerbusch = new Harvest("Himbeerbusch", 'h', new Item("Himbeere", 'h')),
		brombeerbusch = new Harvest("Brombeerbusch", 'b', new Item("Brombeere", 'b'));
	
	public static final ToolHarvest
		gold_ore = new ToolHarvest(pickaxe, "Gold ore", 'g', new Item("Gold", 'g')),
		stone = new ToolHarvest(pickaxe, "Stone", 's', null);
}