package main.java.dtdu.object.bar;

import java.awt.image.BufferedImage;

import main.java.dtdu.graphics.Textures;
import main.java.dtdu.object.Player;
import main.java.dtdu.object.base.*;
import main.java.dtdu.util.*;

public class Chair extends Individual {
	boolean side, sitting = false;
	public Chair(float x, float y, byte[] data) {
		super(x, y, data);
	}
	@Override
	public BufferedImage getImage() {
		return sitting ? null : side ? Textures.chair_right : Textures.chair_left;
	}
	@Override
	public String getRegistryName() {
		return "chair";
	}
	@Override
	public int getDataSize() {
		return 1;
	}
	@Override
	public void loadFrom(byte[] data) {
		side = DataReader.readBoolean(data[0]);
	}
	@Override
	public byte[] saveTo(byte[] data) {
		if(side) data[0] = 1;
		return data;
	}
	@Override
	public Direction interacted(GameObject from, Direction direction) {
		if(from instanceof Player p && (Math.abs(x - p.x) > getHalfWidth() + p.getHalfWidth() || Math.abs(y - p.y) > getHalfHeight() + p.getHalfHeight())) {
			p.upKey = p.downKey = p.leftKey = p.rightKey = p.walkAnim = false;
			p.image = side ? 46 : 47;
			sitting = true;
			p.setPos(x, y);
			return Direction.NONE;
		} sitting = false;
		return direction;
	}
}