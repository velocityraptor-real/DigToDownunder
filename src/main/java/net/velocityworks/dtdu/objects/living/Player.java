package net.velocityworks.dtdu.objects.living;

import static java.lang.System.out;
import static net.velocityworks.dtdu.Main.*;
import static net.velocityworks.dtdu.util.Logger.readInputChar;
import static net.velocityworks.dtdu.util.Direction.*;
import static net.velocityworks.dtdu.world.Inventory.*;

import net.velocityworks.dtdu.Main;
import net.velocityworks.dtdu.objects.base.Generic;
import net.velocityworks.dtdu.objects.statico.type.LivingType;

public final class Player extends LivingObject {
	public Player() {
		super(new LivingType("Character", '&'));
	}
	@Override
	public void tick() {
		move(switch(readInputChar(false, ' ')) {
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
			out.println("wasd: Movement (You are the '&' on screen)");
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
		});
	}
	@Override
	public void damage(float amount) {
		amount -= getArmor();
		if(amount > 0F) super.damage(amount);
		else super.damage(0.01F);
	}
	public float getArmor() {
		return armorSlot == null ? 0F : armorSlot.armor;
	}
	@Override
	protected void death() {Main.endGame();}
	@Override
	public void interaction(Generic o) {
		if(o instanceof Enemy) damage(((Enemy) o).getBaseDamage());
	}
}