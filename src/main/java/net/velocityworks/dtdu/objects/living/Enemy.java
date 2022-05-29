package net.velocityworks.dtdu.objects.living;

import static net.velocityworks.dtdu.world.Registry.player;
import static net.velocityworks.dtdu.world.Inventory.toolSlot;

import net.velocityworks.dtdu.objects.ai.TargetingPlayer;
import net.velocityworks.dtdu.objects.base.Generic;
import net.velocityworks.dtdu.objects.statico.type.EnemyType;

public class Enemy extends LivingObject implements TargetingPlayer {
	boolean aggressive = true;
	public Enemy(EnemyType o) {
		super(o);
	}
	public Enemy(EnemyType o, int x, int y) {
		super(o, x, y);
	}
	public Enemy(EnemyType o, boolean aggressive, int x, int y) {
		super(o, x, y);
		this.aggressive = aggressive;
	}
	public Enemy(EnemyType o, float health, int x, int y) {
		super(o, health, x, y);
	}
	public Enemy(EnemyType o, float health, boolean aggressive, int x,  int y) {
		super(o, health, x, y);
		this.aggressive = aggressive;
	}
	@Override
	public void interaction(Generic o) {
		if(o == player) {
			aggressive = true;
			toolSlot.use();
			damage(toolSlot.damage);
		}
	}
	public final float getBaseDamage() {return ((EnemyType) staticPart).damage;}
	@Override
	public void tick() {
		if(aggressive) {
			targetPlayer();
		}
	}
}