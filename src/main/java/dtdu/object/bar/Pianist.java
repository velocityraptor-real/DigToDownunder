package main.java.dtdu.object.bar;

import java.awt.image.BufferedImage;

import main.java.dtdu.engine.Ticker;
import main.java.dtdu.graphics.Language;
import main.java.dtdu.graphics.Textures;
import main.java.dtdu.graphics.gui.Dialogue;
import main.java.dtdu.object.Player;
import main.java.dtdu.object.base.*;
import main.java.dtdu.util.Direction;

public class Pianist extends TickingIndividual {
	BufferedImage image = Textures.pianist[0];
	int tickCount = 0;
	public Pianist(float x, float y, byte[] data) {
		super(x, y, data);
	}
	@Override
	public String getRegistryName() {
		return "pianist";
	}
	@Override
	public BufferedImage getImage() {
		return image;
	}
	@Override
	public void tick() {
		BufferedImage im;
		if(Dialogue.d == null) {
			if(image != (im = Textures.pianist[1 + (tickCount % 45) / 15])) {
				image = im;
				Ticker.shouldRedrawWorld = true;
			} tickCount++;
		} else {
			if(image != (im = Textures.pianist[0])) {
				image = im;
				Ticker.shouldRedrawWorld = true;
			}
		}
	}
	@Override
	public Direction interacted(GameObject from, Direction direction) {
		if(from instanceof Player) Dialogue.start(Textures.pianist[0], new String[] {Language.get("Hello!")}, new boolean[] {true});
		return Direction.NONE;
	}
}