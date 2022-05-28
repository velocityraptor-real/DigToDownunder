package net.velocityworks.dtdu.objects.living;

import static java.lang.System.out;
import static net.velocityworks.dtdu.Main.*;
import static net.velocityworks.dtdu.util.Logger.readInputChar;
import static net.velocityworks.dtdu.util.Direction.*;
import static net.velocityworks.dtdu.util.Mathe.*;

import net.velocityworks.dtdu.Main;
import net.velocityworks.dtdu.objects.base.Generic;
import net.velocityworks.dtdu.objects.statico.StaticObject;
import net.velocityworks.dtdu.util.Direction;

public class Player extends LivingObject {
	private float armor;
	public Player() {
		super(new StaticObject("Character", '&'));
	}
	public void playerControl() {
		int mtX = x, mtY = y;
		Direction direction = switch(readInputChar(false, ' ')) {
		case 'w' -> UP;
		case 'a' -> LEFT;
		case 's' -> DOWN;
		case 'd' -> RIGHT;
		case '#' -> {
			Main.endGame();
			yield NONE;
		}
		case '?' -> {
			out.println("Listing actions:");
			out.println("wasd: Movement");
			out.println("#: Force end game");
			out.println("e: Open Inventory");
			yield NONE;
		}
		case 'e' -> {
			INVENTORY_TOGGLE = true;
			yield NONE;}
		default -> {
			out.println("Waiting...");
			yield NONE;}
		};
		mtX += getHorizontalM(direction);
		mtY += getVerticalM(direction);
		moveTo(x, y, mtX, mtY);
	}
	@Override
	public void damage(float amount) {
		amount -= armor;
		if(amount > 0F) super.damage(amount);
	}
	@Override
	protected void death() {Main.endGame();}
	@Override
	public void interaction(Generic o) {}
}
