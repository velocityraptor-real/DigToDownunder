package dtdu.graphics.screens;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import dtdu.graphics.Drawable;

public abstract class GameScreen extends JComponent implements Drawable {
	private static final long serialVersionUID = 6271214338668746454L;
	public BufferedImage image;
	boolean waitingForImage = false, visible = false;
	@Override
	public void setVisible(boolean aFlag) {
		visible = aFlag;
	}
	@Override
	public boolean isVisible() {
		return visible;
	}
	@Override
	public void paint(Graphics g) {
		if(visible) {
			int width = getParent().getWidth(), height = getParent().getHeight();
			if(getWidth() != width || getHeight() != height) updateDimensions(width, height);
			Drawable.super.paint(g);
		}
	}
	protected void updateDimensions(int width, int height) {
		setSize(width, height);
		draw(false);
	}
	@Override
	public Component self() {
		return this;
	}
	@Override
	public BufferedImage getImage() {
		return image;
	}
	@Override
	public Component[] getComponents() {
		return super.getComponents();
	}
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
		return waitingForImage = super.imageUpdate(img, infoflags, x, y, w, h);
	}
	@Override
	public boolean isWaitingForImage() {
		return waitingForImage;
	}
	@Override
	public void setWaitingForImage(boolean flag) {
		waitingForImage = flag;
	}
}