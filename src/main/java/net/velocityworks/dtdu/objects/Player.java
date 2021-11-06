package net.velocityworks.dtdu.objects;

import static java.lang.System.out;
import static net.velocityworks.dtdu.util.Logger.readInputChar;

import net.velocityworks.dtdu.objects.base.Living;
import net.velocityworks.dtdu.world.World;

public class Player extends Living {
	public Player() {
		super();
	}
	@Override
	protected void attributes() {
		this.icon = '&';
		this.name = "Character";
	}
	public void playerControl() {
		int mtX = x;
		int mtY = y;
		switch(readInputChar(false, ' ')) {
		case 'w' -> mtY--;
		case 'a' -> mtX--;
		case 's' -> mtY++;
		case 'd' -> mtX++;
		case 't' -> System.exit(0);
		case '?' -> help();
		case 'e' -> {
			World.inventoryToggle = true;
			return;}
		default -> {
			out.println("Waiting...");
			return;}
		}
		moveTo(x, y, mtX, mtY);
	}
	private void help() {
		out.println("Listing actions:");
		out.println("wasd: Movement");
		out.println("t: Force end game");
		out.println("e: Open Inventory");
	}
}
