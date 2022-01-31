package net.velocityworks.dtdu.world;

import java.util.Scanner;

import net.velocityworks.dtdu.items.base.Item;
import net.velocityworks.dtdu.objects.*;
import net.velocityworks.dtdu.objects.base.*;

public class Registry {
	public static final Scanner scanner = new Scanner(System.in);
	public static final int sceneAmount = 3;
	
	//Objects
	public static final Player player = new Player();
	public static final Barkeeper barkeeper = new Barkeeper();
	
	public static final Generic wall = new Generic("Wall", 'W');
	public static final Generic table = new Generic("Table", 'T');
	public static final Generic chair = new Generic("Chair", 'C');
	public static final Generic bed = new Generic("Bed", 'B');
	public static final Generic stoneWall = new Generic("Stone_Wall", 'S');
	public static final Generic tree = new Generic("Tree", 'T');
	public static final Generic rock = new Generic("Rock", 'R');
	
	//Items
	public static final Item coin = new Item("Coin", 'c');
	public static final Item stamperl = new Item("Stamperl", 's');
	public static final Item pickaxe = new Item("Pickaxe", 'p');
	public static final Item erdbeere = new Item("Erdbeere", 'e');
}