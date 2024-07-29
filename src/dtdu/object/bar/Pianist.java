package dtdu.object.bar;

import java.awt.image.BufferedImage;

import dtdu.engine.Ticker;
import dtdu.graphics.Language;
import dtdu.graphics.Textures;
import dtdu.graphics.gui.Dialogue;
import dtdu.object.Player;
import dtdu.object.base.*;
import dtdu.util.Direction;

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