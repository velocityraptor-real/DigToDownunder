package main.java.dtdu.graphics.screens;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FadeScreen extends GameScreen {
	private static final long serialVersionUID = 3699001029492595509L;
	public static volatile int darkness = 0;
	public static volatile boolean ongoingTransition = false;
	@Override public void draw(boolean render) {
		if(isVisible() && getWidth() > 0 && getHeight() > 0) {
			image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphics = image.createGraphics();
			graphics.setPaintMode();
			graphics.setColor(new Color(0, 0, 0, darkness));
			graphics.fillRect(0, 0, getWidth(), getHeight());
			graphics.dispose();
		} super.draw(render);
	}
}