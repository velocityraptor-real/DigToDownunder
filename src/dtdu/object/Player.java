package dtdu.object;

import java.awt.image.BufferedImage;

import dtdu.engine.Ticker;
import dtdu.graphics.*;
import dtdu.object.base.*;
import dtdu.util.Direction;
import dtdu.world.Save;

public class Player extends Living {
	public int image = 5;
	public volatile boolean upKey = false, downKey = false, leftKey = false, rightKey = false;
	public boolean walkAnim = false;
	int tickCount = 0;
	@Override public float getMaxHealth() {return 100F;}
	public Player(float x, float y, byte[] data) {
		super(x, y, data);
		Save.player = this;
	}
	@Override
	public void discard() {
		super.discard();
		Save.player = null;
	}
	@Override
	public BufferedImage getImage() {
		return Textures.player[image];
	}
	@Override
	public String getRegistryName() {
		return "player";
	}
	@Override
	public void tick() {
		float dx, dy;
		if(Screens.shift) {
			dx = (rightKey ? .1F : 0F) - (leftKey ? .1F : 0F);
			dy = (downKey ? .1F : 0F) - (upKey ? .1F : 0F);
		} else {
			dx = (rightKey ? .04F : 0F) - (leftKey ? .04F : 0F);
			dy = (downKey ? .04F : 0F) - (upKey ? .04F : 0F);
		} if(dx != 0F || dy != 0F) {
			Direction d = checkCollisions(x + dx, y + dy);
			if(d != Direction.NONE) setPos(x + Math.abs(dx) * d.horizontalMultiplier, y + Math.abs(dy) * d.verticalMultiplier);
		} //Animations
		tickCount++;
		if(upKey || downKey || leftKey || rightKey) {
			int expected = 5;
			int shift = Screens.shift ? 2 : 0;
			boolean b = Screens.shift && tickCount % 10 > 5 || (!Screens.shift && tickCount % 20 > 10);
			if(leftKey && !rightKey) expected = (b ? 12 : 11) + shift;
			else if(rightKey && !leftKey) expected = (b ? 17 : 16) + shift;
			else if(upKey && !downKey) expected = (b ? 2 : 1) + shift;
			else if(downKey && !upKey) expected = (b ? 7 : 6) + shift;
			else expected = (tickCount % 650) / 25 + 20;
			if(image != expected) {
				walkAnim = true;
				image = expected;
				Ticker.shouldRedrawWorld = true;
			}
		} else if(walkAnim) {
			walkAnim = false;
			Ticker.shouldRedrawWorld = true;
			if(image > 10) {
				if(image < 15) {
					image = 10;
					return;
				} else if(image > 15 && image < 20) {
					image = 15;
					return;
				}
			} else if(image < 5 && image > 0) {
				image = 0;
				return;
			} image = 5;
		}
	}
}