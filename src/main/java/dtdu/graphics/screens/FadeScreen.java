package main.java.dtdu.graphics.screens;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FadeScreen extends GameScreen /*implements Tickable*/ {
	private static final long serialVersionUID = 3699001029492595509L;
	public static volatile int darkness = 0;
	public static volatile boolean ongoingTransition = false;
//	boolean phase;
	@Override
	public void draw(boolean render) {
		if(isVisible() && getWidth() > 0 && getHeight() > 0) {
			image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphics = image.createGraphics();
			graphics.setPaintMode();
			graphics.setColor(new Color(0, 0, 0, darkness));
			graphics.fillRect(0, 0, getWidth(), getHeight());
			graphics.dispose();
		} super.draw(render);
	}
//	@Override
//	public void tick() {
//		if(phase) {
//			if(darkness < 255) {
//				darkness += 15;
//				draw(false);
//				Ticker.update = true;
//			}
//		} else {
//			phase = false;
//			Save.scene.sceneTranstionTo(scene);
//			scene.sceneTransitionFrom(Save.scene);
//		}
//	}
//	@Override
//	public void addToTickList() {
//		Tickable.super.addToTickList();
//		phase = true;
//		ongoingTransition = true;
//		setVisible(true);
//	}
}