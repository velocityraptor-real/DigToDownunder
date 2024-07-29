package dtdu.object.base;

import dtdu.util.*;

public abstract class Living extends TickingIndividual {
	protected float health = getMaxHealth();
	protected float armor;
	public Living(float x, float y, byte[] data) {
		super(x, y, data);
	}
	/**
	 * Called when living object loses all of its health.
	 */
	public void kill() {
		discard();
	}
	public void hurt(float amount) {
		health -= amount < 0F ? amount : (armor > -10F ? amount / (1F + .1F * armor) : amount * .1F * armor);
		if(health < 0F) kill();
	}
	public void setHealth(float health) {
		if((this.health = health) < 0F) kill();
	}
	public float getHealth() {
		return health;
	}
	public abstract float getMaxHealth();
	public float getArmor() {
		return armor;
	}
	@Override
	public int getDataSize() {
		return 8;
	}
	@Override
	public void loadFrom(byte[] data) {
		health = DataReader.readFloat(data, 0);
		armor = DataReader.readFloat(data, 4);
	}
	@Override
	public byte[] saveTo(byte[] data) {
		DataWriter.write(health, data, 0);
		DataWriter.write(armor, data, 4);
		return data;
	}
}