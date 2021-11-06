package net.velocityworks.dtdu.world;

import java.util.Scanner;
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
}