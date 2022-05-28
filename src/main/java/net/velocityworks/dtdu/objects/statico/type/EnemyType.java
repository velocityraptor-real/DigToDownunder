package net.velocityworks.dtdu.objects.statico.type;

public class EnemyType extends LivingType {
	public final float damage;
	public EnemyType(String name, char icon) {
		super(name, icon);
		damage = 1F;
	}
	public EnemyType(String name, char icon, float health) {
		super(name, icon, health);
		damage = 1F;
	}
	public EnemyType(String name, char icon, float health, float damage) {
		super(name, icon, health);
		this.damage = damage;
	}
}