package main.java.dtdu.graphics.screens;

import java.awt.Graphics;

import main.java.dtdu.graphics.*;

public class Vignette extends GameScreen implements ScaledDrawable {
	private static final long serialVersionUID = -3984207058539335689L;
	@Override
	public void draw(boolean render) {
		if(image == null) image = Textures.vignette;
		super.draw(render);
	}
	@Override
	public void paint(Graphics g) {
		int width = getParent().getWidth(), height = getParent().getHeight();
		if(getWidth() != width || getHeight() != height) updateDimensions(width, height);
		ScaledDrawable.super.paint(g);
	}
}